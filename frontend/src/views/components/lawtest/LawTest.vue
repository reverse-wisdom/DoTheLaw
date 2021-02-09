<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="">
          <img src="@/assets/img/lawtitle.png" style="max-width:30%; height:auto;" class="title-lawtest" alt="" />
        </div>
        <div class="">
          <img src="@/assets/img/main2.png" style="max-width:30%; height:auto;" alt="" />
        </div>
        <div class="btn-center">
          <md-button class="md-info" type="button" @click="moveTest">
            테스트하러가기
          </md-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  bodyClass: 'profile-page',
  data() {
    return {
      values: [],
      token: '',
      search: '',
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
  methods: {
    moveTest() {
      this.$router.push('/golawtest');
    },
    detailPage(value) {
      var query = value.boardId;
      this.$router.push({ name: 'boardDetail', query: { boardId: query } });
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
.btn-center {
  text-align: center;
}

// img {
//   width: 80%;
//   top: 50%;
//   left: 50%;
//   transform: translate(-50%, -50%);
// }
.title-lawtest {
  left: 50%;
  animation: flipInX;
  animation-duration: 1s;
  animation-iteration-count: infinite;
}
.section {
  text-align: center;
}
</style>
