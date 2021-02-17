<template>
  <div>
    <div>
      <div>
        <h2>Room</h2>
        <v-input v-model="roomId" />
        <v-text-field label="방이름" v-model="roomId"></v-text-field>
      </div>
    </div>
    <div>
      <div>
        <div>
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
        <div>
          <div>
            <md-button class="md-success" @click="onJoin">Join</md-button>
            <md-button class="md-success" @click="onLeave">Leave</md-button>
            <md-button class="md-success" @click="onCapture">Capture Photo</md-button>
            <md-button class="md-success" @click="onShareScreen">Share Screen</md-button>
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
window.io = io;

Vue.use(WebRTC);
Vue.component('vueWebrtc', WebRTC['vue-webrtc']);

export default {
  name: 'WebRtcView',
  components: {},
  data() {
    return {
      img: null,
      roomId: 'private-room', // 방이름 (영어와 _만 가능)
      socketURL: 'https://rtcmulticonnection.herokuapp.com:443/',
    };
  },
  computed: {},
  watch: {},
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
    onError(error, stream) {},
    logEvent(event) {},
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
</style>
