import Vue from 'vue';
import App from './App.vue';
import router from './router';
import Vuetify from 'vuetify';
import store from '@/store/index';
import 'vuetify/dist/vuetify.min.css';
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import VueCookies from 'vue-cookies';
import vueMoment from 'vue-moment';
import moment from 'moment';

import MaterialKit from './plugins/material-kit';

import vuescroll from 'vue-scroll';
import WebRTC from 'vue-webrtc';
import * as io from 'socket.io-client';
window.io = io;

const options = {
  confirmButtonColor: '#41b882',
  cancelButtonColor: '#ff7674',
};

moment.locale('ko');

Vue.use(vuescroll)
Vue.use(WebRTC);
Vue.component('vueWebrtc', WebRTC['vue-webrtc']);

Vue.use(vueMoment, { moment });

Vue.use(VueSweetalert2, options); // alert API

Vue.config.productionTip = false;

Vue.use(MaterialKit);
Vue.use(Vuetify);
Vue.use(VueCookies);

const NavbarStore = {
  showNavbar: false,
};

Vue.mixin({
  data() {
    return {
      NavbarStore,
    };
  },
});

new Vue({
  router,
  store,
  vuetify: new Vuetify(),
  render: (h) => h(App),
}).$mount('#app');
