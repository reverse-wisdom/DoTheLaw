<template>
  <!-- 로그인 페이지 테이블 컴포넌트 -->
  <div class="wrapper">
    <login-card header-color="info" style="padding-bottom:-3px;">
      <h4 slot="title" class="title kor" style="font-size:250%;">로그인</h4>

      <template slot="inputs">
        <br />
        <md-field class="md-form-group">
          <md-icon>account_circle</md-icon>
          <label>이메일</label>
          <md-input v-model="email"></md-input>
        </md-field>
        <md-field class="md-form-group">
          <md-icon>lock_outline</md-icon>
          <label>비밀번호</label>
          <md-input v-model="password" type="password"></md-input>
        </md-field>
      </template>
      <md-button slot="footer" class="md-info md-wd " @click="login()">
        로그인
      </md-button>
    </login-card>
  </div>
</template>

<script>
import { LoginCard } from '@/components';

import axios from 'axios';
export default {
  name: 'Login',
  components: { LoginCard },
  data() {
    return {
      email: null,
      password: null,
      message: '',
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
    },
  },
  methods: {
    login() {
      if (this.email == null) {
        this.$swal({
          icon: 'error',
          title: '아이디를 입력해 주세요!',
        });
      } else if (this.password == null) {
        this.$swal({
          icon: 'error',
          title: '비밀번호를 입력해 주세요!',
        });
      } else {
        const userData = {
          email: this.email,
          password: this.password,
        };
        console.log(userData);
        this.$store.dispatch('LOGIN', userData);

        // var result = this.$store.dispatch('LOGIN', this.user);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
</style>
