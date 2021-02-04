<template>
  <div class="home">
    <full-page :options="options">
      <div class="section pb-0" :style="image_1" data-width="1200" data-height="730">
        <form @submit="detailSearch()" onSubmit="return false;" autocomplete="off" background-color="white">
          <fieldset id="searchbar">
            <v-text-field id="searchWord" v-model="query" style="backgroundcolor:white;" label="판례명" placeholder="검색어 입력">
              <template v-slot:progress>
                <v-progress-linear v-if="query" :value="progress" :color="color" absolute height="7"></v-progress-linear>
              </template>
            </v-text-field>
          </fieldset>
        </form>
      </div>

      <div id="section1" class="section pb-0" :style="image_2" data-width="1200" data-height="730">
        <div class="kor mt-15">
          <span id="title-solid">실시간 법원/검찰 뉴스</span>

          <div class="md-layout">
            <!-- 뉴스 업데이트한(가져온) 시간 -->
            <div v-if="loadCheck">
              <div class="list-type">
                <ol id="olid" style="list-style: none;">
                  <!-- 파싱한 데이터중 7개의 뉴스제목과 링크를 들고옴 -->
                  <div class="mt-10" v-for="index in 7" :key="index">
                    <a id="box-solid" :href="news[index].link" target="_blank">{{ news[index].title }}</a>
                  </div>
                </ol>
              </div>
            </div>
            <div v-else class="md-layout-item md-size-10 mx-auto">
              <br />
              <br />
              <br />
              <circle8></circle8>
            </div>
          </div>
          <button id="f5" class="" @click="forceRerender">새로고침</button>
        </div>
      </div>

      <div id="section1" class="section pb-0" :style="image_3" data-width="1200" data-height="730">
        <div class="row" style="padding-top: 100px;">
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
            <input class="button" value="공사중" readonly onfocus="this.blur();" />
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
// @ is an alias to /src
import { Circle8 } from 'vue-loading-spinner';
import FullPage from 'vue-fullpage.js';
import axios from 'axios';

export default {
  name: 'Main',
  components: {
    FullPage,
    Circle8,
  },
  data() {
    return {
      query: '',
      news: [{ title: '', link: '' }],
      loadCheck: false,
      refresh_time:
        '업데이트 시간: ' +
        new Date().getFullYear() +
        '/' +
        (new Date().getMonth() + 1) +
        '/' +
        new Date().getDate() +
        ' 시간:' +
        new Date().getHours() +
        '시' +
        new Date().getMinutes() +
        '분' +
        new Date().getSeconds() +
        '초',
      componentKey: 0,
      options: {
        // paddingTop: '30px',
        navigation: true,
        // sectionsColor: ['#f2f2f2', '#4BBFC3', '#7BAABE'],
        image: [require('@/assets/img/mainbg1.png'), require('@/assets/img/mainbg2.jpg'), require('@/assets/img/mainbg3.jpg')],
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
    color() {
      return ['error', 'warning', 'success'][Math.floor(this.progress / 40)];
    },
    progress() {
      var query = document.getElementById('searchWord').value;
      return Math.min(100, query * 10);
    },
  },
  created() {
    // RSS 뉴스 불러오기

    // 파싱
    // const { data } = newsParsing(); // 왜안될까
    // console.log(data);

    this.loadCheck = false;
    axios.get('/api/rss/news').then(({ data }) => {
      if (data['items']) {
        data = data['items'];
        for (let i = 0; i < data.length; i++) {
          this.news.push({
            title: data[i].title,
            link: data[i].link,
          });
          this.loadCheck = true;
          console.log('rss 로딩 완료');
        }
      }
    });
  },
  methods: {
    forceRerender() {
      this.componentKey += 1;
    },
    detailSearch() {
      var query = document.getElementById('searchWord').value;
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
  },
};
</script>
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
.list-type a {
  text-decoration: none;
  color: white !important;
  font-size: 17px;
  font-family: 'Nanum Gothic', sans-serif;
}
/* #img2 {
  filter: brightness(60%);
} */
a + h2 {
  color: aliceblue;
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
#title-solid {
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
