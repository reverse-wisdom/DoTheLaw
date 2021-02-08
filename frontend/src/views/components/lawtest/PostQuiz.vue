<template>
  <div class="container">
    <quiz-core v-bind:id="current" />
  </div>
</template>

<script>
import QuizCore from '@/views/components/lawtest/QuizCore';

export default {
  name: 'PostQuiz',
  components: {
    QuizCore,
  },
  computed: {
    current: function() {
      return this.$route.params.id;
    },
  },
};
</script>
