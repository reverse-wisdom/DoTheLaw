<template>
  <div class="home">
    <full-page :options="options">
      <div class="section pb-0" :style="image_1" data-width="1200" data-height="730">
        <v-container fluid grid-list-sm pa-0 style="padding-left: 5rem !important; margin-top: -200px;">
          <v-row class="text-center" no-gutters>
            <v-col>
              <v-img contain max-height="300" max-width="1000" :src="logo" style="margin-left: 30px;"></v-img>
            </v-col>
          </v-row>
          <v-row class="text-center searchBox">
            <v-col class="nonPadding" cols="12" xs="10" sm="10" md="10" style="height: 0.1rem">
              <form @submit="detailSearch()" autocomplete="off" background-color="black">
                <fieldset class="text-center">
                  <v-text-field id="searchWordMain" clearable color="black" height="50px" v-model="query" style="backgroundcolor: white; ">
                    <template v-slot:label>
                      <strong>검색어</strong>
                      를입력해주세요!
                      <v-icon color="black" style="vertical-align: middle">
                        mdi-file-find
                      </v-icon>
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

          <v-row class="text-center" style="height: 10px">
            <div class="text-center searchRank">
              <v-card color="rgba(255, 255, 255, 0.1)" max-height="100px">
                <search-rank></search-rank>
              </v-card>
            </div>
          </v-row>
        </v-container>
      </div>

      <div class="section pb-0" :style="image_2" data-width="1200" data-height="730">
        <div class="md-layout" style="padding-left: 5rem">
          <div class="md-layout-item md-medium-size-50 md-small-size-100">
            <v-card elevation="7" color="rgba(255, 255, 255, 0.1)" class="custom-card text-center">
              <span class="main-title-solid text-center">실시간 법원/검찰 뉴스</span>
              <button id="f5" @click="forceRerender">새로고침</button>
              <law-rss-news :key="componentKey"></law-rss-news>
            </v-card>
          </div>
          <div class="md-layout-item md-medium-size-50 md-small-size-100">
            <v-card elevation="7" color="rgba(255, 255, 255, 0.1)" class="custom-card text-center">
              <span class="main-title-solid text-center">관련 동영상</span>
              <br />
              <button id="f5" @click="forceRerender">새로고침</button>
              <law-youtube :key="componentKey"></law-youtube>
            </v-card>
          </div>
        </div>
      </div>

      <div class="section pb-0" :style="image_3" data-width="1200" data-height="730">
        <div>
          <v-row class="menu">
            <v-col cols="6" md="6" class="text-right">
              <input @click="moveBoard" class="button" value="자유게시판" readonly onfocus="this.blur();" />
            </v-col>
            <v-col cols="6" md="6">
              <input @click="moveMatch" class="button" value="변호사매칭" readonly onfocus="this.blur();" />
            </v-col>
          </v-row>

          <v-row class="menu">
            <v-col cols="6" md="6" class="text-right">
              <input @click="moveControversy" class="button" value="찬반토론" readonly onfocus="this.blur();" />
            </v-col>
            <v-col cols="6" md="6">
              <input @click="moveScourtExp" class="button" value="모의판사체험" readonly onfocus="this.blur();" />
            </v-col>
          </v-row>

          <v-row class="menu">
            <v-col cols="6" md="6" class="text-right">
              <input @click="moveEasyLaw" class="button" value="생활법령" readonly onfocus="this.blur();" />
            </v-col>
            <v-col cols="6" md="6">
              <input @click="moveLawTest" class="button" value="법상식테스트" readonly onfocus="this.blur();" />
            </v-col>
          </v-row>
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
    logo: {
      type: String,
      default: require('@/assets/img/main1-logo.png'),
    },
  },
  destroyed() {
    $.fn.fullpage.destroy('all');
  },
  computed: {
    image_2() {
      return {
        backgroundImage: `url(${this.image2})`,
      };
    },
    image_1() {
      return {
        backgroundImage: `url(${this.image1})`,
      };
    },
    image_3() {
      return {
        backgroundImage: `url(${this.image3})`,
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

div.row.menu {
  margin-right: 0px;
}

.menu {
  @media (min-width: 1200px) {
    margin-right: 50rem !important;
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
  letter-spacing: 5px;
  border: 0.2rem solid;
  font-size: 2.3rem;
  border-radius: 3rem;
  padding: 0.2rem;
  color: white;
  background-color: rgb(21, 52, 80);
  // font-weight: bold;
  margin-left: 20rem;
  margin-right: 20rem;
  font-family: 'NEXON Lv1 Gothic OTF';
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
  border: 0.2rem solid white;
  padding: 0.5rem;
  margin-top: 1rem;
  color: white;
  border-radius: 1rem;
  margin-left: 1rem;
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
  border: 0.2rem solid white;
  font-size: 2.3rem;
  border-radius: 1.5rem;
  padding: 1rem;
  color: white;
  font-weight: bold;
  margin-left: 50px;
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
  // border: 0.2rem solid;
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
  width: 10rem;
  height: 10rem;
  font-family: 'MaplestoryOTFBold';
  font-size: 1rem;
  text-transform: uppercase;
  text-align: center;
  letter-spacing: 0.5rem;
  color: black;

  border: 0.18rem solid black;
  border-radius: 1rem;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
}
</style>
