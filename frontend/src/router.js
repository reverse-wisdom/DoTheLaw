import Vue from 'vue';
import Router from 'vue-router';
import Index from './views/Index.vue';

import Login from './views/Login.vue';
import Search from './views/Search.vue';
import MainNavbar from './layout/MainNavbar.vue';
import MainFooter from './layout/MainFooter.vue';

import RegisterUser from './views/RegisterUser.vue';
import RegisterLawyer from './views/RegisterLawyer.vue';
import RegisterIndex from './views/RegisterIndex.vue';

import WebrtcView from './views/WebRtcView.vue';

import Board from './views/Board.vue';
import BoardDetail from './views/components/BoardDetail.vue';
import BoardWrite from './views/components/BoardWrite.vue';

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
      path: '/registerindex',
      name: 'RegisterIndex',
      components: { default: RegisterIndex, header: MainNavbar, footer: MainFooter },
      props: {
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/registeruser',
      name: 'registeruser',
      components: { default: RegisterUser, header: MainNavbar, footer: MainFooter },
      props: {
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/registerlawyer',
      name: 'registerlawyer',
      components: { default: RegisterLawyer, header: MainNavbar, footer: MainFooter },
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
      path: '/board',
      name: 'board',
      components: { default: Board, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/boarddetail',
      name: 'boarddetail',
      components: { default: BoardDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/boardWrite',
      name: 'boardWrite',
      components: { default: BoardWrite, header: MainNavbar, footer: MainFooter },
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
