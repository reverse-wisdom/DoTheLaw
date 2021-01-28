<template>
  <!-- 백에서 완성시 작성할것 -->
  <!-- 게시판 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">회원 게시판</h2>
        <hr class="div-hr" />
        <div class="inn">
          <div class="md-layout text-center">
            <div class="md-layout-item md-medium-size-100 md-small-size-100">
              <form class="form" @submit="searchBoard()" onSubmit="return false;">
                <select name="searchType" id="searchType">
                  <option value="title">제목</option>
                  <option value="content">내용</option>
                  <option value="writer">작성자</option>
                </select>
                <input id="searchWord" type="text" placeholder="검색어" @keydown.enter="searchBoard()" />
                <md-button class="md-info" style="margin:auto;" @click="searchBoard()">검색</md-button>
              </form>
            </div>
          </div>
        </div>

        <div class="container">
          <!-- 게시판 테이블 -->
          <table class="styled-table" style="width: 100%">
            <thead>
              <tr>
                <th scope="col">제목</th>
                <th scope="col">내용</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">업로드시간</th>
              </tr>
            </thead>
            <template>
              <!-- 검색데이터 처리를 위해 조건으로 나눔 -->
              <tbody v-if="pageArray.length == 0">
                <tr v-for="value in values" :key="value.id" @click="detailPage(value)">
                  <td>{{ value.title }}</td>
                  <td>{{ value.content.substring(0, 10) }}</td>
                  <td>{{ value.writer }}</td>
                  <td>{{ value.hit }}</td>
                  <td>{{ value.uploaddate }}</td>
                </tr>
              </tbody>
              <tbody>
                <tr v-for="value in pageArray" :key="value.id" @click="detailPage(value)">
                  <td>{{ value.title }}</td>
                  <td>{{ value.content.substring(0, 10) }}</td>
                  <td>{{ value.writer }}</td>
                  <td>{{ value.hit }}</td>
                  <td>{{ value.uploaddate }}</td>
                </tr>
              </tbody>
            </template>
          </table>

          <!-- 회원만 글쓰기가 가능함 -->
          <div class="btn-right">
            <md-button class="md-dense md-raised md-info" v-if="getUserId.length > 0" type="button" @click="movePage">
              글쓰기
            </md-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import BoardCreate from '@/views/components/BoardCreate.vue';
import { mapGetters } from 'vuex';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  bodyClass: 'profile-page',
  data() {
    return {
      pageArray: [],
      values: [],
    };
  },
  mounted() {
    let $vm = this;
    axios
      .get(SERVER_URL + '/board/search')
      .then(({ data }) => {
        this.values = data;
      })
      .catch();
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
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
  },
  methods: {
    movePage() {
      this.$router.push('/boardcreate');
    },
    detailPage(value) {
      var no = value.no;
      var hit = JSON.stringify(no);
      var router = this.$router;

      // 페이지 클릭시 조회수를 증가시켜주고 현재 페이지번호를 라우터에 포함해 이동함
      axios.put(SERVER_URL + '/board/hit/' + no, { no: hit }).then(function(response) {
        JSON.stringify(response.data);
        router.push({ name: 'boarddetail', query: { boardNo: no } });
      });
    },
    // 게시판 검색
    searchBoard() {
      var searchType = document.getElementById('searchType').value;
      var searchWord = document.getElementById('searchWord').value;

      axios
        .get(SERVER_URL + '/board/search/?searchType=' + searchType + '&searchWord=' + searchWord)
        .then(({ data }) => {
          console.log(data);
          this.pageArray = data;
        })
        .catch();
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
.btn-right {
  text-align: right;
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
.styled-table thead tr {
  background-color: #98cec3;
  color: #ffffff;
  text-align: left;
  width: 10rem;
}
.styled-table th,
.styled-table td {
  padding: 12px 15px;
}
.styled-table tbody tr {
  border-bottom: 1px solid #dddddd;
}
//even 잡으면 호버 작동 안됨 어두운 부분.
.styled-table tbody tr:nth-of-type {
  background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
  border-bottom: 2px solid #009879;
}
tr:hover {
  color: rgb(10, 0, 0);
  background-color: rgb(163, 203, 221);
}
.inn {
  padding: 40px;
}
// select option css select,
select,
input {
  width: 200px;
  padding: 0.8em 0.5em;
  border: 1px solid #999;
  font-family: inherit;
  border-radius: 0px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}
select {
  background: url('~@/assets/img/arrow.jpg') no-repeat 95% 50%;
}

select::-ms-expand {
  display: none;
}
</style>
