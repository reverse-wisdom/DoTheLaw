import Vue from 'vue';
import Router from 'vue-router';
import Index from './views/Index.vue';
import Login from './views/Login.vue';
import Search from './views/Search.vue';
import MainNavbar from './layout/MainNavbar.vue';
import MainFooter from './layout/MainFooter.vue';
import Register from './views/Register.vue';
import WebrtcView from './views/WebRtcView.vue';

import AdviseList from './views/AdviseList.vue';
import AdviseDetail from './views/components/AdviseDetail.vue';
import AdviseWrite from './views/components/AdviseWrite.vue';
import AdviseUpdate from './views/components/AdviseUpdate.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'index',
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/register',
      name: 'register',
      components: { default: Register, header: MainNavbar, footer: MainFooter },
      props: {
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/login',
      name: 'login',
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: '/search',
      name: 'search',
      components: { default: Search, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
      },
    },
    {
      path: '/adviselist',
      name: 'AdviseList',
      components: { default: AdviseList, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/advisedetail',
      name: 'AdviseDetail',
      components: { default: AdviseDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/advisewrite',
      name: 'AdviseWrite',
      components: { default: AdviseWrite, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/adviseUpdate',
      name: 'AdviseUpdate',
      components: { default: AdviseUpdate, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/webrtc',
      name: 'webrtc',
      components: { default: WebrtcView, header: undefined, footer: undefined },
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});

