<template>
  <!-- 회원가입 페이지 테이블 컴포넌트 -->
  <div class="wrapper">
    <login-card header-color="green" style="padding-bottom:-3px;">
      <h4 slot="title" class="title kor" style="font-size:250%;">회원가입</h4>

      <template slot="inputs">
        <br />
        <!-- 닉네임 -->
        <md-field class="md-form-group">
          <md-icon>face</md-icon>
          <label>닉네임...</label>
          <md-input required v-model="name"></md-input>
          <md-button class="md-success md-wd md-sm" @click="nameCheckFun()">닉네임 중복확인</md-button>
        </md-field>

        <!-- 이메일 -->
        <md-field class="md-form-group">
          <md-icon>account_circle</md-icon>
          <label>이메일...</label>
          <md-input required v-model="email" placeholder="ssafy@ssafy.com"></md-input>
          <template v-if="msg['email']">
            <div class="icon icon-info">
              <md-icon>done</md-icon>
            </div>
          </template>
          <template v-else>
            <div class="icon icon-danger">
              <md-icon>priority_high</md-icon>
            </div>
          </template>
          <md-button class="md-success md-wd md-sm" @click="emailCheckFun()">이메일 중복확인</md-button>
        </md-field>

        <!-- 비밀번호 -->
        <md-field class="md-form-group">
          <md-icon>lock_outline</md-icon>
          <label>비밀번호...</label>
          <md-input required v-model="password" type="password"></md-input>
          <template v-if="msg['password']">
            <div class="icon icon-info" style="padding-right: 30px;">
              <md-icon>done</md-icon>
            </div>
          </template>
          <template v-else>
            <div class="icon icon-danger" style="padding-right: 30px;">
              <md-icon>priority_high</md-icon>
            </div>
          </template>
        </md-field>

        <!-- 비밀번호 확인 -->
        <md-field class="md-form-group">
          <md-icon>lock_outline</md-icon>
          <label>비밀번호확인...</label>
          <md-input required v-model="pwdcheck" type="password"></md-input>
        </md-field>

        <!-- 전화번호 -->
        <md-field class="md-form-group">
          <md-icon>call</md-icon>
          <label>전화번호...</label>
          <md-input v-model="phone" placeholder="010-1234-5678"></md-input>
          <template v-if="msg['phone']">
            <div class="icon icon-info">
              <md-icon>done</md-icon>
            </div>
          </template>
          <template v-else>
            <div class="icon icon-danger">
              <md-icon>priority_high</md-icon>
            </div>
          </template>
        </md-field>
      </template>

      <md-button slot="footer" class="md-success md-wd" @click="register()">가입하기</md-button>
    </login-card>
  </div>
</template>

<script>
import { LoginCard } from '@/components';
import { registerUser, nameCheck, emailCheck } from '@/api/auth';

export default {
  components: { LoginCard },
  data() {
    return {
      email: null,
      phone: null,
      name: null,
      password: null,
      pwdcheck: null,
      emailCheck: 'fail',
      nameCheck: 'fail',
      isCheckedEmail: false,
      isCheckedName: false,
      data: null,
      msg: [],
    };
  },
  // 실시간 파악(검증)
  watch: {
    email(value) {
      this.email = value;
      this.validateEmail(value);
    },
    phone(value) {
      this.phone = value;
      this.validatePhone(value);
    },
    password(value) {
      this.password = value;
      this.validatePassword(value);
    },
  },
  methods: {
    validateEmail(value) {
      if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value)) {
        this.msg['email'] = true;
      } else {
        this.msg['email'] = false;
      }
    },
    validatePhone(value) {
      if (/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(value)) {
        this.msg['phone'] = true;
      } else {
        this.msg['phone'] = false;
      }
    },
    validatePassword(value) {
      if (/^.*(?=^.{8,}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/.test(value)) {
        this.msg['password'] = true;
      } else {
        this.msg['password'] = false;
      }
    },

    // 사용자 닉네임이 유효한지 검사하는 함수
    async nameCheckFun() {
      if (this.name == null) {
        this.$swal({
          icon: 'error',
          title: '닉네임을 입력해주세요!',
        });
      } else {
        const { data } = await nameCheck(this.name);
        if (data == 'DUPLICATE') {
          this.$swal({
            icon: 'error',
            title: '이미 닉네임이 존재합니다!!',
          });
          this.isCheckedName = false;
          this.nameCheck = 'fail';
        } else {
          this.$swal({
            icon: 'success',
            title: '사용가능한 닉네임 입니다!!',
          });
          this.isCheckedName = true;
          this.nameCheck = 'success';
        }
      }
    },
    // 이메일이 유효한지 검사하는 함수
    async emailCheckFun() {
      if (this.email == null) {
        this.$swal({
          icon: 'error',
          title: '이메일을 입력해주세요!',
        });
      } else if (!this.msg['email']) {
        this.$swal({
          icon: 'error',
          title: '이메일형식이 잘못되었습니다!',
        });
      } else {
        const { data } = await emailCheck(this.email);
        if (data == 'DUPLICATE') {
          this.$swal({
            icon: 'error',
            title: '이미 이메일이 존재합니다!!',
          });
          this.isCheckedEmail = false;
          this.emailCheck = 'fail';
        } else {
          this.$swal({
            icon: 'success',
            title: '사용가능한 이메일 입니다!!',
          });
          this.isCheckedEmail = true;
          this.emailCheck = 'success';
        }
      }
    },

    // 각항목에대해 null값 체크
    async register() {
      if (!this.isCheckedName && this.nameCheck != 'success') {
        this.$swal({
          icon: 'error',
          title: '닉네임 중복검사를 해주세요!',
        });
      } else if (!this.msg['email']) {
        this.$swal({
          icon: 'error',
          title: '이메일 형식이 잘못되었습니다.!',
        });
      } else if (!this.isCheckedEmail && this.emailCheck != 'success') {
        this.$swal({
          icon: 'error',
          title: '이메일 중복검사를 해주세요.!',
        });
      } else if (!this.msg['phone']) {
        this.$swal({
          icon: 'error',
          title: '전화 번호 형식이 잘못되었습니다.!',
        });
      } else if (!this.msg['password']) {
        this.$swal({
          icon: 'error',
          title: '비밀번호 입력시 8자리이상, 특수문자를 포함해주세요!',
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
          role: 'USER',
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
