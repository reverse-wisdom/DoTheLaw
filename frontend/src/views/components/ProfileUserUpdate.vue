<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">사용자 수정폼</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <div class="col-1"></div>
            <div class="col-3 colum mx-auto">
              <img v-if="value.image" class="col-12 r-7 mx-auto" id="profile" :src="value.image" alt="" />
              <img v-else id="profile" class="col-12 r-7 mx-auto" src="@/assets/img/noimage.jpg" alt="noimage" />
            </div>
            <div class="col-8 row" id="content-sort">
              <h1 class="col-12 r-4">
                사용자
                <input class="col-12 text-solid-input" v-model="value.name" />
              </h1>
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
                <input class="col-12 text-solid-input" v-model="value.phone" />
              </div>
              <div class="col-11 mx-auto" id="text-solid-one">
                최근 자문요청
                <AdviseMe />
              </div>
            </div>
            <div class="row mt-5">
              <div class="col-9"></div>
              <div class="btn btn-info col-1 mr-5" @click="UserUpdata">수정완료</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { searchUser, editUser } from '@/api/auth';
import AdviseMe from '@/views/components/advise/AdviseMe.vue';
export default {
  bodyClass: 'profile-page',
  components: {
    AdviseMe,
  },
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
    async UserUpdata() {
      const userData = {
        name: this.value.name,
        phone: this.value.phone,
        uuid: this.value.uuid,
      };
      const res = await editUser(userData);
      console.log(res);
      this.$router.push({ name: 'profileUser' });
    },
  },
};
</script>

<style scoped>
.text-solid-input {
  border: 1px solid black;
  width: 100%;
  height: auto;
  margin-bottom: 5px;
}
</style>
