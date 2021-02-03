<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <v-card class="mx-auto" color="#ECEFF1">
        <div class="md-layout">
          <div class="md-layout-item">
            <div class="image-wrapper">
              <div class="brand">
                <!-- <img :src="logo" alt="logo" /> -->
                <v-img class="white--text align-end" :src="logo"></v-img>
                <br />
              </div>
            </div>
          </div>
        </div>
      </v-card>
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic">
        <!-- 테스트 영역 start-->
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

        <!-- 검색어 순위 컴포넌트 -->
        <search-rank></search-rank>

        <!-- 토큰값 체크 -->
        <div class="section">
          <div class="container text-center">
            <md-button class="md-info" style="margin: auto" @click="tokenTest()">토큰값 확인</md-button>

            <p>{{ testToken }}</p>
            <p>{{ $store.state.email }}</p>
            <p>{{ $store.state.role }}</p>

            <md-button class="md-info" style="margin: auto" @click="checkCookie()">쿠키체크</md-button>
            쿠키값:
            <p>{{ cookie }}</p>
            <br />
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
import SearchRank from './components/SearchRank';
export default {
  name: 'index',
  bodyClass: 'index-page',
  props: {
    image: {
      type: String,
      default: require('@/assets/img/law-wallpapers-hd-1080p-1920x1080-desktop-02.jpg'),
    },
    logo: {
      type: String,
      default: require('@/assets/img/logo.png'),
    },
  },
  components: {
    RSSParser,
    SearchRank,
  },
  data() {
    return {
      query: '',
      testToken: '',
      componentKey: 0,

      laws: [],

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
      console.log(this.$store.state);
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
      $cookies.keys().forEach((cookie) => $cookies.remove(cookie));
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
</style>
