<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <img :src="logo" alt="logo" />
              <br />

              <form @submit="detailSearch()" onSubmit="return false;" autocomplete="off" background-color="white">
                <fieldset>
                  <v-text-field id="searchWord" v-model="query" color="cyan darken" label="판례명" placeholder="검색어 입력" loading>
                    <template v-slot:progress>
                      <v-progress-linear v-if="query" :value="progress" :color="color" absolute height="7"></v-progress-linear>
                    </template>
                  </v-text-field>
                  <button class="searchBtn" @click="detailSearch()"><i class="fa fa-search"></i></button>
                </fieldset>
              </form>

              <!-- 리스트 -->

              <v-list>
                <v-list-item-group v-model="model">
                  <v-list-item v-for="(item, i) in items" :key="i">
                    <v-list-item-icon>
                      <v-icon v-text="item.icon"></v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                      <v-list-item-title v-text="item.text"></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic">
        <!-- 테스트 영역 start-->

        <!-- 토큰값 체크 -->
        <div class="section">
          <div class="container text-center">
            <md-button class="md-info" style="margin: auto" @click="tokenTest()">토큰값 확인</md-button>

            <p>{{ testToken }}</p>
            <p>{{ $store.state.email }}</p>
            <md-button class="md-info" style="margin: auto" @click="logoutUser()">로그아웃</md-button>
            <md-button class="md-info" style="margin: auto" @click="checkCookie()">쿠키체크</md-button>
            쿠키값:
            <p>{{ cookie }}</p>
            <br />
            <md-button class="md-info" style="margin: auto" @click="moveBoard()">게시판으로이동</md-button>
          </div>
        </div>

        <div class="section">
          <div class="icon icon-success">
            <md-icon>local_post_office</md-icon>
          </div>
          <h4 class="info-title kor">실시간 법원/검찰 뉴스</h4>
          <md-button class="md-success" @click="forceRerender">새로고침</md-button>
          <!-- 뉴스 RSS파싱 컴포넌트 -->
          <RSSParser :key="componentKey" style="margin-top:-20px; padding: auto; text-center" />
        </div>

        <!-- 테스트 영역 end -->

        <!-- 임시 채우는 값들 -->
        <div class="section">
          <div class="container text-center">
            <div class="md-layout">
              <div class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center">
                <h2>WebRTC 미팅으로 바로가기</h2>
                <h4>
                  <md-button class="md-success" @click="goWebRTC">webRTC로 이동</md-button>
                </h4>
              </div>
            </div>
          </div>
        </div>
        <div class="section">
          <div class="container text-center">
            <div class="md-layout">
              <div class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center">
                <h2>Completed with examples</h2>
                <h4>
                  The kit comes with three pre-built pages to help you get started faster. You can change the text and images and you're good to go. More importantly, looking at them will give you a
                  picture of what you can built with this powerful kit.
                </h4>
              </div>
            </div>
          </div>
        </div>
        <div class="section">
          <div class="container text-center">
            <div class="md-layout">
              <div class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center">
                <h2>Completed with examples</h2>
                <h4>동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세</h4>
              </div>
            </div>
          </div>
        </div>
        <div class="section">
          <div class="container text-center">
            <div class="md-layout">
              <div class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center">
                <h2>Completed with examples</h2>
                <h4>
                  The kit comes with three pre-built pages to help you get started faster. You can change the text and images and you're good to go. More importantly, looking at them will give you a
                  picture of what you can built with this powerful kit.
                </h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import RSSParser from './components/RSSParser';
const LAWS_API_KEY = process.env.VUE_APP_LAWS_API_KEY;
import { deleteCookie } from '@/utils/cookies';

export default {
  name: 'index',
  bodyClass: 'index-page',
  props: {
    image: {
      type: String,
      default: require('@/assets/img/bigstock.jpg'),
    },
    logo: {
      type: String,
      default: require('@/assets/img/logo.png'),
    },
  },
  components: {
    RSSParser,
  },
  data() {
    return {
      query: '',
      testToken: '',
      componentKey: 0,

      laws: [],

      items: [
        {
          text: '1. 명예훼손',
        },
        {
          text: '2. 도로교통법',
        },
        {
          text: '3. 모욕죄',
        },
      ],

      model: 1,
      cookie: '',
    };
  },
  methods: {
    // RSS 뉴스 새로고침 버튼
    // :key "componentKey" 변수를 활용해 컴포넌트 강제새로고침 생성
    forceRerender() {
      this.componentKey += 1;
    },
    detailSearch() {
      var query = document.getElementById('searchWord').value;
      this.$router.push({ name: 'search', query: { searchWord: query } });
    },
    tokenTest() {
      this.testToken = this.$store.state.token;
    },
    moveBoard() {
      this.tokenTest();
      if (this.testToken != null && this.testToken != '') {
        this.$router.push('board');
      } else {
        this.$swal({
          icon: 'error',
          title: '로그인상태만 이동가능!',
        });
      }
    },
    logoutUser() {
      this.$store.commit('clearEmail');
      this.$store.commit('clearToken');
      this.$store.commit('clearNickname');
      this.$store.commit('clearPwd');
      this.$store.commit('clearName');
      localStorage.clear();
      sessionStorage.clear();

      // 쿠키날리기
      // deleteCookie(all);
      // this.deleteAllCookies();
      $cookies.keys().forEach((cookie) => $cookies.remove(cookie));
      // this.$router.push('/');
      // this.$router.go(0);
      // var auth2 = window.gapi.auth2.getAuthInstance();
      // if (auth2 != null) {
      //   auth2.sighOut(0);
      //   auth2.disconnect();
      // }

      this.$router.go(this.$router.currentRoute);
    },
    deleteAllCookies() {
      var cookies = document.cookie.split(';');

      for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf('=');
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT';
      }
    },
    checkCookie() {
      this.cookie = '';
      // console.log($cookies.keys());
      console.log($cookies.isKey(''));
      this.cookie = $cookies.keys();
    },
    goWebRTC() {
      this.$router.push('/webrtc');
    },
  },
  computed: {
    progress() {
      var query = document.getElementById('searchWord').value;
      return Math.min(100, query * 10);
    },
    color() {
      return ['error', 'warning', 'success'][Math.floor(this.progress / 40)];
    },
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`,
      };
    },
  },

  mounted() {
    // console.log(this.$cookies.get('NID'));
    // console.log(this.$cookies.keys().join('\n'));
  },
};
</script>

<style lang="scss">
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}

.md-layout {
  margin-top: 15px;
}

.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.trans {
  top: 50%;
  transform: translateY(-200%);
  transform: translateX(10%);
  color: white;
  text-align: left;
}

// 검색창 디자인

// #form-buscar > .form-group > .input-group > .form-control {
//   height: 40px;
// }
// #form-buscar > .form-group > .input-group > .input-group-btn > .btn {
//   height: 40px;
//   font-size: 16px;
//   font-weight: 300;
// }
// #form-buscar > .form-group > .input-group > .input-group-btn > .btn .glyphicon {
//   margin-right: 12px;
// }

// #form-buscar > .form-group > .input-group > .form-control {
//   font-size: 16px;
//   font-weight: 300;
// }

// #form-buscar > .form-group > .input-group > .form-control:focus {
//   border-color: #33a444;
//   outline: 0;
//   -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 1px rgba(0, 109, 0, 0.8);
//   box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 1px rgba(0, 109, 0, 0.8);
// }

// //
// fieldset {
//   position: relative;
//   display: inline-block;
//   padding: 0 0 0 40px;
//   background: #ceb980;
//   border: none;
//   border-radius: 5px;
// }

// .searchInput,
// .searchBtn {
//   position: relative;
//   width: 200px;
//   height: 50px;
//   padding: 0;
//   display: inline-block;
//   float: left;
// }

// .searchInput {
//   color: #000000;
//   z-index: 2;
//   border: 0 none;
// }
// .searchInput:focus {
//   outline: 0 none;
// }
// .searchInput:focus + .searchBtn {
//   -webkit-transform: translate(0, 0);
//   -ms-transform: translate(0, 0);
//   transform: translate(0, 0);
//   -webkit-transition-duration: 0.3s;
//   transition-duration: 0.3s;
// }
// .searchInput:focus + .searchBtn .fa {
//   -webkit-transform: translate(0px, 0);
//   -ms-transform: translate(0px, 0);
//   transform: translate(0px, 0);
//   -webkit-transition-duration: 0.3s;
//   transition-duration: 0.3s;
//   color: #fff;
// }

// .searchBtn {
//   z-index: 1;
//   width: 50px;
//   border: 0 none;
//   background: #ceb980;
//   cursor: pointer;
//   border-radius: 0 5px 5px 0;
//   -webkit-transform: translate(-50px, 0);
//   -ms-transform: translate(-50px, 0);
//   transform: translate(-50px, 0);
//   -webkit-transition-duration: 0.3s;
//   transition-duration: 0.3s;
// }

// .fa-search {
//   font-size: 1.4rem;
//   color: #29abe2;
//   z-index: 3;
//   top: 25%;
//   -webkit-transform: translate(-190px, 0);
//   -ms-transform: translate(-190px, 0);
//   transform: translate(-190px, 0);
//   -webkit-transition-duration: 0.3s;
//   transition-duration: 0.3s;
//   -webkit-transition: all 0.1s ease-in-out;
//   transition: all 0.1s ease-in-out;
// }
</style>
