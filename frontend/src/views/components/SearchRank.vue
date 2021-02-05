<template>
  <div>
    <div v-if="loadCheck">
      <div class="list-type">
        <ol id="olid" style="list-style: none;">
          <div class="mt-10" v-for="item in items" :key="item.rank">
            <a class="box-solid" @click="moveSearch(item.word)" v-text="item.word"></a>
          </div>
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
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.list-type a {
  text-decoration: none;
  color: white !important;
  font-size: 17px;
  font-family: 'Nanum Gothic', sans-serif;
}
/* #img2 {
  filter: brightness(60%);
} */
a + h2 {
  color: aliceblue;
}
.box-solid {
  box-sizing: border-box;
  border: 1px solid white;
  border-radius: 1.5rem;
  padding: 1rem;
  width: 500px;
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
