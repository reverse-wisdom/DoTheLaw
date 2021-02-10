<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">추천 변호사 상세</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <div class="col-1 "></div>
            <div class="col-3 colum mx-auto">
              <img v-if="lawyer.image" class="col-12 r-7" id="profile" :src="lawyer.image" alt="" />
              <img v-else id="profile" class="col-12 r-7" src="@/assets/img/noimage.jpg" alt="noimage" />
            </div>
            <div class="col-8 row">
              <h1 class="col-12 r-4 mx-auto">변호사 {{ lawyer.name }}</h1>
              <md-button class="col-1" @click="writeAdvise">자문요청</md-button>
              <div class="col-11 mx-auto" id="text-solid-1">
                한줄소개
                <hr />
                {{ lawyer.introduction }}
              </div>
            </div>
            <div class="row mx-auto">
              <div class="col-5 mx-auto" id="text-solid-margin">
                관심분야
                <hr />
              </div>
              <div class="col-5 mx-auto" id="text-solid">
                잔화번호
                <hr />
                {{ lawyer.phone }}
              </div>
              <div class="col-5 mx-auto" id="text-solid-margin">
                경력
                <hr />
                {{ lawyer.career }}
              </div>
              <div class="col-5 mx-auto" id="text-solid">
                이메일
                <hr />
                {{ lawyer.email }}
              </div>
              <div class="col-11 mx-auto" id="text-solid-one">
                최근답변
                <hr />
                <div></div>
              </div>
            </div>
            <div id="map" ref="map" style="width: 100%; height: 400px; margin: 2rem;"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//   {
//   "uuid": 125,
//   "email": "lawyer24@ssafy.com",
//   "password": "$2a$10$GASjX.Qu.2i5NpVWlYeSluwDJF4gVcw3YDb.BKwdLBRDR3LLh6WT.",
//   "name": "lawyer24",
//   "phone": "010-1234-1234",
//   "role": "ROLE_LAWYER",
//   "joinDate": "2021-02-07T13:01:21.000+00:00",
//   "image": "string",
//   "career": "경력1",
//   "introduction": "자기소개",
//   "address": "경북 구미시 3공단 3로 302",
//   "dept": "싸피",
//   "chck": "Y"
// }
import { LawyerDetail } from '@/api/auth';
import { saveImage } from '@/api/service';
import axios from 'axios';
const GOOGLE_MAP_KEY = 'AIzaSyCcSBj7dF4tkNfeV7U2YzwdAupmh2GYpoc';

export default {
  bodyClass: 'profile-page',
  data() {
    return {
      lawyer: '',
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
    },
  },
  async created() {
    const email = this.$route.query.email;
    const res = await LawyerDetail(email);
    console.log(res);
    this.lawyer = res.data;

    const imgres = await saveImage(res.data.uuid, res.data.image);
    console.log(imgres);

    var query = res.data.address;
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
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    writeAdvise() {
      console.log(this.lawyer);
      const lawyerUuid = this.lawyer.uuid;
      this.$router.push({ name: 'AdviseWrite', query: { lawyerUuid: lawyerUuid } });
    },
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
};
</script>

<style></style>
