<template>
  <!-- 자문 게시판 페이지 -->
  <li>
    <div class="post-title">
      {{ data.title }}
    </div>
    <div class="post-contents" v-html="data.content">
      {{ data.content }}
    </div>
    <div>작성자 : {{ data.name }}</div>
    <div class="post-time">
      {{ data.createDate }}
      <i class="icon ion-md-create" @click="adviseDetail"></i>
    </div>
  </li>
</template>

<script>
export default {
  bodyClass: 'profile-page',
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      values: [],
      token: '',
      name: this.$store.state.name,
    };
  },

  methods: {
    adviseDetail(value) {
      var matchingId = this.data.matchingId;
      this.$router.push({ name: 'AdviseDetail', query: { matchingId: matchingId } });
    },
  },
};
</script>

<style lang="scss" scoped>
li {
  list-style: none;
}
.post-title {
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 0.5rem;
}
.post-contents {
  height: 160px;
  overflow-y: auto;
  font-size: 18px;
}
.post-time {
  position: absolute;
  bottom: 4px;
  right: 5px;
  font-size: 14px;
  color: #9e9e9e;
}
li {
  position: relative;
  flex-grow: 1;
  width: 400px;
  height: 250px;
  margin: 7px;
  padding: 10px 20px;
  background: white;
  box-shadow: 0 20px 20px rgba(0, 0, 0, 0.08);
  border-radius: 3px;
}
.icon {
  font-size: 1.3rem;
  cursor: pointer;
  color: #364f6b;
  padding-right: 0.4rem;
}
.icon:hover {
  color: #3fc1c9;
}
.icon:active {
  color: #fc5185;
}
.ion-md-create {
  padding-left: 0.1rem;
}
</style>
