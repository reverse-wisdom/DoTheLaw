<template>
  <!-- 로그인 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <hr />
        <div class="container">
          <v-card>
            <v-card-title class="text-center justify-center py-6">
              <h2 class="font-weight-bold display-3">
                생활법령
              </h2>
            </v-card-title>
            <v-tabs v-model="tab" background-color="#ebf3fe " class="primary--text" centered icons-and-text>
              <v-tabs-slider></v-tabs-slider>

              <v-tab v-for="(item, index) in items" :key="index" :href="'#tab-' + index">
                <v-img :src="item.img.off" max-height="60"></v-img>
                {{ item.name }}
              </v-tab>
            </v-tabs>

            <v-tabs-items v-model="tab">
              <v-tab-item v-for="(item, index) in items" :key="index" :value="'tab-' + index">
                <v-card flat>
                  <!-- <v-card-text>{{ item.content }}</v-card-text> -->
                  <v-item-group active-class="item--active">
                    <v-container>
                      <v-row>
                        <v-col v-for="(obj, index) in item.content" :key="index" cols="12" md="12">
                          <v-item v-slot="{ active, toggle }">
                            <v-card class="d-flex align-center" @click="toggle">
                              <v-scroll-y-transition>
                                <div class="ml-3 mr-3">
                                  <div class="h4 flex-grow-1 text-left font-weight-black">
                                    {{ obj.title }}
                                  </div>
                                  <span>
                                    {{ obj.content }}
                                  </span>
                                  <easy-law-detail-card v-if="active" :no="obj.no"></easy-law-detail-card>
                                </div>
                              </v-scroll-y-transition>
                            </v-card>
                          </v-item>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-item-group>
                </v-card>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { getEasyLawMain, getEasyLawList, getEasyLawDetail } from '@/api/easylaw.js';
import EasyLawDetailCard from './components/EasyLawDetailCard.vue';

export default {
  data() {
    return {
      tab: null,
      items: [],
    };
  },
  components: { EasyLawDetailCard },
  mounted() {
    this.tab = 'tab-5';
    getEasyLawMain((response) => {
      // console.dir(response.data);
      this.items = response.data;
      this.items.forEach((el) => {
        getEasyLawList(el.type, (response) => {
          // console.dir(response.data);
          el.content = response.data;
        });
      });
    });
    setTimeout(() => {
      this.tab = 'tab-0';
    }, 500);
  },
  methods: {
    getItemDetail(no) {
      // console.log('받은 no', no);
      const data = (async () => {
        return await getEasyLawDetail(no, (response) => response);
      })();
      // console.log(data);
    },
  },
  bodyClass: 'profile-page',
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
};
</script>

<style lang="scss" scoped>
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.section {
  padding: 0;
}
div.item--active {
  color: green;
}
.v-tab.v-tab--active {
  color: #1976d2;
}
.v-tab:hover {
  background-color: #1986d244;
  color: #1976d2;
}
</style>
