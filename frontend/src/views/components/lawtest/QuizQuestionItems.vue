<template>
  <ul>
    <li class="question-item" v-for="(item, index) in items.exp" :key="index">
      <button class="seletor" @click.prevent="clickQuestionItem(item)">
        {{ item }}
      </button>
    </li>
  </ul>
</template>

<script>
import EventBus from '@/views/components/lawtest/EventBus';

export default {
  name: 'QuizQuestionItems',
  props: {
    items: Object,
    index: Number,
  },
  data() {
    return {
      picked: '',
      checkedPenalty: 0,
    };
  },
  methods: {
    clickQuestionItem(item) {
      this.nextQuestion();
      this.isAnswer(item);
    },
    nextQuestion() {
      EventBus.$emit('nextQuestion');
    },
    isAnswer(item) {
      if (item !== this.items.answer) {
        this.checkedPenalty = this.items.penalty;
      }
      EventBus.$emit('checkPenalty', this.checkedPenalty);
      this.checkedPenalty = 0;
    },
  },
};
</script>

<style lang="scss">
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
