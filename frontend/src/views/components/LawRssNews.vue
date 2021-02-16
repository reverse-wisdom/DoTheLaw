<template>
  <div class="md-layout">
    <div style="margin: auto">
      <!-- 뉴스 업데이트한(가져온) 시간 -->
      <div v-if="loadCheck">
        <br />
        <div id="rss_time">{{ refresh_time }}</div>
        <br />
        <div class="list-type">
          <ul>
            <!-- 파싱한 데이터중 7개의 뉴스제목과 링크를 들고옴 -->
            <li v-for="index in 7" :key="index">
              <a class="box-solid hvr-pulse-grow" :href="news[index].link" target="_blank">{{ news[index].title }}</a>
            </li>
          </ul>
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
.list-type {
  flex-direction: column;
}
.list-type a {
  text-decoration: none;
  color: white !important;
  font-size: 17px;
  font-family: 'Nanum Gothic', sans-serif;
}
#rss_time {
  text-align: right;
  font-size: 0.9rem;
  margin-bottom: 0.1rem;
  color: white;
  font-family: 'Nanum Gothic', sans-serif;
  margin-right: -7rem;
}
ul,
ol {
  list-style: none;
  text-align: left;
}
li {
  padding: 10px;
}

a {
  border-style: none none none solid;
  padding-left: 30px;
}

// li {
//   background: rgb(68, 114, 148);
//   color: #fff;
//   counter-increment: myCounter;
//   margin: 0 0 30px 0;
//   padding: 13px;
//   position: relative;
//   top: 1em;
//   border-radius: 0em 2px 1em 1em;
//   padding-left: 2em;
//   font-size: 1rem;
//   font-family: Constantia;
// }
// li:before {
//   content: counter(myCounter, decimal-leading-zero);
//   display: inline-block;
//   text-align: center;
//   font-size: 2em;
//   line-height: 1.3em;
//   background-color: rgb(41, 64, 82);
//   // background-color: rgb(68, 114, 148);
//   padding: 10px;
//   font-weight: bold;
//   position: absolute;
//   top: 0;
//   left: -40px;
//   border-radius: 50%;
//   font-family: exo;
// }

// li:nth-child(even) {
//   // background-color: #434a54;
//   background-color: rgb(143, 188, 219);
// }
</style>
