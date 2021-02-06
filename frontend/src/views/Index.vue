<template>
  <div class="home">
    <full-page :options="options">
      <div class="section pb-0" :style="image_1" data-width="1200" data-height="730">
        <form @submit="detailSearch()" autocomplete="off" background-color="white">
          <fieldset id="searchbar" class="text-center">
            <v-text-field id="searchWordMain" clearable color="white" height="50px" v-model="query" style="backgroundcolor: white; width: 1500px; float: left;">
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
      </div>

      <div id="section1" class="section pb-0" :style="image_2" data-width="1200" data-height="730">
        <!-- <div class="kor mt-15">
          <span id="title-solid">실시간 법원/검찰 뉴스</span>
          <law-rss-news :key="componentKey" style="text-center"></law-rss-news>
          <button id="f5" class="" @click="forceRerender">새로고침</button>
        </div> -->
        <v-row>
          <v-col>
            <span class="title-solid">실시간 법원/검찰 뉴스</span>
            <law-rss-news :key="componentKey" style="text-center"></law-rss-news>
            <button id="f5" class="" @click="forceRerender">새로고침</button>
          </v-col>
          <v-col>
            <!-- 검색어 순위 컴포넌트 -->
            <span class="title-solid">검색어 순위</span>
            <search-rank></search-rank>
          </v-col>
        </v-row>
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
            <input type="text" class="button" value="공사중" readonly onfocus="this.blur();" />
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
        // paddingTop: '30px',
        navigation: true,
        // sectionsColor: ['#f2f2f2', '#4BBFC3', '#7BAABE'],
        // image: [require('@/assets/img/mainbg1.png'), require('@/assets/img/mainbg2.jpg'), require('@/assets/img/mainbg3.jpg')],
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
// #fp-nav ul li a span,
// .fp-slidesNav ul li a span {
//   background: rgba(236, 206, 140, 0.356) !important;
//   /* height: 20px !important;
//   width: 20px !important;
//   margin: -2px 0 0 -2px !important; */
// }
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
  font-size: 50px;
  border-radius: 1.5rem;
  padding: 1rem;
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
