<template>
  <!-- 공지사항 수정 페이지 -->
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">자문게시판 수정</h2>

        <hr class="div-hr" />
        <div class="container">
          <table class="styled-table" style="width: 100%">
            <tr>
              <th scope="col">카테고리</th>
              <td>{{ value.category }}</td>
            </tr>
            <tr>
              <th scope="col">제목</th>
              <td>
                <input
                  type="text"
                  id="title"
                  v-model="value.title"
                  style="width:100%; height: 30px;"
                />
              </td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">
                글내용
              </th>
              <td>
                <textarea
                  type="text"
                  id="content"
                  v-model="value.content"
                  style="width:100%; height:30px; "
                ></textarea>
              </td>
            </tr>
          </table>

          <div v-if="$store.state.name == value.name" style="text-align:right">
            <md-button class="md-warning" @click="modifyBoard(value)"
              >수정완료</md-button
            >
          </div>
          <!-- else -->
          <div v-if="$store.state.name != value.name" style="text-align:right">
            <md-button class="md-info" @click="moveList()">목록</md-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import { mapGetters } from "vuex";
// import axios from "axios";
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { detailBoard, editBoard } from "@/api/board";
export default {
  bodyClass: "profile-page",
  data() {
    return {
      value: "",
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/jj02.gif"),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },

    // ...mapGetters(["getAccessToken", "getUserId", "getUserName"]),
  },

  async created() {
    // const userData = this.$store.state.role;
    const postData = this.$route.query.boardId;
    const { data } = await detailBoard(postData);
    console.log(data);
    this.value = data;
  },
  // created() {
  //   // 공지사항 상세 정보 호출
  //   axios
  //     .get(SERVER_URL + "/notice/" + this.$route.query.noticeNo)
  //     .then(({ data }) => {
  //       this.value = data;
  //     })
  //     .catch();
  // },
  methods: {
    async modifyBoard() {
      const editData = {
        boardId: this.value.boardId,
        category: this.value.category,
        content: this.value.content,
        title: this.value.title,
        name: this.value.name,
        uuid: this.value.uuid,
      };

      const role = this.$store.state.role;
      const { data } = await editBoard(editData, role);
      console.log(data);
      // axios
      //   .put(SERVER_URL + "/notice/" + no, {
      //     title: title,
      //     content: content,
      //     no: no,
      //   })
      //   .then((response) => {
      //     JSON.stringify(response.data);
      //   })
      //   .catch();

      this.$swal({
        icon: "success",
        title: "글 수정 완료",
      });
      var query = this.value.boardId;
      this.$router.push({ name: "boarddetail", query: { boardId: query } });
    },
  },
  moveList() {
    this.$router.push({ name: "board" });
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
  font-family: "Nanum Gothic", sans-serif;
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
