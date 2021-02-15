<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised paybooc">
      <div class="section profile-content paybooc">
        <h2 class="title-solid text-center">
          판례검색
        </h2>
        <br />
        <v-row justify="center" style="margin : 5px">
          <v-col cols="6" md="4">
            <form @submit="searchLaw()" onSubmit="return false;">
              <v-toolbar dense>
                <v-text-field hide-details single-line id="searchWord" placeholder="판례명" v-model="search"></v-text-field>
                <v-btn icon @click="searchLaw()">
                  <i class="material-icons">search</i>
                </v-btn>
              </v-toolbar>
            </form>
            <br />

            <v-card class="mx-auto" tile elevation="5">
              <v-subheader>검색기록</v-subheader>
              <hr />
              <v-list-item v-for="(word, i) in history" :key="i">
                <v-list-item-content>
                  <v-list-item-title class="hvr-underline-from-center" @click="historySearch(word)" style="height: 30px;">{{ i + 1 }}.&nbsp; {{ word }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-card>

            <br />
            <v-card class="mx-auto " max-width="auto" tile elevation="5">
              <v-subheader>판례목록</v-subheader>
              <hr />
              <v-list style="max-height: 400px" class="overflow-y-auto">
                <template v-for="content in contents">
                  <v-list-item :key="content.no">
                    <v-list-item-content>
                      <v-list-item-title v-html="content.name" @click="detailJudgment(content.no)" style="height: 30px;" class="hvr-underline-from-center"></v-list-item-title>
                    </v-list-item-content>
                    <v-btn icon @click="detailJudgment(content.no)">
                      <v-icon>source</v-icon>
                    </v-btn>
                  </v-list-item>
                </template>
              </v-list>
            </v-card>
          </v-col>
          <v-col cols="12" md="8" class="mr">
            <v-card class="pa-2 overflow-y-auto" max-height="1024" max-width="1400" style="margin : 5px" outlined tile elevation="5">
              <template v-if="render">
                <h1 v-html="judgment.PrecService.사건명" class="text-center font-weight-bold display-1"></h1>
                <v-simple-table>
                  <tbody>
                    <tr>
                      <td>선고일자</td>
                      <td>{{ judgment.PrecService.선고일자 }}</td>
                    </tr>
                    <tr>
                      <td>법원명</td>
                      <td>{{ judgment.PrecService.법원명 }}</td>
                    </tr>
                    <tr>
                      <td>사건종류명</td>
                      <td>{{ judgment.PrecService.사건종류명 }}</td>
                    </tr>
                    <tr>
                      <td>사건종류코드</td>
                      <td>{{ judgment.PrecService.사건종류코드 }}</td>
                    </tr>
                  </tbody>
                </v-simple-table>

                <div class="radiobtn">
                  <input v-model="radio" :value="judgment.PrecService.판결요지" type="radio" id="판결요지" checked />
                  <label for="판결요지">판결요지</label>
                </div>

                <div class="radiobtn">
                  <input v-model="radio" :value="judgment.PrecService.판례내용" type="radio" id="판례내용" />
                  <label for="판례내용">판례내용</label>
                </div>

                <div class="radiobtn">
                  <input v-model="radio" :value="judgment.PrecService.참조조문" type="radio" id="참조조문" />
                  <label for="참조조문">참조조문</label>
                </div>

                <!-- <p v-html="radio"></p> -->
                <v-expand-transition>
                  <div v-show="radio">
                    <v-divider></v-divider>
                    <v-card-text>
                      <p v-html="radio"></p>
                    </v-card-text>
                  </div>
                </v-expand-transition>
              </template>

              <div class="container text-center"></div>
            </v-card>
          </v-col>
        </v-row>
        <br />
        <div class="text-center">
          <md-button class="md-info" style="margin: 5px" @click="moveMatch">
            <i class="material-icons">search</i>
            변호사찾기
          </md-button>
          <md-button class="md-info" style="margin: 5px" @click="moveBoard">
            <i class="material-icons">search</i>
            커뮤니티에 검색
          </md-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { lawService, lawSearch, lawDict } from '@/api/service';

export default {
  name: 'search',
  bodyClass: 'profile-page',
  data() {
    return {
      laws: [],
      contents: [],
      search: null,
      judgment: {},
      render: false,
      judgment_detail: '',
      radio: false,
      dict: [],
      history: [],
      show1: false,
      show2: false,
      show3: false,
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/book.png'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  created() {
    this.search = this.$route.query.searchWord;
    this.searchLaw();
  },
  async mounted() {
    const { data } = await lawDict();
    for (let i = 0; i < data.length; i++) {
      this.dict.push({
        word: data[i].word,
        mean: String.raw`${data[i].mean}`.replace(/\\n/g, '\n'),
      });
    }
  },
  methods: {
    matchExact(r, str) {
      var match = str.match(r);
      return match && str === match[0];
    },

    historySearch(word) {
      this.search = word;
      this.searchLaw();
    },
    async searchLaw() {
      this.contents = [];
      const { data } = await lawSearch(this.search);
      for (var i = 0; i < data.PrecSearch.prec.length; i++) {
        this.contents.push({
          no: data.PrecSearch.prec[i].판례일련번호,
          name: data.PrecSearch.prec[i].사건명,
          category: data.PrecSearch.prec[i].법원명,
        });
      }
      if (!this.history.includes(this.search)) {
        this.history.push(this.search);
      }
      this.detailJudgment(this.contents[0].no);
    },

    async detailJudgment(ID) {
      this.render = false;
      this.judgment = {};
      const { data } = await lawService(ID);
      this.render = true;
      this.judgment = data;

      this.judgment.PrecService.판결요지 = this.judgment.PrecService.판결요지.replace(/<(\/a|a)([^>]*)>/gi, '');
      this.judgment.PrecService.참조조문 = this.judgment.PrecService.참조조문.replace(/<(\/a|a)([^>]*)>/gi, '');
      this.judgment.PrecService.판례내용 = this.judgment.PrecService.판례내용.replace(/<(\/a|a)([^>]*)>/gi, '');

      var count = 0;

      this.dict.forEach((element) => {
        var regEx = new RegExp(element.word, 'g');

        this.judgment.PrecService.판결요지 = this.judgment.PrecService.판결요지.replace(regEx, '#obj-' + count + '#');
        this.judgment.PrecService.참조조문 = this.judgment.PrecService.참조조문.replace(regEx, '#obj-' + count + '#');
        this.judgment.PrecService.판례내용 = this.judgment.PrecService.판례내용.replace(regEx, '#obj-' + count + '#');
        count++;
      });
      count = 0;
      this.dict.forEach((element) => {
        var regEx = new RegExp('#obj-' + count + '#', 'g');

        this.judgment.PrecService.판결요지 = this.judgment.PrecService.판결요지.replace(regEx, `<a data-title="${element.mean}">${element.word}</a>`);
        this.judgment.PrecService.참조조문 = this.judgment.PrecService.참조조문.replace(regEx, `<a data-title="${element.mean}">${element.word}</a>`);
        this.judgment.PrecService.판례내용 = this.judgment.PrecService.판례내용.replace(regEx, `<a data-title="${element.mean}">${element.word}</a>`);
        count++;
      });
      this.radio = this.judgment.PrecService.판결요지;
    },
    moveMatch() {
      this.$router.push({ name: 'lawyermatch' });
    },
    moveBoard() {
      this.$router.push({ name: 'board' });
    },
  },
};
</script>
<style lang="scss">
[data-title]:hover:after {
  opacity: 1;
  transition: all 0.1s ease 0.5s;
  visibility: visible;
}
[data-title]:after {
  content: attr(data-title);
  background-color: grey;
  color: white;
  font-size: 110%;
  position: absolute;
  padding: 1px 5px 2px 5px;
  bottom: -1.6em;
  left: 100%;
  white-space: nowrap;
  box-shadow: 1px 1px 3px #222222;
  opacity: 0;
  border: 1px solid #111111;
  z-index: 99999;
  visibility: hidden;
  white-space: pre;
}
[data-title] {
  position: relative;
}
</style>
<style lang="scss" scoped>
// hr 설정
.div-hr {
  width: 80%;
}
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.paybooc {
  font-family: 'paybooc-Bold';
}
.mr {
  padding: 5px;
}

// v-data-table hover 스타일 해제
tbody {
  tr:hover {
    background-color: transparent !important;
  }
}

// 라디오 버튼 스타일
$accentcolor: #a8a6a3;
$lightcolor: #fff;
$darkcolor: #444;

.radiobtn {
  position: relative;
  display: block;
  label {
    display: block;
    background: lighten($accentcolor, 30%);
    color: $darkcolor;
    border-radius: 5px;
    padding: 10px 20px;
    border: 2px solid lighten($accentcolor, 20%);
    margin-bottom: 5px;
    cursor: pointer;
    &:after,
    &:before {
      content: '';
      position: absolute;
      right: 11px;
      top: 11px;
      width: 20px;
      height: 20px;
      border-radius: 3px;
      background: lighten($accentcolor, 15%);
    }
    &:before {
      background: transparent;
      transition: 0.1s width cubic-bezier(0.075, 0.82, 0.165, 1) 0s, 0.3s height cubic-bezier(0.075, 0.82, 0.165, 2) 0.1s;
      z-index: 2;
      overflow: hidden;
      background-repeat: no-repeat;
      background-size: 13px;
      background-position: center;
      width: 0;
      height: 0;
      background-image: url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxNS4zIDEzLjIiPiAgPHBhdGggZmlsbD0iI2ZmZiIgZD0iTTE0LjcuOGwtLjQtLjRhMS43IDEuNyAwIDAgMC0yLjMuMUw1LjIgOC4yIDMgNi40YTEuNyAxLjcgMCAwIDAtMi4zLjFMLjQgN2ExLjcgMS43IDAgMCAwIC4xIDIuM2wzLjggMy41YTEuNyAxLjcgMCAwIDAgMi40LS4xTDE1IDMuMWExLjcgMS43IDAgMCAwLS4yLTIuM3oiIGRhdGEtbmFtZT0iUGZhZCA0Ii8+PC9zdmc+);
    }
  }
  input[type='radio'] {
    display: none;
    position: absolute;
    width: 100%;
    appearance: none;
    &:checked + label {
      background: lighten($accentcolor, 15%);
      animation-name: blink;
      animation-duration: 1s;
      border-color: $accentcolor;
      &:after {
        background: $accentcolor;
      }
      &:before {
        width: 20px;
        height: 20px;
      }
    }
  }
}

@keyframes blink {
  0% {
    background-color: lighten($accentcolor, 15%);
  }
  10% {
    background-color: lighten($accentcolor, 15%);
  }
  11% {
    background-color: lighten($accentcolor, 20%);
  }
  29% {
    background-color: lighten($accentcolor, 20%);
  }
  30% {
    background-color: lighten($accentcolor, 15%);
  }
  50% {
    background-color: lighten($accentcolor, 20%);
  }
  45% {
    background-color: lighten($accentcolor, 15%);
  }
  50% {
    background-color: lighten($accentcolor, 20%);
  }
  100% {
    background-color: lighten($accentcolor, 15%);
  }
}
</style>
