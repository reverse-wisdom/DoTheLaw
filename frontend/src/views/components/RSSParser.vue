<template>
  <div calss="md-layout">
    <!-- 뉴스 업데이트한(가져온) 시간 -->
    <div v-if="loadCheck">
      <md-button class="md-info" disabled id="rss_time">{{ refresh_time }}</md-button>
      <div class="list-type">
        <ol style="list-style: none; ">
          <!-- 파싱한 데이터중 7개의 뉴스제목과 링크를 들고옴 -->
          <li v-for="index in 7" :key="index">
            <a class="newslink" :href="news[index].link" target="_blank">{{ news[index].title }}</a>
          </li>
        </ol>
      </div>
    </div>
    <div v-else class="md-layout-item md-size-10 mx-auto">
      <br />
      <br />
      <br />
      <circle8></circle8>
    </div>
  </div>
</template>

<script>
import { Circle8 } from 'vue-loading-spinner'; // npm 스피너 컴포넌트
// import { newsParsing } from '@/api/service';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_API_URL;

export default {
  components: { Circle8 },
  data() {
    return {
      news: [{ title: '', link: '' }],
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

  created() {
    // RSS 뉴스 불러오기

    // 파싱
    // const { data } = newsParsing(); // 왜안될까
    // console.log(data);

    this.loadCheck = false;
    axios.get(SERVER_URL + 'api/rss/news').then(({ data }) => {
      for (let i = 0; i < data.length; i++) {
        this.news.push({
          title: data[i].title,
          link: data[i].link,
        });
        this.loadCheck = true;
        console.log('rss 로딩 완료');
      }
    });
  },
};
</script>

<style lang="scss" scoped>
.newslink {
  color: black;
  font-weight: bold;
}
#rss_time {
  font-size: 15px;
  margin-bottom: 10px;
}

// 리스트 디자인
.list-type {
  width: 100%;
  margin: 0 auto;
}
.list-type ol {
  list-style-type: none;
  list-style-type: decimal;
  margin: 0;
  margin-left: 1em;
  padding: 0;
  counter-reset: li-counter;
}
.list-type ol li {
  position: relative;
  margin-bottom: 1.5em;
  padding: 0.5em;
  background-color: #f0d756;
  padding-left: 58px;
}

.list-type a {
  text-decoration: none;
  color: black !important;
  font-size: 17px;
  font-family: 'Nanum Gothic', sans-serif;
}

.list-type li:hover {
  box-shadow: inset -3em 0 #6cd6cc;
  -webkit-transition: box-shadow 0.5s; /* For Safari 3.1 to 6.0 */
  transition: box-shadow 0.5s;
}

.list-type ol li:before {
  position: absolute;
  top: -0.3em;
  left: -0.2em;
  width: 1.8em;
  height: 1.2em;
  font-size: 2em;
  line-height: 1.2;
  font-weight: bold;
  text-align: center;
  color: white;
  background-color: #6cd6cc;
  transform: rotate(-20deg);
  -ms-transform: rotate(-20deg);
  -webkit-transform: rotate(-20deg);
  z-index: 99;
  overflow: hidden;
  content: counter(li-counter);
  counter-increment: li-counter;
}
</style>
