<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised" style="z-index:1">
      <div class="section profile-content">
        <div style="padding:80px">
          <h2 class="title text-center kor" style="font-weight: bold;">자문게시판 글수정</h2>
          <hr class="div-hr" />
          <form @submit.prevent="modifyAdvise">
            <md-field>
              <label>제목</label>
              <md-input id="title" type="text" ref="title" v-model="value.title"></md-input>
            </md-field>

            <md-field>
              <v-row align="center">
                <v-col cols="12">
                  <v-select :items="items" :menu-props="{ bottom: true, offsetY: true }" label="카테고리" v-model="selected">{{ value.category }}</v-select>
                </v-col>
              </v-row>
            </md-field>

            <md-field>
              <label for="state">상태</label>
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
              <div id="summernote"></div>
            </md-field>

            <md-field>
              <label>비고란</label>
              <md-input v-model="remarks"></md-input>
            </md-field>

            <md-field>
              <!-- 파일의 경우 change 리스너로 감지해야함 -->
              <input type="file" name="uploadFile" ref="fileData" />
              <!-- <input type="file" name="uploadFile" ref="fileData" @change="handleFilesUpload" /> -->
            </md-field>

            <md-field>
              <v-row>
                <DateTimePicker :label="'예약날짜'" @date="UTCconvert" />
                <p class="my-auto">추후 변호사의 일정에 따라 변동될 수 있습니다.</p>
              </v-row>
            </md-field>
            <div class="btn-right">
              <v-btn color="rgb(68, 114, 148)" class="ml-1" dark style="float: right;" type="submit">
                수정완료
              </v-btn>
              <v-btn color="rgb(68, 114, 148)" dark style="float: right;" @click="moveAdviseList">
                목록
              </v-btn>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { detailAdvise, editAdvise } from '@/api/advise';
import DateTimePicker from '@/views/components/advise/DateTimePicker.vue';
export default {
  bodyClass: 'profile-page',
  components: {
    DateTimePicker,
  },
  data: function() {
    return {
      value: '',
      content: '',
      selected: '',
      state: '',
      remarks: '',
      reservationDate: '',
      items: ['교통/운전', '가정', '근로/노동', '부동산', '금융', '정보통신/기술'],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_matching.png'),
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
    this.value = data;
    this.selected = data.category;
    $(function() {
      $('#summernote').summernote({
        height: 300, // set editor height
        width: '100%', // set editor weight
        minHeight: null, // set minimum height of editor
        maxHeight: null, // set maximum height of editor
        dialogsInBody: true,
        toolbar: [
          ['style', ['style']],
          ['font', ['bold', 'italic', 'underline', 'clear']],
          ['fontname', ['fontname']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['height', ['height']],
          ['table', ['table']],
          ['insert', ['media', 'link', 'hr', 'picture', 'video']],
          ['view', ['fullscreen', 'codeview']],
        ],
      });
      $('#summernote').summernote('pasteHTML', data.content);
    });
  },
  methods: {
    async modifyAdvise() {
      const editData = {
        matchingId: this.value.matchingId,
        category: this.selected,
        remarks: this.value.remarks,
        content: $('#summernote').summernote('code'),
        title: this.value.title,
        reservationDate: this.reservationDate,
        state: this.state,
        name: this.value.name,
        uuid: this.value.uuid,
      };

      const uuid = this.$store.state.uuid;
      const { data } = await editAdvise(editData, uuid);

      this.$swal({
        icon: 'success',
        title: '글 수정 완료',
      });
      var matchingId = this.value.matchingId;
      this.$router.push({ name: 'AdviseDetail', query: { matchingId: matchingId } });
    },
    moveAdviseList() {
      this.$router.push({ name: 'adviseList' });
    },
    //UTC형태로 변환
    UTCconvert(olddate) {
      var replaceAt = function(input, index, character) {
        return input.substr(0, index) + character + input.substr(index + character.length);
      };
      this.reservationDate = replaceAt(olddate, 10, 'T');
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
  text-align: center;
  width: 10rem;
}
.styled-table th,
.styled-table td {
  padding: 12px 15px;
}
</style>
