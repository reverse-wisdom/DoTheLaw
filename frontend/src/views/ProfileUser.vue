<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="row">
            <div class="col-4 row ml-5">
              <img v-if="value.image" id="profile" class="col-12 r-7 mx-auto" :src="value.image" alt="" />
              <img v-else id="profile" class="col-12 r-7 mx-auto" src="@/assets/img/noimage.jpg" alt="noimage" />
              <div class="r-2 mx-auto" id="button-sort">
                <button id="image-change-button">
                  이미지 변경
                </button>
              </div>
            </div>
            <div class="col-1"></div>
            <div class="col-7 row" id="content-sort">
              <h1 class="col-12 r-4 mx-auto">사용자 {{ $store.state.name }}</h1>
              <div class="col-11 mx-auto" id="text-solid-1">
                한줄소개
                <hr />
              </div>
              <div class="col-5 mx-auto" id="text-solid">
                전화번호
                <hr />
              </div>
              <div class="col-5 mx-auto" id="text-solid">
                전화번호
                <hr />
              </div>
            </div>
            <div class="row ml-10">
              <div class="col-12 mx-auto" id="text-solid-one">
                최근 문의
                <hr />
              </div>
              <div class="col-11 mx-auto"></div>
              <div class="btn btn-info col-1 mt-5 mx-auto" style="float: right;" @click="moveUserUpdate">정보수정</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { searchUser } from '@/api/auth';
export default {
  components: {},
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
