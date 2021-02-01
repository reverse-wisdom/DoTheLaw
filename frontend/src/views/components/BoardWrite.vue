<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised" style="z-index:1">
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

            <!-- <md-field>

              <label for="content">내용</label>
              <md-textarea id="content" type="content" ref="content" v-model="content"></md-textarea>
            </md-field> -->
          </form>
          <div id="summernote" style="top: 0;"></div>

          <div class="btn-right">
            <md-button class="md-dense md-raised md-warning" @click="test">
              html콘솔테스트
            </md-button>
            <md-button class="md-dense md-raised md-warning" type="submit" @click="writeContent()">
              등록
            </md-button>
            <md-button class="md-dense md-raised md-info" type="button" @click="moveList">
              뒤로가기
            </md-button>
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

  data: function() {
    return {
      title: '',
      content: '',
      category: '',
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
  mounted() {
    // $('#summernote').summernote({
    //   height: 700,
    // });

    $(function() {
      $('#summernote').summernote({
        // unfortunately you can only rewrite
        // all the toolbar contents, on the bright side
        // you can place uploadcare button wherever you want

        height: 300, // set editor height
        width: '90%', // set editor height
        minHeight: null, // set minimum height of editor
        maxHeight: null, // set maximum height of editor
        dialogsInBody: true,
        toolbar: [
          ['uploadcare', ['uploadcare']], // here, for example
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
        uploadcare: {
          // button name (default is Uploadcare)
          buttonLabel: 'Image / file',
          // font-awesome icon name (you need to include font awesome on the page)
          buttonIcon: 'picture-o',
          // text which will be shown in button tooltip
          tooltipText: 'Upload files or video or something',

          // uploadcare widget options,
          // see https://uploadcare.com/documentation/widget/#configuration
          publicKey: 'c7e819f2a9454b0a2286', // set your API key
          crop: 'free',
          tabs: 'all',
          multiple: true,
        },
      });
    });
  },
  methods: {
    test() {
      // let bodyClick = document.getElementById('summernote');

      let test = $('#summernote').summernote('code');

      console.log(this.$store.state.uuid);
    },

    // 게시판 글 작성
    writeContent() {
      if (undefined !== this.title && this.title.length < 3) {
        this.$swal({
          icon: 'error',
          title: '제목을 3자 이상 적어주세요!!',
        });
      } else {
        axios
          .post(
            '/api/board/create',
            {
              title: this.title,
              category: this.category,
              content: $('#summernote').summernote('code'),
              uuid: this.$store.state.uuid,
            },
            {
              headers: {
                'x-auth-token': this.$store.state.token,
              },
            }
          )
          .then(({ data }) => {
            console.log(data);
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
// .modal-backdrop {
//   opacity: 0;
//   top: none !important;
// }
// .note-dialog .modal-dialog {
//   z-index: 1050;
// }
</style>
