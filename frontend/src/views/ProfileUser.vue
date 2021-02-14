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
            <div class="col-3 colum mx-auto text-center">
              <img v-if="$store.state.uuid" class="col-12 r-7 mx-auto" id="profile" :src="'/api/member/image/' + $store.state.uuid + '/512'" alt="" />
              <img v-else id="profile" class="col-12 r-7 mx-auto" src="@/assets/img/noimage.jpg" alt="noimage" />
              <h1 class="col-12 r-4">{{ value.name }}</h1>
            </div>
            <div class="col-8 row" id="content-sort">
              <div class="col-12 mx-auto" id="text-solid">
                <div>
                  이메일
                </div>
                <hr />
                <span class="padding">
                  {{ value.email }}
                </span>
              </div>
              <div class="col-12 mx-auto" id="text-solid">
                <div>
                  전화번호
                </div>
                <hr />
                <span class="padding">
                  {{ value.phone }}
                </span>
              </div>
            </div>
            <div class="row mx-auto">
              <div class="col-11 mx-auto" id="text-solid-advise">
                <div>
                  최근 자문요청
                </div>
                <hr />
                <ul>
                  <AdviseMe id="text-solids" v-for="(data, idx) in advise" :key="idx" :data="data" />
                  <div class="col-12"></div>
                </ul>
              </div>
            </div>
            <div class="col-12"></div>
            <div class="col-9"></div>
            <div class="btn btn-info col-1 mx-auto" style="float: right;" @click="moveUserUpdate">정보수정</div>
            <div class="btn btn-info col-1" style="float: right;" @click="deleteUser">회원탈퇴</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdviseMe from '@/views/components/advise/AdviseMe.vue';
import { searchUser, signoutUser } from '@/api/auth';
import { fetchAdviseMe } from '@/api/advise';

export default {
  components: {
    AdviseMe,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      image: this.$store.state.image,
      value: [],
      advise: [],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_profile.jpg'),
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

    {
      const userData = this.$store.state.uuid;
      const { data } = await fetchAdviseMe(userData);

      this.advise = data.client.reverse();
      console.log(data);
    }
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
      // this.$store.commit('clearImage');
      localStorage.clear();
      sessionStorage.clear();
      $cookies.keys().forEach((cookie) => $cookies.remove(cookie));
      this.$router.push({ name: 'RegisterIndex' });
    },
  },
};
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  overflow: auto;
}
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
  width: auto;
  height: auto;
  margin-top: 2rem;
  border: 1px solid gray;
  border-radius: 1rem;
  padding: 0px;
}
#text-solid > div {
  background: skyblue;
  border: 2px solid skyblue;
  border-top-right-radius: 1rem;
  border-top-left-radius: 1rem;
  padding-left: 1rem;
}
#text-solid-intro {
  width: auto;
  height: auto;
  border: 1px solid skyblue;
  border-radius: 1rem;
  padding: 0px;
}
#text-solid-intro > div {
  background: skyblue;
  border: 2px solid skyblue;
  border-top-right-radius: 1rem;
  border-top-left-radius: 1rem;
  padding-left: 2rem;
}
#text-solid-margin {
  width: auto;
  height: auto;
  margin-top: 2rem;
  margin-right: 4.7rem;
  border: 1px solid gray;
  border-radius: 1rem;
  padding: 0px;
}
#text-solid-margin > div {
  background: skyblue;
  border: 2px solid skyblue;
  border-top-right-radius: 1rem;
  border-top-left-radius: 1rem;
  padding-left: 1rem;
  padding: auto;
}
#info-update {
  text-align: end;
}
hr {
  margin: 3px;
}
.padding {
  margin: 1rem;
}
#text-solids {
  background: white;
}
#text-solid-advise {
  height: auto;
  margin-top: 2rem;
  /* text-align: center; */
  border: 1px solid gray;
  border-radius: 1rem;
  padding-bottom: 2rem;
  padding: 0;
  /* background: whitesmoke; */
}
#text-solid-advise > div {
  background: skyblue;
  border: 2px solid skyblue;
  border-top-right-radius: 1rem;
  border-top-left-radius: 1rem;
  padding-left: 1rem;
}
</style>
