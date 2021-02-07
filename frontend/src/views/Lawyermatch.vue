<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h1>추천 변호사 매칭</h1>
        <hr />
        <div class="row">
          <LawyermatchList v-for="lawyer in lawyerlists.data" :key="lawyer.uuid" :lawyer="lawyer"></LawyermatchList>
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
      default: require('@/assets/img/jj02.gif'),
    },
  },
  async created() {
    const res = await LawyerList();
    this.lawyerlists = res;
    console.log(res);
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

<style>
h1 {
  text-align: center;
}
</style>
