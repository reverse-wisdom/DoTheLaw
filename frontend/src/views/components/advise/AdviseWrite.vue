<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised" style="z-index:1">
      <div class="section profile-content">
        <div style="padding:80px">
          <h2 class="title text-center kor" style="font-weight: bold;">자문요청</h2>
          <hr class="div-hr" />
          <form>
            <md-field>
              <label>제목</label>
              <md-input id="title" type="text" ref="title" v-model="title"></md-input>
            </md-field>
            <md-field>
              <label for="category">자문종류</label>
              <md-select v-model="category" name="category" id="category">
                <md-option value="교통/운전">교통/운전</md-option>
                <md-option value="가정">가정</md-option>
                <md-option value="근로/노동">근로/노동</md-option>
                <md-option value="부동산">부동산</md-option>
                <md-option value="금융">금융</md-option>
                <md-option value="정보통신/기술">정보통신/기술</md-option>
              </md-select>
            </md-field>
            <md-field>
              <label for="state">상태</label>
              <md-select v-model="state" name="state" id="state" disabled>
                <md-option value="신청">신청</md-option>
                <md-option value="접수">접수</md-option>
                <md-option value="예약">예약</md-option>
                <md-option value="진행">진행</md-option>
                <md-option value="완료">완료</md-option>
                <md-option value="종료">종료</md-option>
              </md-select>
            </md-field>
            <md-field>
              <div id="summernote"></div>
            </md-field>
            <md-field>
              <!-- 파일의 경우 change 리스너로 감지해야함 -->
              <!-- <input type="file" name="uploadFile" ref="fileData" /> -->
              <!-- <input type="file" name="uploadFile" ref="fileData" @change="handleFilesUpload" /> -->
            </md-field>
            <md-field>
              <v-row>
                <DateTimePicker :label="'예약날짜'" @date="UTCconvert" />
                <p class="my-auto">추후 변호사의 일정에 따라 변동될 수 있습니다.</p>
              </v-row>
            </md-field>
            <!-- <md-field>
              <label>비고란</label>
              <md-input v-model="remarks"></md-input>
            </md-field> -->
          </form>
          <div class="btn-right">
            <v-btn color="primary" elevation="2" medium type="submit" @click="writeAdvise()">등록하기</v-btn>
            <!-- <md-button class="md-dense md-raised md-warning" type="submit" @click="writeAdvise()">
              등록
            </md-button> -->
          </div>
          <!-- <h2>{{ date }}</h2> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { createAdvise, detailAdvise } from '@/api/advise';
import DateTimePicker from '@/views/components/advise/DateTimePicker.vue';
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
  components: {
    DateTimePicker,
  },
  data() {
    return {
      title: '',
      content: '',
      category: '',
      state: '신청',
      reservationDate: '',
      remarks: '',
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_matching.png'),
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
    async writeAdvise() {
      const adviseData = {
        uuid: this.$store.state.uuid,
        lawyerUuid: this.$route.query.lawyerUuid,
        // lawyerUuid: this.$store.state.lawuuid,
        reservationDate: this.reservationDate,
        remarks: this.remarks,
        state: this.state,
        title: this.title,
        // writer: this.$store.state.name,
        content: $('#summernote').summernote('code'),
        category: this.category,
      };
      const res = await createAdvise(adviseData);
      this.$swal({
        icon: 'success',
        title: '작성완료',
        showConfirmButton: false,
        timer: 1500,
      });
      this.detailGo();
    },

    //UTC형태로 변환
    UTCconvert(olddate) {
      var replaceAt = function(input, index, character) {
        return input.substr(0, index) + character + input.substr(index + character.length);
      };
      this.reservationDate = replaceAt(olddate, 10, 'T');
    },
    detailGo() {
      const email = this.$route.query.email;
      this.$router.push({ name: 'lawyermatchdetail', query: { email: email } });
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
