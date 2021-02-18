<template>
  <div class="wrapper kor">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="row" id="overflow">
            <LawyermatchList v-for="lawyer in lawyerlists.data" :key="lawyer.uuid" :lawyer="lawyer" :color="lawyer.color"></LawyermatchList>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LawyermatchList from './components/LawyermatchList';
import { LawyerList } from '@/api/auth';

export default {
  components: {
    LawyermatchList,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      lawyerlists: [],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_matching.png'),
    },
  },
  async created() {
    const res = await LawyerList();
    this.lawyerlists = res;
    for (let index = 0; index < this.lawyerlists.data.length; index++) {
      this.lawyerlists.data[index].color = 'rgb(68, 114, 148)';
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    inum(i) {
      this.i = i;
    },
  },
};
</script>

<style lang="scss">
#overflow {
  overflow: auto;
}
</style>
<style lang="scss" scoped>
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
</style>
