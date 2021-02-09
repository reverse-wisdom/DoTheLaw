<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <hr />
        <div class="container">
          <controversy :list-array="list" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Controversy from '@/views/components/Controversy';
import { all, agree, opposition } from '@/api/controversy';
export default {
  components: { Controversy },
  bodyClass: 'profile-page',
  data() {
    return {
      list: [],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
    },
    city: {
      type: String,
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  async created() {
    const { data } = await all();
    this.list = data;
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
</style>
