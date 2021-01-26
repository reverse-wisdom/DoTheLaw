<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <img :src="logo" alt="logo" />
              <!-- <v-text-field label="검색" placeholder="검색어입력" background-color="blue" filled rounded solo solo-inverted></v-text-field>

              <v-btn color="primary" depressed elevation="23" raised>검색</v-btn> -->
              <div>
                <section class="webdesigntuts-workshop">
                  <form action="" method="">
                    <input type="search" placeholder="검색어입력" />
                    <button>검색</button>
                  </form>
                </section>
              </div>
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
            <md-button class="md-info" style="margin:auto;" @click="tokenTest()">토큰값 확인</md-button>

            <p>{{ testToken }}</p>
            <p>{{ $store.state.email }}</p>
            <md-button class="md-info" style="margin:auto;" @click="logoutUser()">로그아웃</md-button>
          </div>
        </div>

        <!-- 판례 API 테스트 -->
        <div class="section">
          <div class="container text-center">
            <form class="form" @submit="searchLaw()" onSubmit="return false;">
              <input id="searchWord" type="text" placeholder="판례명" @keydown.enter="searchLaw()" />
              <md-button class="md-info" style="margin:auto;" @click="searchLaw()">검색</md-button>
            </form>
          </div>
        </div>

        <!-- 판례 출력 테스트 -->
        <div class="container">
          <v-icon>mdi-home</v-icon>
          <v-data-table
            :headers="headers"
            :items="laws"
            :items-per-page="5"
            item-key="no"
            class="elevation-1"
            @click:row="handleClick"
            :footer-props="{
              showFirstLastPage: true,
              firstIcon: 'mdi-arrow-collapse-left',
              lastIcon: 'mdi-arrow-collapse-right',
              prevIcon: 'mdi-minus',
              nextIcon: 'mdi-plus',
            }"
          >
            <!-- v-html 사용하기위한 slot 템플릿 -->
            <template v-slot:[`item.name`]="{ item }">
              <div v-html="item.name"></div>
            </template>
          </v-data-table>
        </div>

        <!-- 모달 판례번호기반 판례상세내용 요청 테스트-->
        <div class="md-layout">
          <div class="md-layout-item md-size-33">
            <modal v-if="classicModal" @close="classicModalHide">
              <template slot="header">
                <h4 class="modal-title">판결문제목</h4>
                <md-button class="md-simple md-just-icon md-round modal-default-button" @click="classicModalHide">
                  <md-icon>clear</md-icon>
                </md-button>
              </template>

              <template slot="body">
                <table class="styled-table kor" style="width: 100%; table-layout: fixed;">
                  <span v-html="detailLaw.PrecService.판결요지._cdata"></span>
                </table>
              </template>

              <template slot="footer">
                <md-button class="md-danger md-simple" @click="classicModalHide">닫기</md-button>
              </template>
            </modal>
          </div>
        </div>

        <!-- 테스트 영역 end -->

        <!-- 임시 채우는 값들 -->
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
import axios from 'axios';
import convert from 'xml-js';
import { Modal } from '@/components';

const LAWS_API_KEY = 'dbm01049';

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
    Modal,
  },
  data() {
    return {
      query: '',
      values: [],
      classicModal: false,
      detailLaw: {},
      testToken: '',

      headers: [
        {
          text: '판례일련번호',
          align: 'start',
          value: 'no',
        },
        {
          text: '사건명',
          value: 'name',
        },

        { text: '법원명', value: 'category' },
      ],

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
    };
  },
  methods: {
    searchLaw() {
      var searchWord = document.getElementById('searchWord').value;

      axios
        .get('https://www.law.go.kr/DRF/lawSearch.do?OC=' + LAWS_API_KEY + '&target=prec&type=XML&mobileYn=Y&display=100&query=' + searchWord)
        .then(({ data }) => {
          var xml = data;
          var json = convert.xml2json(xml, { compact: true });
          let $vm = this;
          // console.log(JSON.parse(json).PrecSearch.prec[0]);
          // console.log(JSON.parse(json).PrecSearch);
          JSON.parse(json).PrecSearch.prec.forEach(function(entry) {
            $vm.laws.push({
              no: entry.판례일련번호._text,
              name: entry.사건명._cdata,
              category: entry.법원명._text,
            });
          });
          console.log(this.laws);
        })
        .catch();
    },
    handleClick(data) {
      this.classicModal = true;

      axios
        .get('https://www.law.go.kr/DRF/lawService.do?OC=' + LAWS_API_KEY + '&target=prec&type=xml&ID=' + data.no)
        .then(({ data }) => {
          var xml = data;
          var json = convert.xml2json(xml, { compact: true });
          this.detailLaw = JSON.parse(json);
          console.log(this.detailLaw.PrecService.판결요지._cdata);
        })
        .catch();
    },
    classicModalHide() {
      this.classicModal = false;
    },

    tokenTest() {
      this.testToken = this.$store.state.token;
    },
    logoutUser() {
      this.$store.commit('clearEmail');
      this.$store.commit('clearToken');
      // deleteCookie('til_auth');
      // deleteCookie('til_pwd');
      // deleteCookie('til_email');
      this.$router.push('/');
    },
  },
  computed: {
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

  mounted() {},
};
</script>
<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
@import url(https://fonts.googleapis.com/css?family=Cabin:400);

// .webdesigntuts-workshop {
//   background: #151515;
//   height: 100%;
//   position: absolute;
//   text-align: center;
//   width: 100%;
// }

.webdesigntuts-workshop:before,
.webdesigntuts-workshop:after {
  content: '';
  display: block;
  height: 1px;
  left: 50%;
  margin: 0 0 0 -400px;
  position: absolute;
  width: 800px;
}

.webdesigntuts-workshop:before {
  background: #444;
  background: linear-gradient(left, #151515, #444, #151515);
  top: 192px;
}

.webdesigntuts-workshop:after {
  background: #000;
  background: linear-gradient(left, #151515, #000, #151515);
  top: 191px;
}

.webdesigntuts-workshop form {
  background: #111;
  background: linear-gradient(#1b1b1b, #111);
  border: 1px solid #000;
  border-radius: 5px;
  box-shadow: inset 0 0 0 1px #272727;
  display: inline-block;
  font-size: 0px;
  margin: 150px auto 0;
  padding: 20px;
  position: relative;
  z-index: 1;
}

.webdesigntuts-workshop input {
  background: #222;
  background: linear-gradient(#333, #222);
  border: 1px solid #444;
  border-radius: 5px 0 0 5px;
  box-shadow: 0 2px 0 #000;
  color: #888;
  display: block;
  float: left;
  font-family: 'Cabin', helvetica, arial, sans-serif;
  font-size: 13px;
  font-weight: 400;
  height: 40px;
  margin: 0;
  padding: 0 10px;
  text-shadow: 0 -1px 0 #000;
  width: 200px;
}

.ie .webdesigntuts-workshop input {
  line-height: 40px;
}

.webdesigntuts-workshop input::-webkit-input-placeholder {
  color: #888;
}

.webdesigntuts-workshop input:-moz-placeholder {
  color: #888;
}

.webdesigntuts-workshop input:focus {
  animation: glow 800ms ease-out infinite alternate;
  background: #222922;
  background: linear-gradient(#333933, #222922);
  border-color: #393;
  box-shadow: 0 0 5px rgba(0, 255, 0, 0.2), inset 0 0 5px rgba(0, 255, 0, 0.1), 0 2px 0 #000;
  color: #efe;
  outline: none;
}

.webdesigntuts-workshop input:focus::-webkit-input-placeholder {
  color: #efe;
}

.webdesigntuts-workshop input:focus:-moz-placeholder {
  color: #efe;
}

.webdesigntuts-workshop button {
  background: #222;
  background: linear-gradient(#333, #222);
  box-sizing: border-box;
  border: 1px solid #444;
  border-left-color: #000;
  border-radius: 0 5px 5px 0;
  box-shadow: 0 2px 0 #000;
  color: #fff;
  display: block;
  float: left;
  font-family: 'Cabin', helvetica, arial, sans-serif;
  font-size: 13px;
  font-weight: 400;
  height: 40px;
  line-height: 40px;
  margin: 0;
  padding: 0;
  position: relative;
  text-shadow: 0 -1px 0 #000;
  width: 80px;
}

.webdesigntuts-workshop button:hover,
.webdesigntuts-workshop button:focus {
  background: #292929;
  background: linear-gradient(#393939, #292929);
  color: #5f5;
  outline: none;
}

.webdesigntuts-workshop button:active {
  background: #292929;
  background: linear-gradient(#393939, #292929);
  box-shadow: 0 1px 0 #000, inset 1px 0 1px #222;
  top: 1px;
}

@keyframes glow {
  0% {
    border-color: #393;
    box-shadow: 0 0 5px rgba(0, 255, 0, 0.2), inset 0 0 5px rgba(0, 255, 0, 0.1), 0 2px 0 #000;
  }
  100% {
    border-color: #6f6;
    box-shadow: 0 0 20px rgba(0, 255, 0, 0.6), inset 0 0 10px rgba(0, 255, 0, 0.4), 0 2px 0 #000;
  }
}
</style>
