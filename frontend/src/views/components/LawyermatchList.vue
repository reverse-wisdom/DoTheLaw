<template>
  <div class="col-4">
    <v-card max-width="344">
      <div type="button" @click="moveLawyermatchDetail">
        <v-img :src="image" height="200px" v-if="image"></v-img>
        <v-img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" height="200px" v-else></v-img>
        <v-card-title>
          <strong>{{ lawyer.name }}</strong>
          변호사
        </v-card-title>
        <v-card-subtitle>
          {{ lawyer.phone }}
        </v-card-subtitle>
      </div>
      <v-card-actions>
        <v-btn color="orange lighten-2" text>
          Explore
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>
      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>
          <v-card-text>
            {{ lawyer.introduction }}
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      show: false,
      image: this.lawyer.image,
    };
  },
  props: {
    lawyer: {
      type: Object,
      required: true,
    },
  },
  methods: {
    moveLawyermatchDetail() {
      const email = this.lawyer.email;
      this.$router.push({ name: 'lawyermatchdetail', query: { email: email } });
    },
  },
};
</script>

<style>
a:link {
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
}
</style>
