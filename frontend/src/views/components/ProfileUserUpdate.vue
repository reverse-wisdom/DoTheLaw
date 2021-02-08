<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        변호사 정보 수정폼
        <hr />
        <div class="container"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
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

<style></style>
