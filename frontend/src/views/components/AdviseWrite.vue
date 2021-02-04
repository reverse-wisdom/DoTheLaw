<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised" style="z-index:1">
      <div class="section profile-content">
        <div style="padding:80px">
          <h2 class="title text-center kor">자문요청 글쓰기</h2>
          <hr class="div-hr" />
          <form @submit.prevent="writeContent">
            <md-field>
              <label>제목</label>
              <md-input id="title" type="text" ref="title" v-model="title"></md-input>
            </md-field>
            <md-field>
              <v-row align="center">
                <v-col cols="12">
                  <v-select :items="items" :menu-props="{ bottom: true, offsetY: true }" label="카테고리" v-model="selected"></v-select>
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

            <div class="btn-right">
              <md-button class="md-dense md-raised md-warning" type="submit">
                등록
              </md-button>
              <md-button class="md-dense md-raised md-info" type="button" @click="moveAdviseList">
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
import { createAdvise } from '@/api/advise';

export default {
  bodyClass: 'profile-page',
  data() {
    return {
      title: '',
      content: '',
      selected: '',
      items: ['교통/운전', '가정', '근로/노동', '부동산', '금융', '정보통신/기술'],
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
    });
  },
  methods: {
    async writeContent() {
      const data = {
        uuid: this.$store.state.uuid,
        title: this.title,
        writer: this.$store.state.name,
        content: $('#summernote').summernote('code'),
        category: this.selected,
      };
      const response = await createAdvise(data);
      console.log('자문 작성 성공', response);

      this.$swal({
        position: 'top-end',
        icon: 'success',
        title: '글 작성 완료!!',
        showConfirmButton: false,
        timer: 1500,
      });
      this.moveAdviseList();
    },
    moveAdviseList() {
      this.$router.push({ name: 'adviseList' });
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
