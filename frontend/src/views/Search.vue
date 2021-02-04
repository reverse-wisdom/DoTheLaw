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
                <h3 v-html="judgment.PrecService.사건명"></h3>
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

                <md-radio v-model="radio" :value="judgment.PrecService.판결요지">판결요지</md-radio>
                <md-radio v-model="radio" :value="judgment.PrecService.참조조문">참조조문</md-radio>
                <md-radio v-model="radio" :value="judgment.PrecService.판례내용">판례내용</md-radio>

                <p v-html="radio"></p>
              </template>
            </v-card>
          </v-col>
        </v-row>
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
    async searchLaw() {
      const { data } = await lawSearch(this.search);
      for (var i = 0; i < data.PrecSearch.prec.length; i++) {
        this.contents.push({
          no: data.PrecSearch.prec[i].판례일련번호,
          name: data.PrecSearch.prec[i].사건명,
          category: data.PrecSearch.prec[i].법원명,
        });
      }
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
      this.radio = this.judgment.PrecService.판결요지;

      this.dict.forEach((element) => {
        var regEx = new RegExp(element.word, 'g');
        this.judgment.PrecService.판결요지 = this.judgment.PrecService.판결요지.replace(regEx, element.mean);
        this.judgment.PrecService.참조조문 = this.judgment.PrecService.참조조문.replace(regEx, element.mean);
        this.judgment.PrecService.판례내용 = this.judgment.PrecService.판례내용.replace(regEx, element.mean);
      });
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
