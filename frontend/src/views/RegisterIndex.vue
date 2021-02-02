<template>
  <!-- 회원가입 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <hr />
        <div class="container text-center">
          <div class="md-layout">
            <div class="md-layout-item my-2">
              <v-btn x-large color="blue-grey" dark @click="goRegisterUser">
                일반 회원
                <v-icon right dark>
                  mdi-account-check
                </v-icon>
              </v-btn>
            </div>
            <div class="md-layout-item my-2">
              <v-btn x-large color="success" dark @click="goRegisterLawyer">
                변호사
                <v-icon right dark>
                  mdi-account-check
                </v-icon>
              </v-btn>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  bodyClass: 'profile-page',
  data() {
    return {};
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
  methods: {
    goRegisterUser() {
      this.$router.push('/registeruser');
    },
    goRegisterLawyer() {
      this.$router.push('/registerlawyer');
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
</style>
