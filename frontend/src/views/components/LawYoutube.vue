<template>
  <div class="md-layout">
    <div style="margin: auto">
      <!-- 뉴스 업데이트한(가져온) 시간 -->
      <div v-if="loadCheck">
        <br />

        <md-button class="md-raised" disabled id="rss_time">{{ refresh_time }}</md-button>
        <br />
        <div class="list-type">
          <v-container fill-height>
            <v-layout column fill-height>
              <v-layout row style="flex: 1 1 50%; overflow: hidden">
                <div v-for="(video, index) in videos.slice(1, 3)" :key="index" class="ma-3">
                  <a :href="'https://www.youtube.com/watch?v=' + video.id" target="_blank">
                    <v-card class="mx-auto" max-width="400">
                      <v-img class="card-img-top white--text align-end" height="200px" :src="video.thumbnail" alt="YouTube thumbnail" />
                      <v-card-title class="txt_line">{{ video.title }}</v-card-title>

                      <h6 class="card-subtitle mb-2 text-muted">생성일 | {{ video.publishedAt | formatDate }}</h6>
                      <!-- <p class="card-text">{{ video.description }}</p> -->
                    </v-card>
                  </a>
                </div>
              </v-layout>
              <v-layout row style="flex: 1 1 50%; overflow: hidden">
                <div v-for="(video, index) in videos.slice(3, 5)" :key="index" class="ma-3">
                  <a :href="'https://www.youtube.com/watch?v=' + video.id" target="_blank">
                    <v-card class="mx-auto" max-width="400">
                      <v-img class="card-img-top white--text align-end" height="200px" :src="video.thumbnail" alt="YouTube thumbnail" />
                      <v-card-title class="txt_line">{{ video.title }}</v-card-title>

                      <h6 class="card-subtitle mb-2 text-muted">생성일 | {{ video.publishedAt | formatDate }}</h6>
                      <!-- <p class="card-text">{{ video.description }}</p> -->
                    </v-card>
                  </a>
                </div>
              </v-layout>
            </v-layout>
          </v-container>
        </div>
      </div>
      <div v-else class="md-layout-item md-size-4 mx-auto">
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <circle8></circle8>
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
      </div>
    </div>
  </div>
</template>

<script>
import { Circle8 } from 'vue-loading-spinner'; // npm 스피너 컴포넌트
import { getTodayYoutube } from '@/api/service';
import moment from 'moment';

export default {
  components: { Circle8 },
  data() {
    return {
      videos: [{ title: '', thumbnail: '', link: '' }],
      loadCheck: false,
      refresh_time:
        '업데이트 시간: ' +
        new Date().getFullYear() +
        '/' +
        (new Date().getMonth() + 1) +
        '/' +
        new Date().getDate() +
        ' 시간:' +
        new Date().getHours() +
        '시' +
        new Date().getMinutes() +
        '분' +
        new Date().getSeconds() +
        '초',
    };
  },
  async mounted() {
    // RSS 뉴스 불러오기

    // 파싱
    const { data } = await getTodayYoutube();
    console.log(data);

    if (data) {
      this.loadCheck = false;
      for (let i = 0; i < data.length; i++) {
        this.videos.push(
          data[i]
          // title: data[i].title,
          // link: data[i].link,
          // thumbnail: data[i].thumbnail,
        );
        this.loadCheck = true;
      }
    }
  },
  filters: {
    formatDate(value) {
      if (!value) return '';
      return moment(value).format('MM/DD/YYYY hh:mm');
    },
  },
};
</script>

<style lang="scss" scoped>
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.list-type a {
  text-decoration: none;
  color: white !important;
  font-size: 17px;
  font-family: 'S-CoreDream-6Bold';
}
.txt_line {
  width: 300px;
  padding: 0 5px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

#rss_time {
  font-size: 15px;
  margin-bottom: 10px;
}
ul,
ol {
  /*margin: 0;padding: 0;*/
  list-style: none;
}
li {
  // background: #37bc9b;
  background: rgb(68, 114, 148);
  color: #fff;
  counter-increment: myCounter;
  margin: 0 0 30px 0;
  padding: 13px;
  position: relative;
  top: 1em;
  border-radius: 0em 2px 1em 1em;
  padding-left: 2em;
  font-size: 1.2em;
  font-family: Constantia;
}
li:before {
  content: counter(myCounter, decimal-leading-zero);
  display: inline-block;
  text-align: center;
  font-size: 2em;
  line-height: 1.3em;
  background-color: rgb(41, 64, 82);
  // background-color: rgb(68, 114, 148);
  padding: 10px;
  font-weight: bold;
  position: absolute;
  top: 0;
  left: -40px;
  border-radius: 50%;
  font-family: exo;
}

li:nth-child(even) {
  // background-color: #434a54;
  background-color: rgb(143, 188, 219);
}
</style>
