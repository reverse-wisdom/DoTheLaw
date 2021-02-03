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

      <!-- 구글로그인 -->
      <GoogleLogin slot="footer" :params="params" :renderParams="renderParams" :onSuccess="onSuccess"></GoogleLogin>

      <br />
      <div slot="footer" class="md-info md-wd mt-3">
        <a id="reauthenticate-popup-btn" @click="loginFormWithKakao">
          <img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="40px" />
        </a>
        <p id="reauthenticate-popup-result"></p>
      </div>
    </login-card>
  </div>
</template>

<script>
import { LoginCard } from '@/components';
import GoogleLogin from 'vue-google-login';

export default {
  name: 'Login',
  components: { LoginCard, GoogleLogin },
  data() {
    return {
      email: null,
      password: null,
      googleEmail: '',
      message: '',
      id: '',
      name: '',
      type: '',
      params: {
        client_id: '607177792648-83sqdjosupk6ipqpn7cc69bl35pubgfu.apps.googleusercontent.com',
      },
      // only needed if you want to render the button with the google ui
      renderParams: {
        width: 250,
        height: 50,
        longtitle: true,
      },
    };
  },
  created() {
    window.onSignIn = this.onSignIn;
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
      }
    },

    socialLogin() {
      const userData = {
        id: this.id,
        email: this.socialEmail,
        name: this.name,
        type: this.type,
        role: 'USER',
      };
      this.$store.dispatch('SOCIALLOGIN', userData);
    },
    // onSignIn(googleUser) {
    //   var profile = googleUser.getBasicProfile();
    //   this.id = profile.getId();
    //   this.name = profile.getName();
    //   this.socialEmail = profile.getEmail();
    //   var id_token = googleUser.getAuthResponse().id_token;
    //   this.type = 'google';
    //   console.log(profile);
    //   this.socialLogin();
    // },
    onSuccess(googleUser) {
      var profile = googleUser.getBasicProfile();
      // console.log(profile);
      this.id = profile.KR;
      this.name = profile.sd;
      this.socialEmail = profile.lt;
      var id_token = googleUser.getAuthResponse().id_token;
      this.type = 'google';
      this.socialLogin();
    },
    loginFormWithKakao() {
      //로그인 성공
      var $vm = this;
      Kakao.Auth.loginForm({
        success: function(authObj) {
          //로그인 성공 이후 데이터 받아오기
          Kakao.API.request({
            url: '/v2/user/me',
            success: function(res) {
              console.log(res);
              $vm.id = res.id;
              $vm.socialEmail = res.kakao_account.email;
              $vm.name = res.kakao_account.profile.nickname;
              $vm.type = 'kakao';
              $vm.socialLogin();
            },
            fail: function(error) {
              alert('login success, but failed to request user information: ' + JSON.stringify(error));
            },
          });
        },
        fail: function(err) {
          this.showResult(JSON.stringify(err));
        },
      });
    },
    showResult(result) {
      document.getElementById('reauthenticate-popup-result').innerText = result;
    },
  },
};
</script>

<style lang="scss" scoped>
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
</style>
