<template>
  <div class="home">
    <full-page :options="options">
      <div class="section pb-0" :style="image_1" data-width="1200" data-height="730">
        <form @submit="detailSearch()" autocomplete="off" background-color="white" style="padding-top:50px">
          <fieldset id="searchbar" class="text-center">
            <v-text-field id="searchWordMain" clearable color="white" height="50px" v-model="query" style="margin-top:50px; backgroundcolor: white; width: 1500px; float: left;">
              <template v-slot:label>
                <strong>검색어</strong>
                를입력해주세요!
                <v-icon color="white" style="vertical-align: middle">
                  mdi-file-find
                </v-icon>
              </template>
            </v-text-field>
            <v-btn depressed x-large color="primary" @click="detailSearch">
              검색
            </v-btn>
          </fieldset>
        </form>
        <div class="text-center" style="margin-left:30px; margin-top:30px">
          <v-card style="text-align:center; margin: 0 auto;" color="rgba(255, 255, 255, 0.1)" width="50%" height="200px">
            <search-rank class="text-center"></search-rank>
          </v-card>
        </div>
      </div>

      <div id="section1" class="section pb-0" :style="image_2" data-width="1200" data-height="730">
        <div class="md-layout ">
          <div class="md-layout-item md-medium-size-100 md-small-size-100 ">
            <v-card elevation="7" color="rgba(255, 255, 255, 0.1)" class="custom-card text-center">
              <span class="title-solid text-center">실시간 법원/검찰 뉴스</span>
              <law-rss-news :key="componentKey"></law-rss-news>
              <button id="f5" @click="forceRerender">새로고침</button>
            </v-card>
          </div>
        </div>
      </div>

      <div id="section1" class="section pb-0" :style="image_3" data-width="1200" data-height="730">
        <div class="row" style="padding-top: 100px">
          <li class="col-2">
            <input @click="moveBoard" class="button" value="자유게시판" readonly onfocus="this.blur();" />
          </li>
          <li class="col-2">
            <input @click="moveWebRTC" class="button" value="WebRTC" readonly onfocus="this.blur();" />
          </li>
          <li class="col-2">
            <input @click="moveHome" type="text" class="button" value="HOME" readonly onfocus="this.blur();" />
          </li>
          <div class="col-5"></div>

          <li class="col-2">
            <input @click="moveSearch" class="button" value="판례필터TEST" readonly onfocus="this.blur();" />
          </li>
          <li class="col-2">
            <input @click="moveMatch" class="button" value="변호사매칭" readonly onfocus="this.blur();" />
          </li>
          <li class="col-2">
            <input @click="moveControversy" class="button" value="찬반토론" readonly onfocus="this.blur();" />
          </li>
          <div class="col-5"></div>

          <li class="col-2">
            <input @click="moveScourtExp" class="button" value="모의판사체험" readonly onfocus="this.blur();" />
          </li>
        </div>
      </div>
    </full-page>
  </div>
</template>

<script>
import LawRssNews from './components/LawRssNews';
import SearchRank from './components/SearchRank';

import FullPage from 'vue-fullpage.js';

export default {
  name: 'Index',
  components: {
    FullPage,
    LawRssNews,
    SearchRank,
  },
  data() {
    return {
      query: '',
      componentKey: 0,
      options: {
        navigation: true,
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
    moveWebRTC() {
      this.$router.push({ name: 'webrtc' });
    },
    moveSearch() {
      this.$router.push({ name: 'dicttest' });
    },
    moveHome() {
      this.$router.push({ name: 'index' });
    },
    moveMatch() {
      this.$router.push({ name: 'lawyermatch' });
    },
    moveControversy() {
      this.$router.push({ name: 'controversylist' });
    },
    moveScourtExp() {
      location.href = 'https://i4d103.p.ssafy.io/exp/main.html';
    },
  },
};
</script>

<style lang="scss">
#searchWordMain {
  color: white;
}
#searchbar > div > div > div.v-input__slot > div > label {
  color: white;
}
input#searchWordMain {
  font-size: 200%;
  text-align: center;
}
</style>
<style scoped>
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
#section1 {
  padding-left: 7rem;
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
  border: 1px solid white;
  padding: 1rem;
  margin-top: 3rem;
  color: white;
  border-radius: 1rem;
  margin-left: 10rem;
}
#box-solid {
  box-sizing: border-box;
  border: 1px solid white;
  border-radius: 1.5rem;
  padding: 1rem;
  width: 200px;
}
.title-solid {
  box-sizing: content-box;
  border: 5px solid white;
  font-size: 40px;
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
  margin: 20rem 10rem 0 10rem;
  font-size: 50px;
}
.button {
  width: 70%;
  height: 120px;
  font-family: 'Roboto', sans-serif;
  font-size: 15px;
  text-transform: uppercase;
  text-align: center;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: black;

  border: 1px solid black;
  border-radius: 4px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
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
