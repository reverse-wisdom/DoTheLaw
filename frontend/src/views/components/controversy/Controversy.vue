<template>
  <div class="wrapper">
    <div class="md-layout">
      <div class="md-layout-item" style="text-align: center; ">
        <v-container grid-list-md>
          <template v-for="list in listData">
            <v-card class="pa-md-4 mb-8 mx-lg-auto " :key="list.controversyId">
              <controversy-item  :list="list" v-on:updateContent="updateContent" />
            </v-card>
          </template>
        </v-container>
      </div>
    </div>
  </div>
</template>

<script>
import DonutChart from '@/views/components/controversy/DonutChart';
import ControversyItem from '@/views/components/controversy/ControversyItem';
import { all } from '@/api/controversy';

export default {
  name: 'controversy',
  components: {
    DonutChart,
    ControversyItem,
  },
  data() {
    return {
      listData: [],
      controversyImg: require('@/assets/img/controversy.png'),
    };
  },
  mounted() {},
  async created() {
    const { data } = await all();
    this.listData = data;
  },
  methods: {
    async updateContent() {
      const { data } = await all();
      this.listData = data;
    },
  },
};
</script>

<style lang="scss" scoped>
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: none !important;
}
// 공백문제 해결
.keep-spaces {
  white-space: pre-wrap;
}

.gBar {
  display: inline-block;
  width: 100%;
  height: 20px;
  margin: 0;
  border: none;
  border-radius: 20px;
}
.gAction4 {
  display: block;
  height: 20px;
  background: #4673e3 right 4px center no-repeat;
  background-size: 50px;
  margin: 0;
  border: none;
  border-radius: 20px 0 0 20px;
  float: left;
}
.gAction2 {
  display: block;
  height: 20px;
  background: #fe6b53 left 4px center no-repeat;
  background-size: 50px;
  margin: 0;
  border: none;
  border-radius: 0 20px 20px 0;
  float: right;
}
.t_blue {
  color: #4673e3;
}
.t_red {
  color: #fe6b53;
}
.t_blue,
.t_red {
  font-family: 'Work Sans';
  letter-spacing: -1px;
  line-height: 110%;
  font-size: 30px;
}

.endDate {
  font-family: 'Work Sans';
  letter-spacing: -1px;
  line-height: 110%;
  font-size: 20px;
}

.page {
  height: 30px;
}
.donut-inner {
  margin-top: -100px;
  margin-bottom: 100px;
}
.donut-inner h5 {
  margin-bottom: 20px;
  margin-top: 40px;
}
.donut-inner span {
  font-size: 20px;
}
</style>
