<template>
  <!-- 로그인 페이지 -->
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised kor">
      <div class="section profile-content">
        <hr />
        <div class="container">
          <h2>Room</h2>
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
                chatting
                <!-- 채팅룸의 룸ID 값은 자문매칭의 ID와 같아야 하므로 임의로 4로 배정  -->
                <chat-room :roomId="4" v-if="roomId"></chat-room>
              </div>
            </div>

            <div>
              <div>
                <div>
                  <md-button class="md-success" @click="onJoin">Join</md-button>
                  <md-button class="md-success" @click="onLeave"
                    >Leave</md-button
                  >
                  <md-button class="md-success" @click="onCapture"
                    >Capture Photo</md-button
                  >
                  <md-button class="md-success" @click="onShareScreen"
                    >Share Screen</md-button
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <div class="col-md-12">
        <h2>Captured Image</h2>
        <figure class="figure">
          <img :src="img" class="img-responsive" />
        </figure>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import WebRTC from 'vue-webrtc';
import * as io from 'socket.io-client';

import ChatRoom from './components/ChatRoom.vue';

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
      roomId: 'private-room', // 방이름 (영어와 _만 가능)
      socketURL: 'https://rtcmulticonnection.herokuapp.com:443/',
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
    onCapture() {
      this.img = this.$refs.webrtc.capture();
    },
    onJoin() {
      this.$refs.webrtc.join();
    },
    onLeave() {
      this.$refs.webrtc.leave();
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
