<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised" style="z-index:1">
      <div class="section profile-content">
        <div style="padding:80px">
          <h1 class="title text-center kor">공사중!!!!</h1>
          <h2 class="title text-center kor">자문게시판 글쓰기</h2>
          <hr class="div-hr" />
          <form @submit.prevent="modifyAdvise">
            <md-field>
              <label>제목</label>
              <md-input
                id="title"
                type="text"
                ref="title"
                v-model="value.title"
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
                    >{{ value.category }}</v-select
                  >
                </v-col>
              </v-row>
            </md-field>
            <md-field>
              <div id="summernote"></div>
            </md-field>
            <md-field>
              <!-- 파일의 경우 change 리스너로 감지해야함 -->
              <input type="file" name="uploadFile" ref="fileData" />
              <!-- <input type="file" name="uploadFile" ref="fileData" @change="handleFilesUpload" /> -->
            </md-field>
            <!-- <md-field>
              <label for="content">내용</label>
              <md-textarea
                id="content"
                type="content"
                ref="content"
                v-model="content"
              ></md-textarea>
            </md-field> -->

            <div class="btn-right">
              <md-button class="md-dense md-raised md-warning" type="submit">
                수정완료
              </md-button>
              <md-button
                class="md-dense md-raised md-info"
                @click="moveAdviseList"
              >
                목록
              </md-button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { detailAdvise, editAdvise } from "@/api/advise";

export default {
  bodyClass: "profile-page",
  data: function() {
    return {
      value: "",
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
  async created() {
    // const userData = this.$store.state.role;
    const postData = this.$route.query.boardId;
    const { data } = await detailAdvise(postData);
    console.log(data);
    this.value = data;
    this.selected = data.category;
    // console.log(this.value.content);
    $(function() {
      $("#summernote").summernote({
        height: 300, // set editor height
        width: "100%", // set editor weight
        minHeight: null, // set minimum height of editor
        maxHeight: null, // set maximum height of editor
        dialogsInBody: true,
        toolbar: [
          ["style", ["style"]],
          ["font", ["bold", "italic", "underline", "clear"]],
          ["fontname", ["fontname"]],
          ["color", ["color"]],
          ["para", ["ul", "ol", "paragraph"]],
          ["height", ["height"]],
          ["table", ["table"]],
          ["insert", ["media", "link", "hr", "picture", "video"]],
          ["view", ["fullscreen", "codeview"]],
        ],
      });
      $("#summernote").summernote("pasteHTML", data.content);
    });
  },
  mounted() {
    // $('#summernote').summernote({
    //   height: 700,
    // });
  },
  methods: {
    async modifyAdvise() {
      const editData = {
        boardId: this.value.boardId,
        category: this.value.category,
        content: $("#summernote").summernote("code"),
        title: this.value.title,
        name: this.value.name,
        uuid: this.value.uuid,
      };

      const role = this.$store.state.role;
      const { data } = await editAdvise(editData, role);
      console.log(data);

      this.$swal({
        icon: "success",
        title: "글 수정 완료",
      });
      var query = this.value.boardId;
      this.$router.push({ name: "AdviseDetail", query: { boardId: query } });
    },
    moveAdviseList() {
      this.$router.push({ name: "AdviseList" });
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
