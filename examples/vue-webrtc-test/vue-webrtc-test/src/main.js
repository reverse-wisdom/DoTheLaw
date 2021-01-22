import Vue from "vue";
import WebRTC from "vue-webrtc";
import App from "./App.vue";

Vue.config.productionTip = false;

Vue.use(WebRTC);
Vue.component(WebRTC.name, WebRTC["vue-webrtc"]);

new Vue({
  render: (h) => h(App),
}).$mount("#app");
