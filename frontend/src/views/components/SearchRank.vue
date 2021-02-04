<template>
  <div>
    <div v-if="loadCheck">
      <div class="list-type">
        <ol style="list-style: none; ">
          <li v-for="item in items" :key="item.rank">
            <a @click="moveSearch(item.word)" v-text="item.word"></a>
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
import { top } from '@/api/service';
import { Circle8 } from 'vue-loading-spinner'; // npm 스피너 컴포넌트
export default {
  components: { Circle8 },
  data() {
    return {
      items: [],
      loadCheck: false,
    };
  },
  async mounted() {
    this.loadCheck = false;
    const { data } = await top();
    // 랭킹 5등까지만
    for (let i = 0; i < 5; i++) {
      this.items.push({
        rank: i,
        word: data.items[i].word,
      });
    }
    this.loadCheck = true;
  },
  methods: {
    moveSearch(query) {
      this.$router.push({ name: 'search', query: { searchWord: query } });
    },
  },
};
</script>

<style lang="scss" scoped>
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
