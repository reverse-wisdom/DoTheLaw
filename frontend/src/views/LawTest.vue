<template>
  <!-- 게시판 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">법상식테스트</h2>
        <div class="container">
          <img src="@/assets/img/lawtitle.png" style="max-width:80%; height:auto;" class="title-lawtest" alt="" />
          <img src="@/assets/img/main2.png" style="max-width:80%; height:auto;" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { fetchBoardList } from '@/api/board';

export default {
  bodyClass: 'profile-page',
  data() {
    return {
      values: [],
      token: '',
      search: '',
      headers: [
        {
          text: '카테고리',
          align: 'start',
          value: 'category',
        },
        { text: '제목', value: 'title' },
        { text: '작성자', value: 'name' },
        { text: '조회수', value: 'hit' },
        { text: '업로드시간', value: 'uploadDate' },
      ],
    };
  },
  async mounted() {
    try {
      this.token = this.$store.state.token;
      const { data } = await fetchBoardList();
      for (let i = 0; i < data.length; i++) {
        this.values.push({
          boardId: data[i].boardId,
          category: data[i].category,
          hit: data[i].hit,
          name: data[i].name,
          title: data[i].title,
          uploadDate: this.$moment(data[i].uploadDate).format('llll'),
        });
      }
    } catch (err) {
      console.log(err);
    }
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
  methods: {
    writePage() {
      this.$router.push('/boardWrite');
    },
    detailPage(value) {
      var query = value.boardId;
      this.$router.push({ name: 'boardDetail', query: { boardId: query } });
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
img {
  justify-content: center;
  align-items: center;
}
.title-lawtest {
  animation: swing;
  animation-duration: 2s;
  animation-iteration-count: infinite;
}
</style>
