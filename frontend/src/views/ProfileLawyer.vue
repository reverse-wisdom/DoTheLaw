<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">마이페이지</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <div class="col-8">
              <div class="col-11 mx-auto" id="text-solid-one">
                최근답변
                <AdviseLawyer v-for="(data, idx) in advise" :key="idx" :data="data" />
                <hr />
              </div>
            </div>
            <div class="col-4 colum mx-auto" id="text-solid-one">
              <img v-if="$store.state.uuid" class="col-12 r-6" id="profile" :src="'/api/member/image/' + value.uuid + '/512'" alt="" />
              <img v-else id="profile" class="col-12 r-6" src="@/assets/img/noimage.jpg" alt="noimage" />
              <div class="r-2 mx-auto"></div>

              <div class="col-12 row" id="content-sort">
                <hr />
                <h2 class="col-12 r-4">변호사 {{ value.name }}</h2>
                <div class="col-11 mx-auto" id="">
                  <hr />
                  한줄소개
                  <hr class="mb-3" />
                  {{ value.introduction }}
                </div>
                <div class="col-5 mx-auto" id="">
                  <hr />
                  관심분야
                  <hr class="mb-3" />
                  {{ value.bailiwick }}
                </div>
                <div class="col-5 mx-auto" id="">
                  <hr />
                  전화번호
                  <hr class="mb-3" />
                  {{ value.phone }}
                </div>
                <div class="col-5 mx-auto" id="">
                  <hr class="mb-3" />
                  경력
                  <hr class="mb-3" />
                  {{ value.career }}
                </div>
                <div class="col-5 mx-auto" id="">
                  <hr />
                  이메일
                  <hr class="mb-3" />
                  {{ value.email }}
                </div>
              </div>
            </div>
            <div class="col-3 mt-4 mx-auto">찾아오시는 길</div>
            <div id="map" ref="map" class="col-9 mx-auto" style="width: 100%; height: 400px; margin: 2rem;"></div>
            <div class="col-9"></div>
            <div class="btn btn-info col-1 mx-auto" style="float: right;" @click="moveLawyerUpdate">정보수정</div>
            <div class="btn btn-info col-1" style="float: right;" @click="deleteLawyer">회원탈퇴</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const GOOGLE_MAP_KEY = 'AIzaSyCcSBj7dF4tkNfeV7U2YzwdAupmh2GYpoc';
import AdviseLawyer from '@/views/components/advise/AdviseLawyer.vue';
import axios from 'axios';
import { searchLawyer, signoutUser } from '@/api/auth';
import { fetchAdviseLawyer } from '@/api/advise';

export default {
  components: {
    AdviseLawyer,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      image: this.$store.state.image,
      address: null,
      map: null,
      mapState: window.mapState,
      multi: {
        lat: 37.5665734,
        lng: 126.978179,
      },
      value: [],
      advise: [],
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
    headerStyle() {
      return {
        image: `url(${this.header})`,
      };
    },
  },
  async created() {
    const email = this.$store.state.email;
    const { data } = await searchLawyer(email);

    this.value = data;
    this.$store.commit('setLawuuid', data.uuid);
    console.log('회원정보', this.value);

    var query = this.value.address;
    axios
      .get('https://maps.googleapis.com/maps/api/geocode/json?key=' + GOOGLE_MAP_KEY + '&address=' + query)
      .then(({ data }) => {
        let lat = data.results[0].geometry.location.lat;
        let lng = data.results[0].geometry.location.lng;
        this.map = new window.google.maps.Map(document.getElementById('map'), {
          center: {
            lat,
            lng,
          },
          zoom: 18,
        });
        new window.google.maps.Marker({
          label: query,
          position: {
            lat,
            lng,
          },
          map: this.map,
        });
      })
      .catch();

    const userData = this.$store.state.lawuuid;
    // console.log(userData);
    const res = await fetchAdviseLawyer(userData);
    // console.log(res.data);
    this.advise = res.data;
  },
  watch: {
    // watch를 통해 mounted가 실패하더라도 다시호출함 지도가 랜더링 안되는 현상 방지함
    'mapState.initMap'(value) {
      if (value) {
        if (this.mapState.initMap) {
          this.map = new window.google.maps.Map(document.getElementById('map'), {
            center: this.multi,
            zoom: 12,
          });
          new window.google.maps.Marker({
            position: this.multi,
            map: this.map,
            icon: require('@/assets/building.png'),
          });
        }
        console.log('load by watch');
      }
    },
  },
  methods: {
    moveLawyerUpdate() {
      this.$router.push({ name: 'profileLawyerUpdate' });
    },
    async deleteLawyer() {
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

<style scoped>
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
}
#text-solid-1 {
  width: auto;
  height: auto;
}
#text-solid-margin {
  width: auto;
  height: auto;
  margin-top: 2rem;
  margin-right: 4.7rem;
}
#text-solid-one {
  height: auto;
  margin-top: 2rem;
  text-align: center;
  background: whitesmoke;
}
#info-update {
  text-align: end;
}
hr {
  margin: 3px;
}
</style>
