<template>
  <div>
    <div class="start-content" v-html="content"></div>
    <div class="title">
      <div class="">
        <img src="@/assets/img/main2.png" class="animate__animated animate__heartBeat animate__infinite" alt="" />
      </div>
    </div>
    <button @click="introEnd" class="button-submit animate__animated animate__shakeX animate__infinite">시작하기</button>
  </div>
</template>

<script>
export default {
  name: 'StartContent',
  props: {
    content: String,
  },
  methods: {
    introEnd() {
      this.$emit('introEnd');
    },
  },
};
</script>

<style lang="scss"></style>
<style scoped>
.title-lawtest {
  left: 50%;
  animation: flipInX;
  animation-duration: 1s;
  animation-iteration-count: infinite;
}
.title {
  text-align: center;
}
img {
  max-width: 50%;
  height: auto;
}
</style>
