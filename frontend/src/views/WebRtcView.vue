<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised kor">
      <div class="section profile-content">
        <hr />
        <div class="container">
          <h2 class="title-solid text-center">화상자문매칭</h2>
          <v-input v-model="roomId" />
          <v-text-field label="방이름" v-model="roomId"></v-text-field>

          <div>
            <div class="row">
              <div class="col-8">
                <vue-webrtc
                  ref="webrtc"
                  width="100%"
                  :roomId="roomId"
                  :socketURL="socketURL"
                  v-on:joined-room="logEvent"
                  v-on:left-room="logEvent"
                  v-on:opened-room="logEvent"
                  v-on:share-started="logEvent"
                  v-on:share-stopped="logEvent"
                  @error="onError"
                />
              </div>
              <div class="col-4">
                <chat-room :roomId="roomId" v-if="roomId"></chat-room>
              </div>
            </div>

            <div>
              <div>
                <div class="mb-5">
                  <v-btn color="error" dark style="margin-right: 10px; padding: 25px 25px; font-size: 17px;" @click="onLeave">나가기</v-btn>
                  <v-btn color="light-blue lighten-2" dark style="padding: 25px 25px; font-size: 17px;" @click="onShareScreen">화면공유</v-btn>
                </div>
              </div>
            </div>
            <!-- <div>
              <div class="col-md-12">
                <figure class="figure">
                  <img :src="img" class="img-responsive" />
                </figure>
              </div>
            </div> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import WebRTC from 'vue-webrtc';
import * as io from 'socket.io-client';

import ChatRoom from './components/ChatRoom.vue';
import { fetchAdviseMe } from '@/api/advise';

window.io = io;

Vue.use(WebRTC);
Vue.component('vueWebrtc', WebRTC['vue-webrtc']);

export default {
  name: 'web-rtc',
  bodyClass: 'profile-page',
  components: { ChatRoom },
  data() {
    return {
      img: null,
      roomId: '', // 방이름 (영어와 _만 가능)
      socketURL: 'https://rtcmulticonnection.herokuapp.com:443/',
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_webRTC.jpg'),
    },
  },
  mounted() {
    this.$refs.webrtc.join();
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  async created() {
    console.log('test', this.$route.query.matchingId);
    this.roomId = this.$route.query.matchingId;
  },

  methods: {
    onCapture() {
      this.img = this.$refs.webrtc.capture();
    },
    onJoin() {
      this.$refs.webrtc.join();
    },
    onLeave() {
      this.$swal({
        icon: 'info',
        title: '종료하시겠습니까?',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '승인',
        cancelButtonText: '취소',
      }).then((result) => {
        if (result.isConfirmed) {
          this.$refs.webrtc.leave();

          window.close();
        } else {
        }
      });
    },
    onShareScreen() {
      this.img = this.$refs.webrtc.shareScreen();
    },
    onError(error, stream) {
      console.log('On Error Event', error, stream);
    },
    logEvent(event) {
      console.log('Event : ', event);
    },
  },
};
</script>

<style lang="scss" scoped>
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.section {
  padding: 0;
}

.video-list {
  margin: 0 auto;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-evenly;
  background: white;
  // flex-wrap: wrap;
  // flex-direction: row;
}

video {
  border: 2px solid red;
}
// .video-list > video {
//   width: 50px;
// }
// .video-list video {
//   width: 50px;
//   margin: 10 auto;
//   // display: inline-flex;
//   flex-direction: column;
//   flex: none;
//   // flex-basis: auto;
//   flex-grow: 1;
//   // flex-basis: 33.33%;
//   margin-top: 20px;
//   padding: 0 5px;
//   box-sizing: border-box;
//   border: 2x, red;
// }
</style>
