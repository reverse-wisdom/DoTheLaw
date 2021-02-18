<template>
  <div class="result-load-message">
    <p class="result">벌금을 계산 중입니다.. 🤔</p>
    <loadingSpinner />
  </div>
</template>

<script>
import LoadingSpinner from '@/views/components/lawtest/LoadingSpinner';

export default {
  name: 'ResultLoader',
  components: {
    LoadingSpinner,
  },
};
</script>

<style lang="scss" scoped>
.result-load-message {
  padding: 4rem 0;
  color: #666;
  font-size: 3rem;
  text-align: center;
}
.result {
  font-family: 'MaplestoryOTFBold';
  font-size: 3rem;
  color: black;
}
</style>
