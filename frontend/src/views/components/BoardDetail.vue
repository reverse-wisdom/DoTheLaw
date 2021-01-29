<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">게시판 상세 내용</h2>

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
              <th scope="col">작성시간</th>
              <td>{{ value.upload_date }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">조회수</th>
              <td>{{ value.hit }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">내용</th>
              <td>{{ value.content }}</td>
            </tr>
          </table>

          <!-- 수정, 삭제는 자신이 쓴글일경우만 나타나게 처리함 -->
          <!-- 추후 비슷하게 로직처리할 것 -->
          <div v-if="$store.state.name == value.name" style="text-align:right">
            <md-button class="md-warning" @click="updatePage(value)">글수정</md-button>
            <md-button class="md-rose" @click="deletePage()">글 삭제</md-button>
            <md-button class="md-info" @click="moveBoard()">뒤로가기</md-button>
          </div>
          <!-- else -->
          <div v-if="$store.state.name != value.name" style="text-align:right">
            <md-button class="md-info" @click="moveBoard()">뒤로가기</md-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  bodyClass: 'profile-page',
  data() {
    return {
      value: '',
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
  created() {
    this.token = this.$store.state.token;
    axios
      .get('/api/board/search/detail?board_id=' + this.$route.query.board_id, {
        headers: {
          'x-auth-token': this.token,
        },
      })
      .then(({ data }) => {
        this.value = data;
      })
      .catch();
  },
  methods: {
    moveBoard() {
      this.$router.push('/board');
    },
    deletePage() {
      var obj = null;
      axios
        .delete(SERVER_URL + '/board/' + this.$route.query.boardNo)
        .then(({ data }) => {
          let msg = '삭제 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            JSON.stringify(response.data);
            obj = response;
          }
        })
        .catch(() => {});
      this.$swal({
        position: 'top-end',
        icon: 'success',
        title: '삭제성공!!',
        showConfirmButton: false,
        timer: 1500,
      });
      this.$router.push('/board');
    },
    updatePage(value) {
      var no = value.no;
      this.$router.push({ name: 'boardupdate', query: { boardNo: no } });
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
