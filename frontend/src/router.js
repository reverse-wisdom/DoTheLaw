import Vue from 'vue';
import Router from 'vue-router';
import Index from './views/Index.vue';
import store from '@/store/index';

import Login from './views/Login.vue';

import Search from './views/Search.vue';
import MainNavbar from './layout/MainNavbar.vue';
import MainFooter from './layout/MainFooter.vue';

import RegisterUser from './views/RegisterUser.vue';
import RegisterLawyer from './views/RegisterLawyer.vue';
import RegisterIndex from './views/RegisterIndex.vue';

import WebrtcView from './views/WebRtcView.vue';
import DictTest from './views/DictTest.vue';

import Board from './views/Board.vue';
import BoardDetail from './views/components/board/BoardDetail.vue';
import BoardWrite from './views/components/board/BoardWrite.vue';
import BoardUpdate from './views/components/board/BoardUpdate.vue';

import ProfileLawyer from './views/ProfileLawyer.vue';
import ProfileLawyerUpdate from './views/components/ProfileLawyerUpdate.vue';
import ProfileUser from './views/ProfileUser.vue';
import ProfileUserUpdate from './views/components/ProfileUserUpdate.vue';

import AdviseDetail from './views/components/advise/AdviseDetail.vue';
import AdviseWrite from './views/components/advise/AdviseWrite.vue';
import AdviseUpdate from './views/components/advise/AdviseUpdate.vue';
import AdviseMe from './views/components/advise/AdviseMe.vue';
import AdviseLawyer from './views/components/advise/AdviseLawyer.vue';

import QuizCore from './views/components/lawtest/QuizCore.vue';

import EasyLawView from './views/EasyLawView.vue';

import Lawyermatch from './views/Lawyermatch.vue';
import LawyermatchDetail from './views/components/LawyermatchDetail.vue';

import ControversyList from './views/ControversyList.vue';
import ControversyDetail from '@/views/components/controversy/ControversyDetail';

import VueSweetalert2 from 'vue-sweetalert2';
const options = {
  confirmButtonColor: '#41b882',
  cancelButtonColor: '#ff7674',
};
Vue.use(VueSweetalert2, options); // alert API

Vue.use(Router);

const router = new Router({
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
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/registeruser',
      name: 'registeruser',
      components: { default: RegisterUser, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/registerlawyer',
      name: 'registerlawyer',
      components: { default: RegisterLawyer, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/login',
      name: 'login',
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/search',
      name: 'search',
      components: { default: Search, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/profileLawyer',
      name: 'profileLawyer',
      components: { default: ProfileLawyer, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/profileLawyer/update',
      name: 'profileLawyerUpdate',
      components: { default: ProfileLawyerUpdate, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/profileUser',
      name: 'profileUser',
      components: { default: ProfileUser, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/profileUser/update',
      name: 'profileUserUpdate',
      components: { default: ProfileUserUpdate, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/board',
      name: 'board',
      components: { default: Board, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/boardDetail',
      name: 'boardDetail',
      components: { default: BoardDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/boardWrite',
      name: 'boardWrite',
      components: { default: BoardWrite, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/boardUpdate',
      name: 'boardUpdate',
      components: { default: BoardUpdate, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },

    {
      path: '/adviseDetail',
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
      meta: { auth: true },
    },
    {
      path: '/adviseUpdate',
      name: 'AdviseUpdate',
      components: { default: AdviseUpdate, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/adviseme',
      name: 'AdviseMe',
      components: { default: AdviseMe, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/adviselawyer',
      name: 'AdviseLawyer',
      components: { default: AdviseLawyer, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/webrtc',
      name: 'webrtc',
      components: { default: WebrtcView, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/dicttest',
      name: 'dicttest',
      components: { default: DictTest, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/lawtest',
      name: 'lawtest',
      components: { default: QuizCore, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 10 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/lawyermatch',
      name: 'lawyermatch',
      components: { default: Lawyermatch, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/lawyermatchdetail',
      name: 'lawyermatchdetail',
      components: { default: LawyermatchDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/controversylist',
      name: 'controversylist',
      components: { default: ControversyList, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
    },
    {
      path: '/controversydetail',
      name: 'controversydetail',
      components: { default: ControversyDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
      meta: { auth: true },
      // 로그인 이전에  원하는 페이지 이동 막기 아래 코드 추가하면됨
      // meta: { auth: true },
    },
    {
      path: '/easyLaw',
      name: 'easyLaw',
      components: { default: EasyLawView, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
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

router.beforeEach((to, from, next) => {
  if (to.meta.auth && !store.getters.isLogin) {
    Vue.swal({
      icon: 'error',
      position: 'top-end',
      title: '로그인후 사용가능한 서비스입니다.!!',
      showConfirmButton: false,
      timer: 1500,
    });
    next('/login');
    return;
  }
  next();
});

export default router;
