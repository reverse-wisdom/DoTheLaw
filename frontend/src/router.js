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
import DictTest from './views/DictTest.vue';

import Board from './views/Board.vue';
import BoardDetail from './views/components/BoardDetail.vue';
import BoardWrite from './views/components/BoardWrite.vue';
import BoardUpdate from './views/components/BoardUpdate.vue';

import ProfileLawyer from './views/ProfileLawyer.vue';
import ProfileUser from './views/ProfileUser.vue';

import AdviseList from './views/AdviseList.vue';
import AdviseDetail from './views/components/AdviseDetail.vue';
import AdviseWrite from './views/components/AdviseWrite.vue';
import AdviseUpdate from './views/components/AdviseUpdate.vue';

// import LawTest from './views/LawTest.vue';
// import GoLawTest from './views/components/GoLawTest.vue';
import QuizCore from './views/components/lawtest/QuizCore.vue';

import Lawyermatch from './views/Lawyermatch.vue';
import LawyermatchDetail from './views/components/LawyermatchDetail.vue';

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
    },
    {
      path: '/profileLawyer',
      name: 'profileLawyer',
      components: { default: ProfileLawyer, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/profileUser',
      name: 'profileUser',
      components: { default: ProfileUser, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
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
      path: '/boardDetail',
      name: 'boardDetail',
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
      path: '/boardUpdate',
      name: 'boardUpdate',
      components: { default: BoardUpdate, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/adviseList',
      name: 'adviseList',
      components: { default: AdviseList, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/adviseDetail',
      name: 'adviseDetail',
      components: { default: AdviseDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/advisewrite',
      name: 'advisewrite',
      components: { default: AdviseWrite, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/adviseUpdate',
      name: 'adviseUpdate',
      components: { default: AdviseUpdate, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/webrtc',
      name: 'webrtc',
      components: { default: WebrtcView, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/dicttest',
      name: 'dicttest',
      components: { default: DictTest, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    // {
    //   path: '/lawtest',
    //   name: 'lawtest',
    //   components: { default: LawTest, header: MainNavbar, footer: MainFooter },
    //   props: {
    //     header: { colorOnScroll: 100 },
    //     footer: { backgroundColor: 'black' },
    //   },
    // },
    // {
    //   path: '/golawtest',
    //   name: 'golawtest',
    //   components: { default: GoLawTest, header: MainNavbar, footer: MainFooter },
    //   props: {
    //     header: { colorOnScroll: 100 },
    //     footer: { backgroundColor: 'black' },
    //   },
    // },
    {
      path: '/lawtest',
      name: 'lawtest',
      components: { default: QuizCore, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/lawyermatch',
      name: 'lawyermatch',
      components: { default: Lawyermatch, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/lawyermatchdetail',
      name: 'lawyermatchdetail',
      components: { default: LawyermatchDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 100 },
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
