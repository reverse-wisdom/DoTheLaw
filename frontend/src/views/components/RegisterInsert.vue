<template>
  <!-- 회원가입 페이지 테이블 컴포넌트 -->
  <div class="wrapper">
    <login-card header-color="green" style="padding-bottom:-3px;">
      <h4 slot="title" class="title kor" style="font-size:250%;">회원가입</h4>

      <template slot="inputs">
        <br />
        <md-field class="md-form-group">
          <md-icon>face</md-icon>
          <label>사용자이름...</label>
          <md-input v-model="name"></md-input>
        </md-field>

        <md-field class="md-form-group">
          <md-icon>account_circle</md-icon>
          <label>이메일</label>
          <md-input v-model="email"></md-input>
          <md-button class="md-success md-wd md-sm" @click="emailCheckFun()">이메일 중복확인</md-button>
        </md-field>
        <md-field class="md-form-group">
          <md-icon>face</md-icon>
          <label>전화번호...</label>
          <md-input v-model="phone"></md-input>
        </md-field>
        <md-field class="md-form-group">
          <md-icon>lock_outline</md-icon>
          <label>비밀번호</label>
          <md-input v-model="password" type="password"></md-input>
        </md-field>
        <md-field class="md-form-group">
          <md-icon>lock_outline</md-icon>
          <label>비밀번호확인</label>
          <md-input v-model="pwdcheck" type="password"></md-input>
        </md-field>
        <div>
          <md-radio v-model="isLawer" :value="false">사용자</md-radio>
          <md-radio v-model="isLawer" :value="true">변호사</md-radio>
        </div>
      </template>
      <md-button slot="footer" class="md-success md-wd" @click="register()">
        가입하기
      </md-button>
    </login-card>
  </div>
</template>

<script>
import { LoginCard } from '@/components';
import { registerUser, emailCheck } from '@/api/auth';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: { LoginCard },
  data() {
    return {
      email: null,
      userid: null,
      phone: null,
      name: null,
      password: null,
      pwdcheck: null,
      emailCheck: 'fail',
      isChecked: false,
      isLawer: false,
    };
  },
  mounted() {},
  methods: {
    // 이메일이 유효한지 검사하는 함수
    async emailCheckFun() {
      if (this.email == null) {
        this.$swal({
          icon: 'error',
          title: '아이디를 입력해주세요!',
        });
      } else {
        const { data } = await emailCheck(this.email);
        if (data == 'SUCCESS') {
          this.$swal({
            icon: 'error',
            title: '이미 이메일이 존재합니다!!',
          });
          this.isChecked = false;
        } else {
          // 추후 정규식 처리 필요함
          this.$swal({
            icon: 'success',
            title: '사용가능한 이메일 입니다!!',
          });
          this.isChecked = true;
          this.emailCheck = 'fail';
        }
      }
    },

    // 각항목에대해 null값 체크
    async register() {
      if (this.name == null) {
        this.$swal({
          icon: 'error',
          title: '이름을 입력해주세요!',
        });
      } else if (!this.isChecked && this.emailCheck != 'success') {
        this.$swal({
          icon: 'error',
          title: '이메일 중복검사를 해주세요!',
        });
      } else if (this.password == null) {
        this.$swal({
          icon: 'error',
          title: '비밀번호를 입력해주세요!',
        });
      } else if (this.pwdcheck == null) {
        this.$swal({
          icon: 'error',
          title: '비밀번호확인을 입력해주세요!',
        });
      } else if (this.phone == null) {
        this.$swal({
          icon: 'error',
          title: '전화번호를 입력해주세요!',
        });
      } else if (this.password != this.pwdcheck) {
        this.$swal({
          icon: 'error',
          title: '비밀번호확인이 일치하지않습니다!',
        });
      } else {
        const userData = {
          email: this.email,
          password: this.password,
          name: this.name,
          phone: this.phone,
          role: this.isLawer ? 'ADMIN' : 'USER', // 임시
        };
        const { data } = await registerUser(userData);
        if (data == 'SUCCESS') {
          this.$swal({
            position: 'top-end',
            icon: 'success',
            title: '회원가입성공!!',
            showConfirmButton: false,
            timer: 1500,
          });
          this.$router.push('/');
        } else {
          this.$swal({
            icon: 'error',
            title: '회원가입 실패 관리자에게 문의해주세요',
          });
        }
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
