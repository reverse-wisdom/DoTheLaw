<template>
  <div class="home">
    <full-page :options="options">
      <div class="section pb-0" :style="image_1" data-width="1200" data-height="730">
        <v-container fluid grid-list-sm pa-0 style="margin-top: -200px;" class="text-center">
          <v-row class="" no-gutters>
            <v-col>
              <v-img contain max-height="300" max-width="1000" :src="logo" class="logoImg" style="margin-top: 30px"></v-img>
            </v-col>
          </v-row>
          <v-row class="text-center" style="height: 10px">
            <div class="text-center searchRank">
              <v-card color="rgba(255, 255, 255, 0.1)" max-height="100px">
                <search-rank></search-rank>
              </v-card>
            </div>
          </v-row>

          <v-row class="text-center searchBox" style="margin-top: 130px;">
            <v-col class="nonPadding" cols="12" xs="10" sm="10" md="10" style="height: 0.1rem">
              <form @submit="detailSearch()" autocomplete="off" background-color="black">
                <fieldset class="text-center">
                  <v-text-field id="searchWordMain" clearable color="black" height="50px" v-model="query" style="backgroundcolor: white; ">
                    <template v-slot:label>
                      검색어를 입력해주세요!
                    </template>
                  </v-text-field>
                </fieldset>
              </form>
            </v-col>
            <v-col class="nonPadding" cols="6" xs="2" sm="2" md="2">
              <v-btn class="searchBtn mx-2" fab dark small color="rgb(21, 52, 80)" @click="detailSearch">
                <v-icon color="white" style="vertical-align: middle">
                  fas fa-search
                </v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
      </div>

      <div class="section pb-0" :style="image_2" data-width="1200" data-height="730">
        <div class="md-layout" style="padding-left: 5rem">
          <div class="md-layout-item md-medium-size-50 md-small-size-100">
            <v-card elevation="7" color="rgba(255, 255, 255, 0.0)" class="custom-card text-center">
              <span class="news-title text-center">실시간 법원/검찰 뉴스</span>
              <law-rss-news :key="componentKey"></law-rss-news>
              <button id="f5" @click="forceRerender">새로고침</button>
            </v-card>
          </div>
          <div class="md-layout-item md-medium-size-50 md-small-size-100"></div>
        </div>
      </div>

      <div class="section pb-0" :style="image_4" data-width="1200" data-height="730">
        <div class="md-layout" style="padding-left: 5rem">
          <div class="md-layout-item md-medium-size-50 md-small-size-100"></div>
          <div class="md-layout-item md-medium-size-50 md-small-size-100 text-center">
            <v-card elevation="7" color="rgba(255, 255, 255, 0.1)" class="custom-card text-center">
              <span class="main-title-solid" style="border: none;">교통사고 사례영상</span>
              <law-youtube :key="componentKey"></law-youtube>
            </v-card>
          </div>
        </div>
      </div>

      <div class="section pb-0" :style="image_3" data-width="1200" data-height="730">
        <!-- <div style="margin-left: 100px;">
          <v-row class="menu">
            <v-col cols="4" md="4">
              <input @click="moveBoard" class="button" value="자유게시판" readonly onfocus="this.blur();" />
            </v-col>
            <v-col cols="4" md="4">
              <input @click="moveMatch" class="button" value="변호사매칭" readonly onfocus="this.blur();" />
            </v-col>
            <v-col cols="4" md="4">
              <input @click="moveControversy" class="button" value="찬반토론" readonly onfocus="this.blur();" />
            </v-col>
          </v-row>

          <v-row class="menu">
            <v-col cols="4" md="4">
              <input @click="moveScourtExp" class="button" value="모의판사체험" readonly onfocus="this.blur();" />
            </v-col>
            <v-col cols="4" md="4">
              <input @click="moveEasyLaw" class="button" value="생활법령" readonly onfocus="this.blur();" />
            </v-col>
            <v-col cols="4" md="4">
              <input @click="moveLawTest" class="button" value="법상식테스트" readonly onfocus="this.blur();" />
            </v-col>
          </v-row>
        </div> -->
        <div class="main-section mb-3" style="margin-left: 100px;">
          <button class="dashbord-bold">
            <div class="icon-section">
              <i class="fa fa-users" aria-hidden="true"></i>
              <br />
              <br />
              <br />
              <p style="font-weight:bold; font-size:20px;" @click="moveMatch">변호사매칭</p>
            </div>
            <div class="detail-section"></div>
          </button>
          <button class="dashbord-light dashbord-green">
            <div class="icon-section">
              <i class="fa fa-user" aria-hidden="true"></i>
              <br />
              <br />
              <br />
              <p style="font-weight:bold; font-size:20px;" @click="moveControversy">찬반토론</p>
            </div>
            <div class="detail-section"></div>
          </button>
          <button class="dashbord-bold dashbord-orange">
            <div class="icon-section">
              <i class="fa fa-bell" aria-hidden="true"></i>
              <br />
              <br />
              <br />
              <p style="font-weight:bold; font-size:20px;" @click="moveScourtExp">모의판사체험</p>
            </div>
            <div class="detail-section"></div>
          </button>
          <button class="dashbord-light dashbord-blue">
            <div class="icon-section">
              <i class="fa fa-tasks" aria-hidden="true"></i>
              <br />
              <br />
              <br />
              <p style="font-weight:bold; font-size:20px;" @click="moveEasyLaw">생활법령</p>
            </div>
            <div class="detail-section"></div>
          </button>
          <button class="dashbord-bold dashbord-red">
            <div class="icon-section">
              <i class="fa fa-shopping-cart" aria-hidden="true"></i>
              <br />
              <br />
              <br />
              <p style="font-weight:bold; font-size:20px;" @click="moveLawTest">법상식테스트</p>
            </div>
            <div class="detail-section"></div>
          </button>
          <button class="dashbord-light dashbord-skyblue">
            <div class="icon-section">
              <i class="fa fa-comments" aria-hidden="true"></i>
              <br />
              <br />
              <br />
              <p style="font-weight:bold; font-size:20px;" @click="moveBoard">자유게시판</p>
            </div>
            <div class="detail-section"></div>
          </button>
        </div>
      </div>
    </full-page>
  </div>
</template>

<script>
import LawRssNews from './components/LawRssNews';
import LawYoutube from './components/LawYoutube';
import SearchRank from './components/SearchRank';

import FullPage from 'vue-fullpage.js';

export default {
  name: 'Index',
  components: {
    FullPage,
    LawRssNews,
    LawYoutube,
    SearchRank,
  },
  data() {
    return {
      query: '',
      componentKey: 0,
      options: {
        navigation: true,
        responsiveWidth: 900,
      },
    };
  },

  props: {
    image1: {
      type: String,
      default: require('@/assets/img/mainbg1.png'),
    },
    image2: {
      type: String,
      default: require('@/assets/img/mainbg2.png'),
    },
    image3: {
      type: String,
      default: require('@/assets/img/mainbg3.jpg'),
    },
    image4: {
      type: String,
      default: require('@/assets/img/mainbg4.png'),
    },
    logo: {
      type: String,
      default: require('@/assets/img/main1-logo.png'),
    },
  },
  destroyed() {
    $.fn.fullpage.destroy('all');
  },
  computed: {
    image_1() {
      return {
        backgroundImage: `url(${this.image1})`,
      };
    },
    image_2() {
      return {
        backgroundImage: `url(${this.image2})`,
      };
    },
    image_3() {
      return {
        backgroundImage: `url(${this.image3})`,
      };
    },
    image_4() {
      return {
        backgroundImage: `url(${this.image4})`,
      };
    },
  },
  methods: {
    forceRerender() {
      this.componentKey += 1;
    },
    detailSearch() {
      var query = document.getElementById('searchWordMain').value;
      this.$router.push({ name: 'search', query: { searchWord: query } });
    },
    moveBoard() {
      this.$router.push({ name: 'board' });
    },
    moveAdvise() {
      this.$router.push({ name: 'adviseList' });
    },
    moveMatch() {
      this.$router.push({ name: 'lawyermatch' });
    },
    moveControversy() {
      this.$router.push({ name: 'controversylist' });
    },
    moveScourtExp() {
      window.open('about:blank').location.href = 'https://i4d103.p.ssafy.io/exp/main.html';
    },
    moveEasyLaw() {
      this.$router.push({ name: 'easyLaw' });
    },
    moveLawTest() {
      this.$router.push({ name: 'lawtest' });
    },
  },
};
</script>

<style lang="scss">
// 웹폰트
@font-face {
  font-family: 'MaplestoryOTFBold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'Cafe24Ohsquare';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Cafe24Ohsquare.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'S-CoreDream-6Bold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-6Bold.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'paybooc-Bold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Bold.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
// @font-face {
//   font-family: 'GmarketSansBold';
//   src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff');
//   font-weight: normal;
//   font-style: normal;
// }
@font-face {
  font-family: 'GongGothicMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/GongGothicMedium.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
div.row.menu {
  margin-right: 0px;
}

.menu {
  @media (min-width: 1200px) {
    margin-right: 50rem !important;
  }
}
.logoImg {
  @media (min-width: 1200px) {
    margin-left: 80px !important;
  }
}
#searchWordMain {
  color: black;
}
form > fieldset > div > div.v-input__control {
  padding-left: 30px;
}
> div.v-input__slot > div.v-text-field__slot > label {
  color: black;
}
input#searchWordMain {
  font-size: 200%;
  text-align: center;
}
.title-solid {
  letter-spacing: 0.3rem;
  border: 0.2rem solid;
  font-size: 2.3rem;
  border-radius: 3rem;
  padding: 0.2rem;
  color: white;
  background-color: rgb(21, 52, 80);
  margin-left: 20rem;
  margin-right: 20rem;
  font-family: 'GongGothicMedium';

  @media (max-width: 800px) {
    letter-spacing: none;
    margin-left: 10rem;
    margin-right: 10rem;
  }
}

.searchBtn {
  margin-bottom: 10px;
}
> span.v-btn__content {
  color: #fff;
}
</style>
<style scoped>
.logo {
  width: 100%;
  height: auto;
}
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}

.home > div:nth-child(1) {
  background: gray;
}
.home > div:nth-child(2) {
  background: yellow;
}
.home > div:nth-child(3) {
  background: green;
}
.section {
  width: 100vw;
  height: 100vh;
  z-index: -1;
  padding: 0px;
}
#img2 {
  width: 100%;
  height: 100%;
}
.custom-card {
  background-color: rgba(255, 255, 255, 0.1);
  border-color: rgba(255, 255, 255, 0.1);
  height: 100%;
  padding-top: 30px;
  padding-left: 30px;
  margin-top: 30px;
  margin-right: 50px;
}
#f5 {
  border: 0.1rem solid white;
  padding: 0.35rem;
  margin-top: 1rem;
  color: white;
  border-radius: 0.5rem;
  margin-right: -40rem;
  font-size: 0.8rem;
}
#box-solid {
  box-sizing: border-box;
  border: 1px solid white;
  border-radius: 1.5rem;
  padding: 1rem;
  width: 200px;
}
.main-title-solid {
  box-sizing: content-box;
  border: 0.1rem solid white;
  font-size: 1.5rem;
  border-radius: 1.5rem;
  padding: 1rem;
  color: white;
  font-weight: bold;
}
.rank-title {
  font-size: 40px;
  color: white;
  font-weight: bold;
}

#olid {
  box-sizing: content-box;
}
#searchbar {
  border: 1px solid white;
  /* margin: auto; */
  font-size: 50px;
}

.button:hover {
  background-color: #3c99dc;
  box-shadow: 0px 15px 20px rgba(0, 0, 0, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
li {
  list-style: none;
}
input {
  color: transparent;
  text-shadow: 0 0 0 black;
}
</style>
<style lang="scss" scoped>
.searchBox {
  font-size: 50px;
  background-color: white;
  border-radius: 3rem;
}
.searchForm {
  width: 300%;
}
.searchRank {
  width: 300%;
  height: 50%;
}
.nonPadding {
  padding: 0 !important;
}
.button {
  width: 9rem;
  height: 9rem;
  font-family: 'GongGothicMedium';
  font-size: 1rem;
  text-transform: uppercase;
  text-align: center;
  letter-spacing: 0.5rem;
  color: black;
  border: 0.15rem solid black;
  border-radius: 1rem;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
}
.news-title {
  font-size: 1.2rem;
  padding-left: 3rem !important;
  padding-right: 3rem !important;
  padding-top: 0.7rem;
  padding-bottom: 0.7rem;
  color: white;
  background-color: black;
  font-weight: bold;
  // font-family: 'Nanum Gothic', sans-serif;
  letter-spacing: 0.2rem;
}
.main-section {
  width: 50%;
  margin: 0 auto;
  text-align: center;
  padding: 0px 5px;
}
.dashbord-bold {
  width: 32%;
  display: inline-block;
  background-color: rgb(68, 114, 148);
  color: #fff;
  margin-top: 50px;
}

.dashbord-light {
  width: 32%;
  display: inline-block;
  background-color: rgb(68, 114, 148);
  color: #fff;
  margin-top: 50px;
}

.icon-section i {
  font-size: 30px;
  padding: 10px;
  border: 1px solid;
  border-radius: 50%;
  margin-top: -25px;
  margin-bottom: 10px;
  background-color: #34495e;
}
.icon-section p {
  margin: 0px;
  font-size: 20px;
  padding-bottom: 10px;
}
.detail-section {
  background-color: rgb(68, 114, 148);
  padding: 5px 0px;
}
.detail-section a {
  color: #fff;
  text-decoration: none;
}
.dashbord-green .icon-section,
.dashbord-green .icon-section i {
  background-color: rgb(255, 255, 255);
  color: black;
}
.dashbord-green .detail-section {
  background-color: #34495e;
  color: black;
}
.dashbord-orange .icon-section,
.dashbord-orange .icon-section i {
  background-color: rgb(68, 114, 148);
}
.dashbord-orange .detail-section {
  background-color: rgb(68, 114, 148);
}
.dashbord-blue .icon-section,
.dashbord-blue .icon-section i {
  background-color: rgb(255, 255, 255);
  color: black;
}
.dashbord-blue .detail-section {
  background-color: #34495e;
}
.dashbord-red .icon-section,
.dashbord-red .icon-section i {
  background-color: rgb(68, 114, 148);
}
.dashbord-red .detail-section {
  background-color: rgb(68, 114, 148);
}
.dashbord-skyblue .icon-section,
.dashbord-skyblue .icon-section i {
  background-color: rgb(255, 255, 255);
  color: black;
}
.dashbord-skyblue .detail-section {
  background-color: #34495e;
}
</style>
