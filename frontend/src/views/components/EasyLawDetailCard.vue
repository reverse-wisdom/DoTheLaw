<template>
  <div>
    <hr />
    <span class="font-weight-bold h4">
      &lt; 요약 &gt;
    </span>
    <v-img :src="image"></v-img>
    <span>{{ summary }}</span>
    <hr />
    <span class="font-weight-bold h4">
      &lt; 상세 &gt;
    </span>
    <span v-html="content"></span>
    <v-overlay :opacity="0" :value="overlay">
      <v-progress-circular color="deep-orange" indeterminate size="64" :width="7">
        Loading...
      </v-progress-circular>
    </v-overlay>
  </div>
</template>

<script>
import { getEasyLawDetail } from '@/api/easylaw.js';
function setBookmarkCookiess(data) {
  return false;
}
export default {
  data() {
    return { overlay: true, content: '', image: '', summary: '' };
  },
  props: ['no'],
  mounted() {
    getEasyLawDetail(this.no, (response) => {
      this.content = response.data.content;
      this.image = response.data.image;
      this.summary = response.data.summary;
      this.overlay = false;
      this.$root.scrollIntoView({ behavior: 'smooth', block: 'start' });
    });
  },
};
</script>

<style></style>
