<template>
  <ol>
    <li class="text-center" v-for="(item, index) in items" v-show="index + 1 === step" :key="index" style="list-style-type: none;">
      <img :src="item.image" />
      <span class="question">
        {{ item.quiz }}
      </span>
      <quiz-question-items :parentIndex="index" v-bind:items="item.example" />
    </li>
  </ol>
</template>

<script>
import QuizQuestionItems from '@/views/components/lawtest/QuizQuestionItems.vue';
export default {
  components: {
    QuizQuestionItems,
  },
  name: 'QuizQuestions',
  props: {
    step: Number,
    items: Array,
  },
};
</script>

<style lang="scss" scoped>
.question {
  display: block;
  position: relative;
  min-height: 46px;
  padding-left: 1.5em;
  margin-bottom: 20px;
  color: #666666;
  font-size: 30px;
  font-family: 'MaplestoryOTFBold';
  line-height: 1.3;
  word-break: keep-all;
  span {
    position: absolute;
    top: 0;
    left: 0;
  }

  p {
    margin-bottom: 1em;
  }
}
</style>
