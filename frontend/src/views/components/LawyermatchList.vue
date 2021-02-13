<template>
  <div class="col-4">
    <div type="button" @click="moveLawyermatchDetail">
      <div class="main center">
        <div class="card">
          <div class="content center">
            <div class="img"></div>

            <h2>{{ lawyer.bailiwick }}</h2>
            <p>{{ lawyer.introduction }}</p>
          </div>
          <div id="colorId" class="icons center container">
            <v-img class="i" :src="'/api/member/image/' + lawyer.uuid + '/512'" height="200px" v-if="$store.state.uuid"></v-img>
            <v-img class="i" src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" height="200px" v-else></v-img>
            <h2 class="">{{ lawyer.name }}</h2>
            <div class="">{{ lawyer.email }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      show: false,
    };
  },
  props: {
    lawyer: {
      type: Object,
      required: true,
    },
    i: {
      type: Number,
      required: true,
    },
  },
  methods: {
    moveLawyermatchDetail() {
      const email = this.lawyer.email;
      this.$router.push({ name: 'lawyermatchdetail', query: { email: email } });
    },
  },
  computed: {
    // backcolor() {
    //   var i = this.i + 1;
    //   console.log(i);
    //   if (i > 5) {
    //     i = 0;
    //   }
    //   var color = ['(226, 56, 56)', '(247, 130, 0)', '(255, 185, 0)', '(94, 189, 62)', '(0, 156, 223)', '(151, 57, 153)'];
    //   console.log(color[i]);
    //   this.$emit('inum', i);
    //   return {
    //     // background: `rgb${color[i]}`,
    //   };
    // },
  },
};
</script>

<style scoped>
/* a:link {
  color: black;
  text-decoration: none;
}
a:visited {
  color: black;
  text-decoration: none;
}
a:hover {
  color: black;
  text-decoration: underline;
} */
* {
  margin: 0;
  padding: 0;
}
.center {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.main {
  width: 100%;
  /* height: 100vh; */
}
.card {
  width: 350px;
  height: 400px;
  position: relative;
  box-shadow: 2px 2px 10px rgb(85, 85, 85);
  overflow: hidden;
}
.content {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
}
.content h2 {
  margin: 10xp 0px;
}
.content p {
  margin: 10px 60px;
}
.icons {
  width: 100%;
  height: 100%;
  background: rgb(0, 156, 223);
  position: absolute;
  top: 0;
  left: 0;
  color: white;
  transform: translateX(0);
  transition: all 0.4s;
  cursor: pointer;
}
.icons .i {
  margin: 10px 0px;
}
.card:hover > .icons {
  transform: translateX(-95%);
}
.i {
  max-width: 50%;
  max-height: 40%;
  display: block;
  border-radius: 70%;
}
</style>
