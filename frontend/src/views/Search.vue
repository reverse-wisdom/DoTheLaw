<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised kor">
      <div class="section section-basic">
        <v-row no-gutters justify="center">
          <v-col cols="6" md="4">
            <v-toolbar dense>
              <v-text-field hide-details single-line id="searchWord" placeholder="판례명" v-model="search"></v-text-field>

              <v-btn icon @click="searchLaw()">
                <i class="material-icons">search</i>
              </v-btn>
            </v-toolbar>
            <br />
            <v-card class="mx-auto" tile>
              <v-subheader>연관검색어</v-subheader>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>1. 공사설비</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>2. 부실공사</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>3. 소음</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>4. 방음</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-card>

            <br />
            <v-card class="mx-auto" max-width="500" tile>
              <v-subheader>판례목록</v-subheader>
              <v-list style="max-height: 400px" class="overflow-y-auto">
                <template v-for="content in contents">
                  <v-list-item :key="content.no">
                    <v-list-item-content>
                      <v-list-item-title v-html="content.name"></v-list-item-title>
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
            <v-card class="pa-2 overflow-y-auto" max-height="1024" max-width="1200" outlined tile>
              <div class="container">
                <md-button class="md-info" style="margin: auto">
                  <i class="material-icons">search</i>
                  변호사찾기
                </md-button>
                <md-button class="md-info" style="margin: auto">
                  <i class="material-icons">search</i>
                  커뮤니티에 검색
                </md-button>
              </div>
              <div class="container text-center"></div>
              <br />

              <template v-if="render">
                <h3 v-html="judgment.PrecService.사건명._cdata"></h3>
                <v-simple-table>
                  <tbody>
                    <tr>
                      <td>선고일자</td>
                      <td>{{ judgment.PrecService.선고일자._text }}</td>
                    </tr>
                    <tr>
                      <td>법원명</td>
                      <td>{{ judgment.PrecService.법원명._text }}</td>
                    </tr>
                    <tr>
                      <td>사건종류명</td>
                      <td>{{ judgment.PrecService.사건종류명._text }}</td>
                    </tr>
                    <tr>
                      <td>사건종류코드</td>
                      <td>{{ judgment.PrecService.사건종류코드._text }}</td>
                    </tr>
                  </tbody>
                </v-simple-table>

                <md-radio v-model="radio" :value="judgment.PrecService.판결요지._cdata">판결요지</md-radio>
                <md-radio v-model="radio" :value="judgment.PrecService.참조조문._cdata">참조조문</md-radio>
                <md-radio v-model="radio" :value="judgment.PrecService.판례내용._cdata">판례내용</md-radio>

                <p v-html="radio"></p>
              </template>
            </v-card>
          </v-col>
          <!-- <v-col cols="6" md="4">
            <v-card class="pa-2" outlined tile>
              <v-card class="mx-auto" tile>
                <v-subheader>용어설명</v-subheader>
                <v-list style="max-height: 400px" class="overflow-y-auto">
                  <template v-for="dict in dicts">
                    <v-list-item :key="dict.no" two-line>
                      <v-list-item-content>
                        <v-list-item-title v-text="dict.title"></v-list-item-title>
                        <v-list-item-subtitle v-text="dict.content"></v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                </v-list>
              </v-card>
            </v-card>
          </v-col> -->
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import convert from 'xml-js';
import { lawService, lawSearch } from '@/api/service';

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
      dicts: [
        { no: 1, title: '상고', content: '상고(上告)는 상소의 한 가지로서, 제2심 판결에 불복할 때에 하는 신청이다.' },
        { no: 2, title: '항소', content: '항소(抗訴, Berufung)은 상소의 한 가지로서, 제1심 판결에 불복할 때에 하는 신청이다.' },
        {
          no: 3,
          title: '민사소송법',
          content:
            '민사소송법이라고 함은 형식적으로는 민사소송법이라는 법률을 말하고, 실질적으로는 민사소송제도를 규율하는 법규 일체를 말한다. 따라서 실질적인 의미의 민사소송법에는 민사소송법외에도 민사집행법(2002년 분리), 가사소송법 등등이 포함된다. 파산이나 회생, 개인회생도 민사소송법학의 연구대상이다. 즉, 널리 민사절차 일반이 민사소송법학의 연구 대상이다.',
        },
      ],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
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
  methods: {
    async searchLaw() {
      var searchWord = document.getElementById('searchWord').value;
      const { data } = await lawSearch(searchWord);
      // console.log(data.PrecSearch.prec[0].id);
      // for(var i=0;i<data.length;i++){
      //   this.contents.push({
      //     no: data.
      //   })
      // }

      console.log(data);
      // console.log(data.PrecSearch.prec[0].id);

      this.contents = [];
      axios
        .get('https://www.law.go.kr/DRF/lawSearch.do?OC=dbm01049&target=prec&type=XML&mobileYn=Y&display=100&query=' + searchWord)
        .then(({ data }) => {
          var xml = data;
          var json = convert.xml2json(xml, { compact: true });
          let $vm = this;
          JSON.parse(json).PrecSearch.prec.forEach(function(entry) {
            $vm.contents.push({
              no: entry.판례일련번호._text,
              name: entry.사건명._cdata,
              category: entry.법원명._text,
            });
          });
          this.detailJudgment(this.contents[0].no);
        })
        .catch();
    },
    detailJudgment(data) {
      console.log(data);
      this.render = false;
      this.judgment = {};
      axios
        .get('https://www.law.go.kr/DRF/lawService.do?OC=dbm01049&target=prec&type=xml&ID=' + data)
        .then(({ data }) => {
          var xml = data;
          var json = convert.xml2json(xml, { compact: true });
          this.judgment = JSON.parse(json);
          this.render = true;
          this.radio = this.judgment.PrecService.판결요지._cdata;
        })
        .catch();
    },
  },
};
</script>

<style lang="scss" scoped>
// hr 설정
.div-hr {
  width: 80%;
}
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.mr {
  padding: 5px;
}
</style>
