<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div style="padding:80px">
          <h1 class="title text-center kor">공사중!!!!</h1>
          <h2 class="title text-center kor">게시판 글쓰기</h2>
          <hr class="div-hr" />
          <form v-on:submit.prevent="writeContent">
            <md-field>
              <label>제목</label>
              <md-input id="title" type="text" ref="title" v-model="title"></md-input>
            </md-field>
            <md-field>
              <label>종류</label>
              <md-input id="title" type="text" ref="title" v-model="category"></md-input>
            </md-field>

            <md-field>
              <label for="content">내용</label>
              <md-textarea id="content" type="content" ref="content" v-model="content"></md-textarea>
            </md-field>

            <div class="btn-right">
              <!-- <md-button class="md-dense md-raised md-warning" type="submit" @click="writeContent()">
                등록
              </md-button> -->
              <md-button class="md-dense md-raised md-info" type="button" @click="moveList">
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
import axios from 'axios';

export default {
  bodyClass: 'profile-page',
  data: function() {
    return {
      title: '',
      content: '',
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
  methods: {
    // 게시판 글 작성
    writeContent() {
      if (undefined !== this.title && this.title.length < 3) {
        this.$swal({
          icon: 'error',
          title: '제목을 3자 이상 적어주세요!!',
        });
      } else if (undefined !== this.content && this.content.length < 10) {
        this.$swal({
          icon: 'error',
          title: '내용을 5자 이상 적어주세요!!',
        });
      } else {
        axios
          .post(SERVER_URL + '/board', {
            title: this.title,
            writer: this.getUserName,
            content: this.content,
          })
          .then(function(response) {
            JSON.stringify(response.data);
            obj = JSON.stringify(response.data);
          });

        this.$swal({
          position: 'top-end',
          icon: 'success',
          title: '글 작성 완료!!',
          showConfirmButton: false,
          timer: 1500,
        });
        this.moveList();
      }
    },
    moveList() {
      this.$router.push('/board');
    },
  },
};
</script>
<style lang="scss" scoped>
.kor {
  font-family: 'Nanum Gothic', sans-serif;
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
