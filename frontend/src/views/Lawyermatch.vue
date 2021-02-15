<template>
  <div class="wrapper">
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
      var r = Math.floor(Math.random() * 255);
      var g = Math.floor(Math.random() * 255);
      var b = Math.floor(Math.random() * 255);
      this.lawyerlists.data[index].color = 'rgb(' + r + ',' + g + ',' + b + ')';
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
