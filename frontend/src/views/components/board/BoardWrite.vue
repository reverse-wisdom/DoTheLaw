<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised" style="z-index:1">
      <div class="section profile-content">
        <div style="padding:80px">
          <h2 class="title text-center kor" style="font-weight:bold;">게시판 글쓰기</h2>
          <hr class="div-hr" />
          <form v-on:submit.prevent="writeContent">
            <md-field>
              <label>제목</label>
              <md-input id="title" type="text" ref="title" v-model="title"></md-input>
            </md-field>
            <md-field>
              <label for="category">글 종류</label>
              <md-select v-model="category" name="category" id="category">
                <md-option value="공지사항">공지사항</md-option>
                <md-option value="일반">일반</md-option>
                <md-option value="QnA">QnA</md-option>
              </md-select>
            </md-field>
            <md-field>
              <div id="summernote"></div>
            </md-field>
            <md-field>
              <!-- 파일의 경우 change 리스너로 감지해야함 -->
              <input type="file" name="uploadFile" ref="fileData" />
              <!-- <input type="file" name="uploadFile" ref="fileData" @change="handleFilesUpload" /> -->
            </md-field>
          </form>

          <div class="btn-right">
            <v-btn color="rgb(68, 114, 148)" dark type="submit" @click="writeContent()">
              등록
            </v-btn>
            <v-btn color="rgb(68, 114, 148)" class="ml-1" dark type="button" @click="moveList">
              뒤로가기
            </v-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { createBoard } from '@/api/board';

function uploadSummernoteFile(file, editor) {
  let data = new FormData();
  data.append('file', file);
  $.ajax({
    data: data,
    type: 'POST',
    url: '/api/data',
    cache: false,
    contentType: false,
    processData: false,
    xhr: function() {
      //Handle progress upload
      let myXhr = $.ajaxSettings.xhr();
      if (myXhr.upload) myXhr.upload.addEventListener('progress', progressHandlingFunction, false);
      return myXhr;
    },
    success: function(reponse) {
      console.dir(reponse);
      // if (reponse.status === true) {
      let listMimeImg = ['image/png', 'image/jpeg', 'image/webp', 'image/gif', 'image/svg'];
      let listMimeAudio = ['audio/mpeg', 'audio/ogg'];
      let listMimeVideo = ['video/mpeg', 'video/mp4', 'video/webm'];
      let elem;
      console.log(file);
      if (listMimeImg.indexOf(file.type) > -1) {
        //Picture
        $(editor).summernote('insertImage', reponse);
      } else if (listMimeAudio.indexOf(file.type) > -1) {
        //Audio
        elem = document.createElement('audio');
        elem.setAttribute('src', reponse);
        elem.setAttribute('controls', 'controls');
        elem.setAttribute('preload', 'metadata');
        $(editor).summernote('insertNode', elem);
      } else if (listMimeVideo.indexOf(file.type) > -1) {
        //Video
        elem = document.createElement('video');
        elem.setAttribute('src', reponse);
        elem.setAttribute('controls', 'controls');
        elem.setAttribute('preload', 'metadata');
        $(editor).summernote('insertNode', elem);
      } else {
        //Other file type
        elem = document.createElement('a');
        let linkText = document.createTextNode(file.name);
        elem.appendChild(linkText);
        elem.title = file.name;
        elem.href = reponse;
        $(editor).summernote('insertNode', elem);
      }
      // }
    },
  });
}

function progressHandlingFunction(e) {
  if (e.lengthComputable) {
    //Log current progress
    console.log((e.loaded / e.total) * 100 + '%');

    //Reset progress on complete
    if (e.loaded === e.total) {
      console.log('Upload finished.');
    }
  }
}

export default {
  bodyClass: 'profile-page',
  data() {
    return {
      title: '',
      content: '',
      category: '',
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_board.jpg'),
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
          ['insert', ['media', 'link', 'hr', 'picture', 'video', 'file']],
          ['view', ['fullscreen', 'codeview']],
        ],
        callbacks: {
          //여기 부분이 이미지를 첨부하는 부분
          onFileUpload: function(files, editor, welEditable) {
            for (var i = files.length - 1; i >= 0; i--) {
              uploadSummernoteFile(files[i], this);
            }
          },
          onImageUpload: function(files, editor, welEditable) {
            for (var i = files.length - 1; i >= 0; i--) {
              uploadSummernoteFile(files[i], this);
            }
          },
          onPaste: function(e) {
            // var clipboardData = e.originalEvent.clipboardData;
            // if (clipboardData && clipboardData.items && clipboardData.items.length) {
            //   var item = clipboardData.items[0];
            //   if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
            //     e.preventDefault();
            //   }
            // }
          },
        },
      });
    });
  },
  methods: {
    async writeContent() {
      const data = {
        uuid: this.$store.state.uuid,
        title: this.title,
        writer: this.$store.state.name,
        content: $('#summernote').summernote('code'),
        category: this.category,
      };
      const response = await createBoard(data);

      this.$swal({
        icon: 'success',
        title: '글 작성 완료!!',
        showConfirmButton: false,
        timer: 1500,
      });
      this.moveList();
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
