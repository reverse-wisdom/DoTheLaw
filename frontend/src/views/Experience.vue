<template>
  <div id="abc" :style="headerSt">
    <img id="txt" src="@/assets/img/판사님.png" alt="" />
    <div id="exper-button" class="text-center">
      <v-btn rounded color="primary" dark x-large id="exper-vbutton">
        판결하러가기
      </v-btn>
      <v-btn rounded color="primary" dark x-large>
        판결하러가기
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    image: {
      type: String,
      default: require("@/assets/img/123.jpg"),
    },
  },
  computed: {
    headerSt() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },
};
</script>

<style>
#abc {
  height: 800px;
  z-index: -1;
  opacity: 1;
}
#txt {
  position: relative;
  top: 21rem;
  left: 7rem;
}
#exper-button {
  position: relative;
  top: 21rem;
  left: 9rem;
  display: flex;
}
#exper-vbutton {
  margin-right: 3rem;
}
</style>
