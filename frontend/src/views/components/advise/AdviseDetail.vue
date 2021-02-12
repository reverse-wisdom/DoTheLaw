<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">자문 상세 내용</h2>

        <hr class="div-hr" />
        <div class="container">
          <table class="styled-table" style="width: 100%">
            <tr>
              <th scope="col">제목</th>
              <td>{{ value.title }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">작성자</th>
              <td>{{ value.name }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">진행상황</th>
              <td>{{ value.state }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">비고란</th>
              <td>{{ value.remarks }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">예약시간</th>
              <td>{{ this.$moment(value.reservationDate).format('llll') }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">작성시간</th>
              <td>{{ this.$moment(value.createDate).format('llll') }}</td>
            </tr>

            <!-- <tr style="border-top: 1px solid;">
              <th scope="col">조회수</th>
              <td>{{ value.hit }}</td>
            </tr> -->
            <tr style="border-top: 1px solid;">
              <th scope="col">내용</th>
              <td v-html="value.content"></td>
            </tr>
          </table>

          <!-- 수정, 삭제는 자신이 쓴글일경우만 나타나게 처리함 -->
          <div v-if="$store.state.role == 'USER'" style="text-align:right">
            <md-button class="md-rose" @click="adviseDelete">자문삭제</md-button>
            <md-button class="md-warning" @click="updateAdvise(value)">자문수정</md-button>
            <!-- <md-button class="md-info" @click="moveBoard()">뒤로가기</md-button> -->
          </div>
          <!-- else -->
          <div v-if="$store.state.role == 'LAWYER'" style="text-align:right">
            <md-button class="md-info" @click="modal">자문일정</md-button>
            <md-button class="md-info" @click="$router.go(-1)">뒤로가기</md-button>
            <!-- 모달 -->
            <div class="md-layout">
              <div class="md-layout-item md-size-33">
                <modal v-if="classicModal" @close="classicModalHide">
                  <template slot="header">
                    <h4 class="modal-title kor">{{ value.name }}과의 자문일정</h4>
                    <md-button class="md-simple md-just-icon md-round modal-default-button" @click="classicModalHide">
                      <md-icon>clear</md-icon>
                    </md-button>
                  </template>
                  <template slot="body">
                    <form @submit.prevent="modifyAdvise">
                      <md-field>
                        <label for="state">자문 진행상태</label>
                        <md-select v-model="state" name="state" id="state">
                          <md-option value="신청">신청</md-option>
                          <md-option value="접수">접수</md-option>
                          <md-option value="예약">예약</md-option>
                          <md-option value="진행">진행</md-option>
                          <md-option value="완료">완료</md-option>
                          <md-option value="종료">종료</md-option>
                        </md-select>
                      </md-field>
                      <md-field>
                        <v-row>
                          <DateTimePicker :label="'예약날짜'" @date="UTCconvert" />
                        </v-row>
                      </md-field>
                      <md-field>
                        <label>비고란</label>
                        <md-input v-model="remarks"></md-input>
                      </md-field>
                    </form>
                  </template>
                  <template slot="footer">
                    <md-button class="md-danger md-simple" type="submit" @click="modifyAdvise()">완료</md-button>
                  </template>
                </modal>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { detailAdvise, deleteAdvise, editAdvise } from '@/api/advise';
import { Modal } from '@/components';
import DateTimePicker from '@/views/components/advise/DateTimePicker.vue';
export default {
  bodyClass: 'profile-page',
  components: {
    Modal,
    DateTimePicker,
  },
  data() {
    return {
      value: '',
      state: '',
      remarks: '',
      comments: [],
      classicModal: false,
      reservationDate: '',
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  async created() {
    const adviseId = this.$route.query.matchingId;
    const { data } = await detailAdvise(adviseId);
    console.log(data);
    this.value = data;
  },
  methods: {
    // moveBoard() {
    //   this.$router.go('/board');
    // },
    async adviseDelete() {
      const uuid = this.$store.state.uuid;
      const matchingId = this.value.matchingId;
      const { data } = await deleteAdvise(uuid, matchingId);
      this.$swal({
        icon: 'success',
        title: '삭제성공!!',
        showConfirmButton: false,
        timer: 1500,
      });
      this.$router.push('/profileUser');
    },
    updateAdvise(value) {
      var matchingId = value.matchingId;
      this.$router.push({ name: 'AdviseUpdate', query: { matchingId: matchingId } });
    },
    uploadComment(newComment) {
      axios
        .get('/api/comment/search?boardId=' + this.$route.query.boardId, {})
        .then(({ data }) => {
          this.comments = data;
        })
        .catch();
    },
    deleteComment(oldComment) {
      const commentIndex = this.comments.findIndex((comment) => {
        return comment.comment_id === oldComment.comment_id;
      });
      this.comments.splice(commentIndex, 1);
    },
    // 모달 숨기기
    classicModalHide() {
      this.classicModal = false;
    },
    modal() {
      this.classicModal = true;
    },
    //UTC형태로 변환
    UTCconvert(olddate) {
      var replaceAt = function(input, index, character) {
        return input.substr(0, index) + character + input.substr(index + character.length);
      };
      this.reservationDate = replaceAt(olddate, 10, 'T');
      console.log(this.reservationDate);
    },
    //수정 axios
    async modifyAdvise() {
      const editData = {
        matchingId: this.value.matchingId,
        category: this.value.category,
        remarks: this.value.remarks,
        // content: $('#summernote').summernote('code'),
        title: this.value.title,
        reservationDate: this.value.reservationDate,
        state: this.state,
        name: this.value.name,
        uuid: this.value.uuid,
      };
      console.log('zzz', editData);
      const uuid = this.$store.state.uuid;
      const { data } = await editAdvise(editData, uuid);
      this.classicModal = false;
      this.$swal({
        icon: 'success',
        title: '글 수정 완료',
      });
      {
        const adviseId = this.$route.query.matchingId;
        const { data } = await detailAdvise(adviseId);
        this.value = data;
      }
      // var matchingId = this.value.matchingId;
      // this.$router.push({ name: 'AdviseDetail', query: { matchingId: matchingId } });
    },
  },
};
</script>

<style lang="scss" scoped>
// hr 설정
.div-hr {
  width: 80%;
}

// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
// table css
.styled-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.styled-table th {
  background-color: #98cec3;
  color: #ffffff;
  width: 10rem;
  text-align: center;
}
.styled-table th,
.styled-table td {
  padding: 12px 15px;
}
</style>
