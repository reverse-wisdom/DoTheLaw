<template>
  <div>
    <!-- v-dialog -->
    <v-dialog transition="dialog-bottom-transition" max-width="600">
      <template v-slot:activator="{ on, attrs }">
        <span class="rank-title" v-bind="attrs" v-on="on">
          <v-icon right dark>
            mdi-format-list-numbered
          </v-icon>
          검색어 순위
        </span>
      </template>
      <template v-slot:default="dialog">
        <v-card color="rgba(78, 29, 20, 0.8)">
          <v-toolbar color="gray" dark>검색어 랭킹</v-toolbar>
          <v-card-text>
            <div class="list-type2">
              <ol id="olid" style="list-style: none;">
                <div class="mt-10" v-for="item in items" :key="item.rank">
                  <li><a class="box-solid" @click="moveSearch(item.word)" v-text="item.word"></a></li>
                </div>
              </ol>
            </div>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn text @click="dialog.value = false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>

    <!-- <br /> -->
    <div class="text-center" style=" display:inline-flex;">
      <v-carousel style="display:inline-block;" hide-delimiters cycle interval="3000" class="rank_outter" vertical height="100px" :prev-icon="false" :next-icon="false">
        <v-carousel-item class="rank  " v-for="item in items" :key="item.rank">
          <v-card class="text-center" elevation="2" color="rgba(255, 255, 255, 0.1)" width="200px">
            <a @click="moveSearch(item.word)" class="rank">{{ item.rank }}. {{ item.word }}</a>
            &nbsp;
          </v-card>
        </v-carousel-item>
      </v-carousel>

      <div v-if="loadCheck">
        <!-- <v-carousel hide-delimiters cycle interval="3000" class="rank_outter" vertical height="200px" :prev-icon="false" :next-icon="false"> -->
      </div>
      <div v-else class="md-layout-item md-size-10 mx-auto">
        <br />
        <br />
        <br />
        <circle8></circle8>
      </div>
    </div>
  </div>
</template>

<script>
import { top } from '@/api/service';
import { Circle8 } from 'vue-loading-spinner'; // npm 스피너 컴포넌트
import { Modal } from '@/components';
export default {
  components: { Circle8, Modal },
  data() {
    return {
      items: [],
      loadCheck: false,
    };
  },
  async mounted() {
    this.loadCheck = false;
    const { data } = await top();
    // 랭킹 7등까지만
    for (let i = 0; i < 7; i++) {
      this.items.push({
        rank: i + 1,
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

.rank {
  text-decoration: none;
  transition: all 0.2s ease-in-out;
  text-decoration: none;
  color: white !important;
  font-size: 30px;
  font-family: 'Nanum Gothic', sans-serif;
  padding-top: 30px;
  display: 'inline-block';
}
.rank-title {
  font-size: 2.3rem;
  color: white;
  font-weight: bold;
}
.rank_outter {
  padding-bottom: 30px;
}

.list-type2 {
  width: 400px;
  margin: 0 auto;
}

.list-type2 ol {
  counter-reset: li;
  list-style: none;
  *list-style: decimal;
  font-size: 15px;
  font-family: 'Raleway', sans-serif;
  padding: 0;
  margin-bottom: 4em;
}

.list-type2 ol ol {
  margin: 0 0 0 2em;
}

.list-type2 a {
  position: relative;
  display: block;
  padding: 0.4em 0.4em 0.4em 2em;
  *padding: 0.4em;
  margin: 0.5em 0;
  background: rgba(255, 255, 255, 0.3);
  // border: #fc756f;

  text-decoration: none;
  transition: all 0.2s ease-in-out;
  text-decoration: none;
  color: white !important;
  font-size: 17px;
  font-family: 'Nanum Gothic', sans-serif;
}

.list-type2 a:hover {
  background: #d6d4d4;
  text-decoration: none;
  transform: scale(1.1);
}

.list-type2 a:before {
  content: counter(li);
  counter-increment: li;
  position: absolute;
  left: -1.3em;
  top: 50%;
  margin-top: -1.3em;
  background: #fc756f;
  height: 2em;
  width: 2em;
  line-height: 2em;
  border: 0.1em solid #fff;
  text-align: center;
  font-weight: bold;
  color: #fff;
}
</style>
