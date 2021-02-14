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
            <div class="col-3 colum mx-auto text-center">
              <img v-if="$store.state.uuid" class="col-12 r-10" id="profile" :src="'/api/member/image/' + lawyer.uuid + '/512'" alt="" />
              <img v-else id="profile" class="col-12 r-10" src="@/assets/img/noimage.jpg" alt="noimage" />
              <h1>{{ lawyer.name }}</h1>
            </div>
            <div class="col-8 row">
              <div class="col-12 r-4 mx-auto">
                <chart :list="values" style="height: 250px"></chart>
              </div>
              <br />
              <div v-if="$store.state.role == 'USER'" style="text-align:right">
                <md-button class="col-1" @click="writeAdvise">자문요청</md-button>
              </div>

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
                <AdviseLawyer id="text-solids" v-for="(data, idx) in advise" :key="idx" :data="data" />
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
import { LawyerDetail } from '@/api/auth';
import { fetchAdviseLawyer } from '@/api/advise';
import { saveImage } from '@/api/service';

import axios from 'axios';
import AdviseLawyer from '@/views/components/advise/AdviseLawyer.vue';

const GOOGLE_MAP_KEY = 'AIzaSyCcSBj7dF4tkNfeV7U2YzwdAupmh2GYpoc';

export default {
  bodyClass: 'profile-page',
  components: {
    AdviseLawyer,
  },
  data() {
    return {
      values: [],
      lawyer: '',
      advise: [],
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
    this.lawyer = res.data;
    this.$store.commit('setLawuuid', res.data.uuid);
    {
      const userData = this.$store.state.lawuuid;
      const { data } = await fetchAdviseLawyer(userData);
      this.advise = data;
      console.log(advise);
    }

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
    try {
      const userData = this.$store.state.lawuuid;
      const { data } = await fetchAdviseLawyer(userData);
      for (let i = 0; i < data.length; i++) {
        this.values.push({
          matchingId: data[i].matchingId,
          lawyerUuid: data[i].lawyerUuid,
          category: data[i].category,
          uuid: data[i].uuid,
          title: data[i].title,
          state: data[i].state,
          name: data[i].name,
          reservationDate: this.$moment(data[i].reservationDate).format('llll'),
          createDate: this.$moment(data[i].createDate).format('llll'),
        });
      }
    } catch (err) {
      console.log(err);
    }
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
#profile {
  border-radius: 70%;
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
#text-solid-advise {
  height: auto;
  margin-top: 2rem;
  text-align: center;
  border: 1px solid gray;
  border-radius: 1rem;
  /* background: whitesmoke; */
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
</style>
