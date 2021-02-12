<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">마이페이지</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <div class="col-1"></div>
            <div class="col-3 colum mx-auto">
              <img v-if="$store.state.uuid" class="col-12 r-7 mx-auto" id="profile" :src="'/api/member/image/' + $store.state.uuid + '512'" alt="" />
              <img v-else id="profile" class="col-12 r-7 mx-auto" src="@/assets/img/noimage.jpg" alt="noimage" />
            </div>
            <div class="col-8 row" id="content-sort">
              <h1 class="col-12 r-4">사용자 {{ value.name }}</h1>
              <div class="col-11 mx-auto" id="text-solid-1">
                한줄소개
                <hr />
              </div>
            </div>
            <div class="row">
              <div class="col-5 mx-auto" id="text-solid-margin">
                이메일
                <hr />
                {{ value.email }}
              </div>
              <div class="col-5 mx-auto" id="text-solid">
                전화번호
                <hr />
                {{ value.phone }}
              </div>
              <div class="col-11 mx-auto" id="text-solid-one">
                최근 자문요청
                <AdviseMe />
              </div>
            </div>
            <div class="row mt-5">
              <div class="col-9"></div>
              <div class="btn btn-info col-1 mr-5" @click="moveUserUpdate">정보수정</div>
              <div class="btn btn-info col-1" @click="deleteUser">회원탈퇴</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdviseMe from '@/views/components/advise/AdviseMe.vue';
import { searchUser, signoutUser } from '@/api/auth';

export default {
  components: {
    AdviseMe,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      image: this.$store.state.image,
      value: [],
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
  async created() {
    const email = this.$store.state.email;
    const { data } = await searchUser(email);
    this.value = data;
    console.log('회원정보', this.value);
  },
  methods: {
    moveUserUpdate() {
      this.$router.push({ name: 'profileUserUpdate' });
    },
    async deleteUser() {
      const res = await signoutUser(this.value.uuid);
      console.log(res);
      this.$store.commit('clearEmail');
      this.$store.commit('clearToken');
      this.$store.commit('clearNickname');
      this.$store.commit('clearPassword');
      this.$store.commit('clearName');
      this.$store.commit('clearUuid');
      this.$store.commit('clearImage');
      localStorage.clear();
      sessionStorage.clear();
      $cookies.keys().forEach((cookie) => $cookies.remove(cookie));
      this.$router.push({ name: 'RegisterIndex' });
    },
  },
};
</script>

<style>
#profile {
  border-radius: 70%;
}

#image-change-button {
  border: 1px solid gray;
  margin-top: 3px;
}
#button-sort {
  justify-items: center;
}
#text-solid {
  border: 1px solid black;
  width: 200px;
  height: 120px;
  margin-top: 2rem;
}
#text-solid-1 {
  border: 1px solid black;
  width: 200px;
  height: 120px;
}
#text-solid-margin {
  border: 1px solid black;
  width: 200px;
  height: 120px;
  margin-top: 2rem;
  margin-right: 4.7rem;
}
#text-solid-one {
  border: 1px solid black;
  height: auto;
  margin-top: 2rem;
}
</style>
