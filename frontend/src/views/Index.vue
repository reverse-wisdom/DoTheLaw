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
      </v-card>
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
            <p>{{ $store.state.uuid }}</p>

            <md-button class="md-info" style="margin: auto" @click="checkCookie()">쿠키체크</md-button>
            쿠키값:
            <p>{{ cookie }}</p>
            <br />
          </div>
        </div>

        <!-- 구글 지도 테스트 -->
        <md-field>
          <label>주소입력</label>
          <md-input id="address" type="text" ref="address" v-model="address"></md-input>
        </md-field>
        <md-button class="md-info" style="margin: auto" @click="searchMap">주소로검색</md-button>
        <div id="map" ref="map" style="width: 100%; height: 300px; margin: auto;"></div>
        <!-- end -->

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
const GOOGLE_MAP_KEY = 'AIzaSyCcSBj7dF4tkNfeV7U2YzwdAupmh2GYpoc';
import axios from 'axios';
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

      // 구글 지도관련
      address: null,
      map: null,
      mapState: window.mapState,
      multi: {
        lat: 37.5665734,
        lng: 126.978179,
      },
    };
  },

  // mounted를 통해 지도 로딩 체크
  mounted() {
    console.log(this.mapState.initMap);
    console.log('123123');
    if (this.mapState.initMap) {
      console.log('지도 로딩완료');
      this.map = new window.google.maps.Map(document.getElementById('map'), {
        center: this.multi,
        zoom: 12,
      });
      new window.google.maps.Marker({
        position: this.multi,
        map: this.map,
        icon: require('@/assets/building.png'),
      });
    }
  },

  watch: {
    // watch를 통해 mounted가 실패하더라도 다시호출함 지도가 랜더링 안되는 현상 방지함
    'mapState.initMap'(value) {
      if (value) {
        if (this.mapState.initMap) {
          this.map = new window.google.maps.Map(document.getElementById('map'), {
            center: this.multi,
            zoom: 12,
          });
          new window.google.maps.Marker({
            position: this.multi,
            map: this.map,
            icon: require('@/assets/building.png'),
          });
        }
        console.log('load by watch');
      }
    },
  },
  methods: {
    // 구글 지도 지오코드
    searchMap() {
      var query = this.address;
      axios
        .get('https://maps.googleapis.com/maps/api/geocode/json?key=' + GOOGLE_MAP_KEY + '&address=' + query)
        .then(({ data }) => {
          let lat = data.results[0].geometry.location.lat;
          let lng = data.results[0].geometry.location.lng;
          this.map = new window.google.maps.Map(document.getElementById('map'), {
            center: {
              lat,
              lng,
            },
            zoom: 18,
          });
          new window.google.maps.Marker({
            label: query,
            position: {
              lat,
              lng,
            },
            map: this.map,
          });
        })
        .catch();
    },

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
