<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title-solid text-center">추천 변호사 상세</h2>
        <hr class="div-hr" />
        <div class="container">
          <div class="row">
            <div class="col-1 "></div>
            <div class="col-3 colum mx-auto text-center">
              <img v-if="$store.state.uuid" class="col-12 r-10" id="profile" :src="'/api/member/image/' + lawyer.uuid + '/512'" alt="" />
              <img v-else id="profile" class="col-12 r-10" src="@/assets/img/noimage.jpg" alt="noimage" />
              <h2>{{ lawyer.name }}</h2>
              <div v-if="$store.state.role == 'USER'" style="">
                <md-button class="col-1" @click="writeAdvise">자문요청</md-button>
              </div>
            </div>
            <div class="col-8 row">
              <div class="col-12 r-4 mx-auto">
                <chart :lawyer_uuid="uuid" style="height: 250px"></chart>
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
                <div class="pt-3" style="height:50px">
                  경력
                </div>
                <hr />
                <p style="white-space: pre-line;" class="padding">{{ lawyer.career }}</p>
              </div>
              <div class="col-5 mx-auto">
                <div class="row-1"></div>
                <div class="col-12 mt-5" id="text-solid">
                  <div>
                    전화번호
                  </div>
                  <hr />
                  <span class="padding">
                    {{ lawyer.phone }}
                  </span>
                </div>
                <div class="row-1"></div>
                <div class="col-12 mt-5 " id="text-solid">
                  <div>
                    이메일
                  </div>
                  <hr />
                  <span class="padding">
                    {{ lawyer.email }}
                  </span>
                </div>
              </div>
              <div class="col-11 mx-auto mb-10" id="text-solid-advise">
                <div>
                  최근답변
                </div>
                <hr />
                <ul>
                  <AdviseLawyer id="text-solids" v-for="(data, idx) in advise" :key="idx" :data="data" />
                </ul>
              </div>
            </div>
            <div class="col-3 d-flex mt-5">
              <h3 id="hexagon">찾아오시는 길</h3>
            </div>
            <div id="map" ref="map" style="width: 100%; height: 400px; margin: 0 2rem 2rem 2rem;"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LawyerDetail } from '@/api/auth';
import { fetchAdviseLawyer } from '@/api/advise';
import { saveImage } from '@/api/service';

import axios from 'axios';
import AdviseLawyer from '@/views/components/advise/AdviseLawyer.vue';
import Chart from '@/views/components/Chart.vue';

const GOOGLE_MAP_KEY = 'AIzaSyCcSBj7dF4tkNfeV7U2YzwdAupmh2GYpoc';

export default {
  bodyClass: 'profile-page',
  components: {
    AdviseLawyer,
    Chart,
  },
  data() {
    return {
      values: [],
      lawyer: {},
      advise: [],
      search: '',
      uuid: '',
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_matching.png'),
    },
  },
  async created() {
    const email = this.$route.query.email;
    const res = await LawyerDetail(email);
    this.lawyer = res.data;
    this.uuid = res.data.uuid;
    const userData = this.uuid;
    const { data } = await fetchAdviseLawyer(userData);

    this.advise = data.reverse();

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
  border: 1px solid skyblue;
  margin-top: 3px;
}
#button-sort {
  justify-items: center;
}
#text-solid {
  width: auto;
  height: auto;
  margin-top: 2rem;
  /* border: 1px solid skyblue; */
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  padding: 0px;
}
#text-solid > div {
  background: skyblue;
  border: 2px solid skyblue;

  padding-left: 1rem;
}
#text-solid-intro {
  width: auto;
  height: auto;
  /* border: 1px solid skyblue; */
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  padding: 0px;
}
#text-solid-intro > div {
  background: skyblue;
  border: 2px solid skyblue;

  padding-left: 2rem;
}
#text-solid-margin {
  width: auto;
  height: auto;
  margin-top: 2rem;
  margin-right: 4.7rem;
  /* border: 1px solid skyblue; */
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  padding: 0px;
}
#text-solid-margin > div {
  background: skyblue;
  border: 2px solid skyblue;

  padding-left: 1rem;
  padding: auto;
}
#text-solid-advise {
  height: auto;
  margin-top: 2rem;
  /* text-align: center; */
  /* border: 1px solid skyblue; */
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  /* background: whitesmoke; */
  padding: 0;
  padding-bottom: 2rem;
}
#text-solid-advise > div {
  background: skyblue;
  border: 2px solid skyblue;

  padding: 1rem;
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
#hexagon {
  color: white;
  text-align: center;
  width: 250px;
  height: auto;
  background: skyblue;
  position: relative;
}
</style>
