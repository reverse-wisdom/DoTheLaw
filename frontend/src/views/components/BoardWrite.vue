<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div style="padding:80px">
          <h1 class="title text-center kor">공사중!!!!</h1>
          <h2 class="title text-center kor">게시판 글쓰기</h2>
          <hr class="div-hr" />
          <form @submit.prevent="writeContent">
            <md-field>
              <label>제목</label>
              <md-input
                id="title"
                type="text"
                ref="title"
                v-model="title"
              ></md-input>
            </md-field>
            <md-field>
              <v-row align="center">
                <v-col cols="12">
                  <v-select
                    :items="items"
                    :menu-props="{ bottom: true, offsetY: true }"
                    label="카테고리"
                    v-model="selected"
                  ></v-select>
                </v-col>
              </v-row>
            </md-field>

            <md-field>
              <label for="content">내용</label>
              <md-textarea
                id="content"
                type="content"
                ref="content"
                v-model="content"
              ></md-textarea>
            </md-field>

            <div class="btn-right">
              <md-button class="md-dense md-raised md-warning" type="submit">
                등록
              </md-button>
              <md-button
                class="md-dense md-raised md-info"
                type="button"
                @click="moveList"
              >
                뒤로가기
              </md-button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { createBoard } from "@/api/board";

export default {
  bodyClass: "profile-page",
  data: function() {
    return {
      title: "",
      content: "",
      selected: "",
      items: [
        "교통/운전",
        "가정",
        "근로/노동",
        "부동산",
        "금융",
        "정보통신/기술",
      ],
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
  },
  methods: {
    // 게시판 글 작성
    async writeContent() {
      if (undefined !== this.title && this.title.length < 3) {
        this.$swal({
          icon: "error",
          title: "제목을 3자 이상 적어주세요!!",
        });
      } else if (undefined !== this.content && this.content.length < 10) {
        this.$swal({
          icon: "error",
          title: "내용을 5자 이상 적어주세요!!",
        });
      } else {
        const data = {
          uuid: this.$store.state.uuid,
          title: this.title,
          writer: this.$store.state.name,
          content: this.content,
          category: this.selected,
        };
        // console.log(data)
        const response = await createBoard(data);
        console.log("완료1111111111", response);

        // axios
        //   .post(SERVER_URL + "api/board/create", {

        //   })
        //   .then(function(response) {
        // JSON.stringify(response.data);
        // obj = JSON.stringify(response.data);
        //   });

        this.$swal({
          position: "top-end",
          icon: "success",
          title: "글 작성 완료!!",
          showConfirmButton: false,
          timer: 1500,
        });
        this.moveList();
      }
    },
    moveList() {
      this.$router.push({ name: "board" });
    },
  },
};
</script>
<style lang="scss" scoped>
.kor {
  font-family: "Nanum Gothic", sans-serif;
}
.btn-right {
  text-align: right;
}
.titleSize {
  margin: 10px;
  width: 50%;
  min-height: 30px;
}
.boxSize {
  margin: 10px;
  width: 50%;
  min-height: 300px;
  padding: 10px;
}
</style>
