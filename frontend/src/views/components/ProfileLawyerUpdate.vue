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
              <h2 class="col-12 my-0 py-0 pl-10 mx-auto">{{ value.name }}</h2>
            </div>
            <div class="col-8 row" id="content-sort">
              <div class="col-6 mt-10" v-if="loadCheck">
                <v-file-input type="file" name="uploadFile" accept="image/png, image/jpeg, image/bmp" placeholder="자격증 인증" ref="ocr" @change="check"></v-file-input>
              </div>
              <div v-else class="md-layout-item md-size-4 mx-auto">
                <br />
                <circle8></circle8>
                <br />
              </div>
              <div class="col-11 mx-auto text-solid-1">
                <div style="color:white;">
                  한줄 소개
                </div>
                <input class="col-12 text-solid-input-intro" v-model="value.introduction" />
              </div>
            </div>
            <div class="row ml-10">
              <div class="col-5 mx-auto text-solid-margin">
                <div style="color:white;">
                  관심분야
                </div>

                <input class="col-12 text-solid-input" v-model="value.bailiwick" />
              </div>
              <div class="col-5 mx-auto text-solid">
                <div style="color:white;">
                  전화번호
                </div>

                <input class="col-12 text-solid-input" v-model="value.phone" />
              </div>
              <div class="col-5 mx-auto text-solid-margin">
                <div style="color:white;">
                  경력
                </div>

                <textarea class="col-12 text-solid-input" v-model="value.career" style="height:120px" />
              </div>
              <div class="col-5 mx-auto text-solid">
                <div style="color:white;">
                  이메일
                </div>

                {{ value.email }}
              </div>
              <div class="col-11 mx-auto text-solid-one">
                최근답변
              </div>

              <md-field class="mt-10">
                <label>주소입력</label>
                <md-input id="address" type="text" ref="address" v-model="value.address"></md-input>
              </md-field>
              <v-btn color="rgb(68, 114, 148)" dark style="margin: auto" @click="searchMap">주소로검색</v-btn>
              <div id="map" ref="map" class="mx-auto" style="width: 100%; height: 400px; margin: 2rem;"></div>
              <div class="col-11"></div>
              <div class="btn btn-info col-1" style="float: right;" @click="LawyerUpdate">수정완료</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const GOOGLE_MAP_KEY = 'AIzaSyCcSBj7dF4tkNfeV7U2YzwdAupmh2GYpoc';
import axios from 'axios';
import { Circle8 } from 'vue-loading-spinner';
import { searchLawyer, editLawyer } from '@/api/auth';

export default {
  components: { Circle8 },
  bodyClass: 'profile-page',
  data() {
    return {
      files: null,
      ocr: null,
      loadCheck: true,
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
        check: this.value.check,
      };

      const res = await editLawyer(userdata);
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
    async handleFilesUpload(file) {
      this.files = file;
      if (file) {
        var frm = new FormData();
        frm.append('file', file);

        await axios.post(`api/member/image/update/${this.$store.state.uuid}`, frm, { 'Content-Type': 'multipart/form-data', headers: { 'x-auth-token': this.$store.state.token } }).then((response) => {
          this.$forceUpdate();
        });
      }
    },
    async check(ocr) {
      this.ocr = ocr;
      this.loadCheck = false;
      if (ocr) {
        var form = new FormData();
        form.append('image', ocr);
        await axios
          .post(`api/ocr?uuid=${this.value.uuid}`, form, { 'Content-Type': 'multipart/form-data', headers: { 'x-auth-token': this.$store.state.token } })
          .then((response) => {
            this.$swal({
              icon: 'success',
              title: '인증성공',
            });
            this.value.check = 'Y';
            this.loadCheck = true;
            this.$forceUpdate();
          })
          .catch((err) => {
            this.$swal({
              icon: 'error',
              title: '인증실패',
            });
            this.value.check = 'N';
            this.loadCheck = true;
          });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#profile {
  border-radius: 70%;
}

#image-change-button {
  margin-top: 3px;
}
#button-sort {
  justify-items: center;
}
.text-solid {
  width: 200px;
  height: auto;
  padding: 0;

  margin-top: 2rem;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  > div {
    background: rgb(68, 114, 148);
    border: 2px solid rgb(68, 114, 148);
    padding-left: 1rem;
  }
}

.text-solid-1 {
  width: auto;
  height: auto;
  padding: 0;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  > div {
    background: rgb(68, 114, 148);
    border: 2px solid rgb(68, 114, 148);
    padding-left: 1rem;
  }
}

.text-solid-input {
  border: 1px solid black;
  width: 90%;
  height: auto;
  margin: 1rem 1rem 1rem 1rem;
}
.text-solid-input-intro {
  border: 1px solid black;
  width: 90%;
  height: auto;
  margin: 2rem 1rem 1rem 1rem;
}
.text-solid-margin {
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.08);
  width: auto;
  height: auto;
  padding: 0;
  margin-top: 2rem;
  margin-right: 4.7rem;
  > div {
    background: rgb(68, 114, 148);
    border: 2px solid rgb(68, 114, 148);
    padding-left: 1rem;
  }
}

.text-solid-one {
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
