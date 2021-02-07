<template>
  <div id="app">
    <p>문제.</p>
    <p>{{ item.quiz }}</p>
    <ul v-for="(exp, no) in item.example" :key="no">
      <input type="radio" name="example" v-model="picked" :value="exp" />
      {{
        exp
      }}
    </ul>
    <button class="btn-success" @click="isAnswer">다음문제</button>
    <br />
  </div>
</template>

<script>
export default {
  name: 'QuizeGroup',
  props: {
    item: Object,
  },
  data() {
    return {
      picked: '',
      money: 0,
    };
  },
  methods: {
    isAnswer() {
      console.log('111', this.picked);
      if (this.picked !== this.item.answer) {
        this.money = this.item.penalty;
      }
      this.$emit('checkedPenalty', this.money);
    },
  },
};
</script>

<style>
#wrapper {
  width: 600px;
  margin: 0 auto;
}
</style>
