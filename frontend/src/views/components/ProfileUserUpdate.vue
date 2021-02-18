<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor" style="font-weight:bold">사용자 수정</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <div class="col-3 colum mx-auto text-center">
              <img v-if="$store.state.uuid" class="col-12 r-10" id="profile" :src="'/api/member/image/' + $store.state.uuid + '/512?t=' + new Date().getTime()" alt="" />
              <img v-else id="profile" class="col-12 r-10" src="@/assets/img/noimage.jpg" alt="noimage" />
              <div class="r-2 mx-auto" id="button-sort">
                <v-file-input
                  type="file"
                  name="uploadFile"
                  accept="image/png, image/jpeg, image/bmp"
                  placeholder="회원사진"
                  ref="files"
                  prepend-icon="mdi-camera"
                  @change="handleFilesUpload"
                ></v-file-input>
              </div>
              <h2>{{ value.name }}</h2>
            </div>
            <div class="col-1 "></div>
            <div class="col-8 row" id="content-sort">
              <div class="row">
                <div class="col-12 mx-auto" id="text-solid">
                  <div style="height:50px" class="pt-3 divId">
                    이메일
                  </div>
                  <div class="padding">
                    {{ value.email }}
                  </div>
                </div>
                <div class="col-12 mx-auto" id="text-solid">
                  <div style="height:50px" class="pt-3 divId">
                    전화번호
                  </div>
                  <input class="col-12 text-solid-input" v-model="value.phone" />
                </div>
              </div>
            </div>
            <div class="row mt-5">
              <div class="col-11"></div>
              <div class="btn btn-info col-1" style="float:right" @click="UserUpdata">수정완료</div>
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
import axios from 'axios';

export default {
  bodyClass: 'profile-page',
  components: {
    AdviseMe,
  },
  data() {
    return {
      files: null,
      image: this.$store.state.image,
      value: [],
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
  },
  methods: {
    async UserUpdata() {
      const userData = {
        name: this.value.name,
        phone: this.value.phone,
        uuid: this.value.uuid,
      };
      const res = await editUser(userData);
      this.$router.push({ name: 'profileUser' });
    },
    async handleFilesUpload(file) {
      this.files = file;
      if (file) {
        var frm = new FormData();
        frm.append('file', file);

        await axios
          .post(`api/member/image/update/${this.$store.state.uuid}`, frm, { 'Content-Type': 'multipart/form-data', headers: { 'x-auth-token': this.$store.state.token } })
          .then((response) => {
            this.$forceUpdate();
          })
          .catch((err) => console.log(err));
      }
    },
  },
};
</script>

<style scoped>
#profile {
  border-radius: 70%;
}
.text-solid-input {
  width: 90%;
  height: auto;
  margin: 1rem;
  border: 1px solid black;
}
#text-solid {
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  width: auto;
  height: auto;
  margin-top: 2rem;
  padding: 0;
}
#text-solid .divId {
  background: skyblue;
  padding-left: 1rem;
}
.padding {
  margin: 1rem 1rem 0 2rem;
  font-size: 20px;
}
</style>
