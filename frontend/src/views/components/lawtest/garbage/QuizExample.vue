<template>
  <ul>
    <!-- <li class="question-item" v-for="(item, index) in items" :key="item.quiz_question_item"> -->
    <li class="question-item" v-for="(exp, index) in item.example" :key="index">
      <button class="seletor" @click.prevent="clickQuestionItem(index)">
        {{ exp }}
      </button>
    </li>
  </ul>
  <!-- 기존코드 -->
  <!-- <div id="app" style="color: black">
    <p>{{ item.quiz }}</p>
    <ul v-for="(exp, no) in item.example" :key="no">
      <input type="radio" name="example" v-model="picked" :value="exp" />
      {{
        exp
      }}
    </ul>
    <button class="btn-success" @click="isAnswer">벌금체크</button>
    <br />
    <h3 class="center">{{ exp }}</h3>
  </div> -->
</template>

<script>
// import EventBus from '@/views/lawtest/EventBus';

export default {
  name: 'QuizeExample',
  props: {
    item: Object,
  },
  data() {
    return {
      picked: '',
      checkedPenalty: 0,
    };
  },
  methods: {
    isAnswer() {
      console.log('111', this.picked);
      if (this.picked !== this.item.answer) {
        this.checkedPenalty = this.item.penalty;
      }
      this.$emit('checkPenalty', this.checkedPenalty);
      this.checkedPenalty = 0;
    },
    clickQuestionItem(index) {
      this.userPickLogging(index);
      this.isAnswer();
      this.nextQuestion();
    },
    nextQuestion() {
      EventBus.$emit('nextQuestion');
    },
    userPickLogging(index) {
      const pick = this.item.exmple[index];

      EventBus.$emit('userPickLogging', pick);
    },
  },
};
</script>

<style lang="scss">
#wrapper {
  width: 600px;
  margin: 0 auto;
}
.center {
  text-align: center;
}
.question-item {
  .seletor {
    display: block;
    width: 100%;
    background-color: #fff;
    color: #999999;
    border: 1px solid #dedede;
    margin-bottom: 10px;
    padding: 10px;
    font-size: 18px;
    text-align: left;

    &:hover,
    &:focus,
    &:active {
      color: #016afd;
      border-color: #016afd;
    }
  }
}
</style>
