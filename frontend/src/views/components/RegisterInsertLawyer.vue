<template>
  <!-- 회원가입 페이지 테이블 컴포넌트 -->
  <div class="wrapper">
    <login-card header-color="red" style="padding-bottom:-3px;">
      <h4 slot="title" class="title kor" style="font-size:250%;">회원가입</h4>

      <template slot="inputs">
        <br />
        <md-field class="md-form-group">
          <md-icon>face</md-icon>
          <label>닉네임...</label>
          <md-input v-model="name"></md-input>
          <md-button class="md-accent md-wd md-sm" @click="nameCheckFun()">닉네임 중복확인</md-button>
        </md-field>

        <md-field class="md-form-group">
          <md-icon>account_circle</md-icon>
          <label>이메일...</label>
          <md-input v-model="email"></md-input>
          <md-button class="md-accent md-wd md-sm" @click="emailCheckFun()">이메일 중복확인</md-button>
        </md-field>
        <md-field class="md-form-group">
          <md-icon>call</md-icon>
          <label>전화번호...</label>
          <md-input v-model="phone"></md-input>
        </md-field>
        <md-field class="md-form-group">
          <md-icon>lock_outline</md-icon>
          <label>비밀번호...</label>
          <md-input v-model="password" type="password"></md-input>
        </md-field>
        <md-field class="md-form-group">
          <md-icon>lock_outline</md-icon>
          <label>비밀번호확인...</label>
          <md-input v-model="pwdcheck" type="password"></md-input>
        </md-field>
        <v-file-input type="file" name="uploadFile" accept="image/png, image/jpeg, image/bmp" placeholder="회원사진" ref="files" prepend-icon="mdi-camera" @change="handleFilesUpload"></v-file-input>
      </template>
      <md-button slot="footer" class="md-accent md-wd" @click="register()">가입하기</md-button>
    </login-card>
  </div>
</template>

<script>
import { LoginCard } from '@/components';
import { registerUser } from '@/api/auth';
import axios from 'axios';

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
      files: null,
      data: null,
    };
  },
  mounted() {},
  methods: {
    // 파일의 경우 chage리스너로 감지해야함
    handleFilesUpload(file) {
      this.files = file;
    },

    // 사용자이름이 유효한지 검사하는 함수
    nameCheckFun() {
      let result = '';
      if (this.name == null) {
        this.$swal({
          icon: 'error',
          title: '이름을 입력해주세요!',
        });
      } else {
        axios
          .get('/api/member/check/name?name=' + this.name)
          .then(({ data }) => {
            result = data;
            if (result == 'DUPLICATE') {
              this.$swal({
                icon: 'error',
                title: '이미 이름이 존재합니다!!',
              });
              this.isCheckedName = false;
              this.nameCheck = 'fail';
            } else {
              // 추후 정규식 처리 필요함
              this.$swal({
                icon: 'success',
                title: '사용가능한 이름 입니다!!',
              });
              this.isCheckedName = true;
              this.nameCheck = 'success';
            }
          })
          .catch();
      }
    },
    // 이메일이 유효한지 검사하는 함수
    emailCheckFun() {
      let result = '';
      if (this.email == null) {
        this.$swal({
          icon: 'error',
          title: '이메일을 입력해주세요!',
        });
      } else {
        axios
          .get('/api/member/check/email?email=' + this.email)
          .then(({ data }) => {
            result = data;
            if (result == 'DUPLICATE') {
              this.$swal({
                icon: 'error',
                title: '이미 이메일이 존재합니다!!',
              });
              this.isCheckedEmail = false;
              this.emailCheck = 'fail';
            } else {
              // 추후 정규식 처리 필요함
              this.$swal({
                icon: 'success',
                title: '사용가능한 이메일 입니다!!',
              });
              this.isCheckedEmail = true;
              this.emailCheck = 'success';
            }
          })
          .catch();
      }
    },

    // 각항목에대해 null값 체크
    async register() {
      if (!this.isCheckedName && this.nameCheck != 'success') {
        this.$swal({
          icon: 'error',
          title: '이름 중복검사를 해주세요!',
        });
      } else if (!this.isCheckedEmail && this.emailCheck != 'success') {
        this.$swal({
          icon: 'error',
          title: '이메일 중복검사를 해주세요!',
        });
      } else if (!/^.*(?=^.{8,}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/.test(this.password)) {
        this.$swal({
          icon: 'error',
          title: '8자리이상,특수문자를 포함해주세요!',
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
          role: 'LAWYER',
        };

        var FormData = require('form-data');
        var form = new FormData();

        if (this.files != null) {
          form.append('file', this.files);
        }

        axios.post('/api/data', form, { 'Content-Type': 'multipart/form-data' }).then(function(response) {});

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
