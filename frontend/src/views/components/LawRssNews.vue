<template>
  <div class="md-layout">
    <!-- 뉴스 업데이트한(가져온) 시간 -->
    <div v-if="loadCheck">
      <br />

      <md-button class="md-info" disabled id="rss_time">{{ refresh_time }}</md-button>
      <br />
      <div class="list-type">
        <ol id="olid" style="list-style: none;">
          <!-- 파싱한 데이터중 7개의 뉴스제목과 링크를 들고옴 -->
          <div class="mt-10" v-for="index in 7" :key="index">
            <a class="box-solid" :href="news[index].link" target="_blank">{{ news[index].title }}</a>
          </div>
        </ol>
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
</template>

<script>
import { Circle8 } from 'vue-loading-spinner'; // npm 스피너 컴포넌트
import { newsParsing } from '@/api/service';

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
  async mounted() {
    // RSS 뉴스 불러오기

    // 파싱
    const { data } = await newsParsing();
    console.log(data);

    if (data['items']) {
      this.loadCheck = false;
      for (let i = 0; i < data.items.length; i++) {
        this.news.push({
          title: data.items[i].title,
          link: data.items[i].link,
        });
        this.loadCheck = true;
      }
    }
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
  font-family: 'Nanum Gothic', sans-serif;
}
#rss_time {
  font-size: 15px;
  margin-bottom: 10px;
}
a + h2 {
  color: aliceblue;
}
.box-solid {
  box-sizing: border-box;
  border: 1px solid white;
  border-radius: 1.5rem;
  padding: 1rem;
  width: 200px;
}
#title-solid {
  box-sizing: content-box;
  border: 5px solid white;
  font-size: 50px;
  border-radius: 1.5rem;
  padding: 1rem;
  color: white;
  font-weight: bold;
}
#olid {
  box-sizing: content-box;
}
li {
  list-style: none;
}
</style>
