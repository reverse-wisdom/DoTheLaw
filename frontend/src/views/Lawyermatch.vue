<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">추천 변호사 매칭</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <LawyermatchList v-for="lawyer in lawyerlists.data" :key="lawyer.uuid" :lawyer="lawyer"></LawyermatchList>
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
      default: require('@/assets/img/bg_matching.jpg'),
    },
  },
  async created() {
    const res = await LawyerList();
    this.lawyerlists = res;
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
