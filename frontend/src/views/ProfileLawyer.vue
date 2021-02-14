<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">마이페이지</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <div class="col-1 "></div>
            <div class="col-3 colum mx-auto text-center">
              <img v-if="$store.state.uuid" class="col-12 r-10" id="profile" :src="'/api/member/image/' + lawyer.uuid + '/512'" alt="" />
              <img v-else id="profile" class="col-12 r-10" src="@/assets/img/noimage.jpg" alt="noimage" />
              <h2>{{ lawyer.name }}</h2>
            </div>
            <div class="col-8 row">
              <div class="col-12 r-4 mx-auto">
                <chart :list="values" style="height: 250px"></chart>
              </div>
              <br />

              <div class="col-11 mx-auto" id="text-solid-intro">
                <div>
                  한줄소개
                </div>
                <hr />
                <span class="padding">
                  {{ lawyer.introduction }}
                </span>
              </div>
            </div>
            <div class="row mx-auto">
              <div class="col-5 mx-auto" id="text-solid-margin">
                <div>
                  관심분야
                </div>
                <hr />
                <span class="padding">
                  {{ lawyer.bailiwick }}
                </span>
              </div>
              <div class="col-5 mx-auto" id="text-solid">
                <div>
                  전화번호
                </div>
                <hr />
                <span class="padding">
                  {{ lawyer.phone }}
                </span>
              </div>
              <div class="col-5 mx-auto" id="text-solid-margin">
                <div>
                  경력
                </div>
                <hr />
                <span class="padding">
                  {{ lawyer.career }}
                </span>
              </div>
              <div class="col-5 mx-auto" id="text-solid">
                <div>
                  이메일
                </div>
                <hr />
                <span class="padding">
                  {{ lawyer.email }}
                </span>
              </div>
              <div class="col-11 mx-auto" id="text-solid-advise">
                <div>
                  최근답변
                </div>
                <hr />
                <ul>
                  <AdviseLawyer id="text-solids" v-for="(data, idx) in advise" :key="idx" :data="data" />
                </ul>
              </div>
            </div>
            <div class="col-3 d-flex ml-5 mt-10">
              <h3 id="hexagon">위치</h3>
            </div>
            <div id="map" ref="map" class="col-12 mx-auto" style="width: 100%; height: 400px; margin: 2rem;"></div>
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
import Chart from '@/views/components/Chart.vue';
import { fetchAdviseLawyer } from '@/api/advise';
import { searchLawyer, signoutUser } from '@/api/auth';
export default {
  components: {
    AdviseLawyer,
    Chart,
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
      values: [],
      lawyer: [],
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
    const { data } = await searchLawyer(email);

    this.lawyer = data;
    this.$store.commit('setLawuuid', data.uuid);
    console.log('회원정보', this.lawyer);

    {
      const userData = this.$store.state.lawuuid;
      const { data } = await fetchAdviseLawyer(userData);

      this.advise = data.reverse();
      console.log(data);
    }

    var query = this.lawyer.address;
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
      const res = await signoutUser(this.lawyer.uuid);
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
  overflow: auto;
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
#hexagon {
  color: white;
  text-align: center;
  width: 250px;
  height: auto;
  background: skyblue;
  position: relative;
}
#hexagon:before {
  content: '';
  position: absolute;
  top: -20px;
  left: 0;
  width: 250px;
  height: 0;
  border-left: 50px solid transparent;
  border-right: 50px solid transparent;
  border-bottom: 20px solid skyblue;
}
#hexagon:after {
  content: '';
  position: absolute;
  bottom: -20px;
  left: 0;
  width: 250px;
  height: 0;
  border-left: 50px solid transparent;
  border-right: 50px solid transparent;
  border-top: 20px solid skyblue;
}
</style>
