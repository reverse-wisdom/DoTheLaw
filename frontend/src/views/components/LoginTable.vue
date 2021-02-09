<template>
  <!-- 로그인 페이지 테이블 컴포넌트 -->
  <div class="wrapper">
    <login-card header-color="info" style="padding-bottom:-3px;" v-if="isLoad">
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
          <md-input v-model="password" type="password" id="input_pwd" @keypress="caps_lock"></md-input>

          <p id="capslock" style="position:relative; border:2px solid #003b83; width:300px; bottom:0px; display:none">
            &nbsp;
            <b>CapsLock</b>
            키가 눌려있습니다.&nbsp;
          </p>
        </md-field>
      </template>

      <div slot="footer">
        <h6 class="text-center">
          <span @click="findPassWord">비밀번호 찾기</span>
        </h6>

        <br />
        <v-btn width="250" height="50" color="success" @click="login()">
          로그인
        </v-btn>
        <hr />
        <br />
        <h4 class="text-center">소셜 아이디로 로그인</h4>
        <hr />

        <GoogleLogin :params="params" :renderParams="renderParams" :onSuccess="onSuccess" ref="Glogin"></GoogleLogin>
        <hr />

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
import { findPassword } from '@/api/auth';
import GoogleLogin from 'vue-google-login';

export default {
  name: 'Login',
  components: { LoginCard, GoogleLogin },
  data() {
    return {
      email: null,
      password: null,
      socialEmail: '',
      message: '',
      image: '',
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
      isLoad: false,
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
    },
  },
  mounted() {
    if (typeof this.$refs.Glogin) {
      this.isLoad = true;
    }
  },
  methods: {
    // 인풋갑 caps lock 버튼 체킹
    caps_lock(e) {
      var keyCode = 0;
      var shiftKey = false;
      keyCode = e.keyCode;
      shiftKey = e.shiftKey;
      if ((keyCode >= 65 && keyCode <= 90 && !shiftKey) || (keyCode >= 97 && keyCode <= 122 && shiftKey)) {
        this.show_caps_lock();
        setTimeout(() => {
          this.hide_caps_lock();
        }, 3500);
      } else {
        this.hide_caps_lock();
      }
    },
    show_caps_lock() {
      $('#capslock').show();
    },
    hide_caps_lock() {
      $('#capslock').hide();
    },
    async findPassWord() {
      this.$swal({
        icon: 'question',
        title: '이메일을 입력해 주세요',
        input: 'text',
        showCancelButton: true,
        showLoaderOnConfirm: true,
        preConfirm: async (email) => {
          return await findPassword(email).then((response) => {
            if (response.data == 'FAIL') {
              this.$swal({
                icon: 'error',
                title: '존재하지 않는 이메일 입니다.!!',
              });
            } else {
              this.$swal({
                icon: 'success',
                title: '해당 이메일로 임시 비밀번호를 발급하였습니다.!',
              });
            }
          });
        },
      });
    },
    loadCheck() {
      this.isLoad = true;
      console.log(this.isLoad);
    },
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
        this.$store.dispatch('LOGIN', userData);
      }
    },

    socialLogin() {
      const userData = {
        id: this.id,
        email: this.socialEmail,
        name: this.name,
        type: this.type,
        image: this.image,
        role: 'USER',
      };
      this.$store.dispatch('SOCIALLOGIN', userData);
    },
    // 구글 로그인
    onSuccess(googleUser) {
      var profile = googleUser.getBasicProfile();
      this.id = profile.KR;
      this.name = profile.sd;
      this.socialEmail = profile.lt;
      this.image = profile.wI;
      var id_token = googleUser.getAuthResponse().id_token;
      this.type = 'google';
      this.socialLogin();
    },
    // 카카오로그인
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
              $vm.image = res.kakao_account.profile.profile_image_url;
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
.md-card img {
  width: 250px;
  height: 50px;
}
</style>
