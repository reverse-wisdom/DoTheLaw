import Vue from 'vue';
import App from './App.vue';
import router from './router';
import Vuetify from 'vuetify';
import store from '@/store/index';
import 'vuetify/dist/vuetify.min.css';
import VueSweetalert2 from 'vue-sweetalert2';

import MaterialKit from './plugins/material-kit';

const options = {
  confirmButtonColor: '#41b882',
  cancelButtonColor: '#ff7674',
};

Vue.use(VueSweetalert2, options); // alert API

Vue.config.productionTip = false;

Vue.use(MaterialKit);
Vue.use(Vuetify);

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
