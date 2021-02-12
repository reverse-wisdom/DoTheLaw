<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="row">
            <div class="col-1"></div>
            <div class="col-3 row mx-auto">
              <img v-if="$store.state.uuid" class="col-12 r-10" id="profile" :src="'/api/member/image/' + $store.state.uuid + '/512?t=' + new Date().getTime()" alt="" />
              <img v-else id="profile" class="col-12 r-10" src="@/assets/img/noimage.jpg" alt="noimage" />
              <!-- <img id="profile" class="col-12 r-10" :src="'/api/member/image/' + $store.state.uuid + '/256'" alt="noimage" /> -->
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
            </div>
            <div class="col-8 row" id="content-sort">
              <h1 class="col-12 r-4">변호사 {{ value.name }}</h1>
              <div class="col-11 mx-auto " id="text-solid-1">
                한줄소개
                <hr />
                <input class="col-12 text-solid-input" v-model="value.introduction" />
              </div>
            </div>
            <div class="row ml-10">
              <div class="col-5 mx-auto" id="text-solid-margin">
                관심분야
                <hr />
                <input class="col-12 text-solid-input" v-model="value.bailiwick" />
              </div>
              <div class="col-5 mx-auto " id="text-solid">
                전화번호
                <hr />
                <input class="col-12 text-solid-input" v-model="value.phone" />
              </div>
              <div class="col-5 mx-auto" id="text-solid-margin">
                경력
                <hr />
                <textarea class="col-12 text-solid-input" v-model="value.career" style="height:60px" />
              </div>
              <div class="col-5 mx-auto " id="text-solid">
                이메일
                <hr />
                {{ value.email }}
              </div>
              <div class="col-11 mx-auto" id="text-solid-one">
                최근답변
                <hr />
              </div>
            </div>

            <md-field class="mt-10">
              <label>주소입력</label>
              <md-input id="address" type="text" ref="address" v-model="value.address"></md-input>
            </md-field>
            <md-button class="md-info" style="margin: auto" @click="searchMap">주소로검색</md-button>
            <div id="map" ref="map" class="mx-auto" style="width: 100%; height: 400px; margin: 2rem;"></div>
            <div class="col-11"></div>
            <div class="btn btn-info col-1" style="float: right;" @click="LawyerUpdate">수정완료</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const GOOGLE_MAP_KEY = 'AIzaSyCcSBj7dF4tkNfeV7U2YzwdAupmh2GYpoc';
import axios from 'axios';
import { searchLawyer, editLawyer } from '@/api/auth';
import { imageUpload } from '@/api/service';
// import { searchLawyerAdvise } from '@/api/advise';

export default {
  components: {},
  bodyClass: 'profile-page',
  data() {
    return {
      files: null,
      image: this.$store.state.image,
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
  },
  async created() {
    var query = '경북 구미시 3공단 3로 302';
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
    const email = this.$store.state.email;
    const { data } = await searchLawyer(email);
    this.value = data;
    console.log('회원정보', this.value);

    // const lawyerId = this.$store.state.uuid;
    // const res = await searchLawyerAdvise(lawyerId);
    // console.log('자문', res);
    // this.advise = res.data;
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
    async LawyerUpdate() {
      const userdata = {
        address: this.value.address,
        bailiwick: this.value.bailiwick,
        career: this.value.career,
        dept: this.value.dept,
        introduction: this.value.introduction,
        name: this.value.name,
        password: this.$store.state.password,
        phone: this.value.phone,
        uuid: this.$store.state.uuid,
        role: 'LAWYER',
      };

      // var FormData = require('form-data');
      // var form = new FormData();

      // if (this.files != null) {
      //   form.append('file', this.files);
      // }

      // // axios.post(`api/mamber/image/update/${this.value.uuid}`, form, { 'Content-Type': 'multipart/form-data', headers: this.$store.state.token }).then(function(response) {
      // //   console.log(response);
      // // });
      // const imageres = await imageUpload(this.value.uuid, form);
      // console.log(imageres);

      console.log('인풋데이터 확인', userdata);
      const res = await editLawyer(userdata);
      console.log(res);
      this.$router.push({ name: 'profileLawyer' });
    },
    searchMap() {
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
    },
    handleFilesUpload(file) {
      this.files = file;
      if (file) {
        var frm = new FormData();
        frm.append('file', file);

        axios
          .post(`api/member/image/update/${this.$store.state.uuid}`, frm, { 'Content-Type': 'multipart/form-data', headers: { 'x-auth-token': this.$store.state.token } })
          .then((response) => {
            // console.log('프로필 업로드 성공', response);
            this.$forceUpdate();
          })
          .catch((err) => console.log(err));
        // const imageres = await imageUpload(this.value.uuid, form);
        // console.log(imageres);
      }
    },
  },
};
</script>

<style>
#profile {
  border-radius: 70%;
}

#image-change-button {
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
  height: auto;
}
.text-solid-input {
  border: 1px solid black;
  width: 100%;
  height: auto;
  margin-bottom: 5px;
}
#text-solid-margin {
  border: 1px solid black;
  width: 200px;
  height: auto;
  margin-top: 2rem;
  margin-right: 4.7rem;
}
#text-solid-one {
  border: 1px solid black;
  height: auto;
  margin-top: 2rem;
}
#info-update {
  text-align: end;
}
hr {
  margin: 5px;
}
</style>
