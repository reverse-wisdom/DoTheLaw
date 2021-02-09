<template>
  <div>
    <h2>당신의 벌금은 {{ total }}입니다</h2>
    <button @click="clickReset" class="button-submit ghost">처음으로 돌아가기</button>
  </div>
</template>

<script>
export default {
  name: 'ResultContent',
  props: {
    total: Number,
  },
  methods: {
    clickRestart() {
      this.$emit('clickRestart');
    },
    clickReset() {
      this.$emit('clickReset');
    },
  },
};
</script>

<style lang="scss">
.result {
  h2,
  h3 {
    font-size: 1.25rem;
    font-weight: 500;
    margin: 0 0 20px 0;
    padding: 0;
    color: #016afd;
  }

  .other {
    margin: 1.5rem 0;
    padding: 0.75rem;
    border: 1px solid #e5e5e5;
    border-radius: 0.25rem;
    background-color: #f1f2f2;
    text-align: center;
    font-size: 1.125rem;
    color: #111;
  }

  p {
    margin-bottom: 1em;
  }

  img {
    max-width: calc(100% + 40px);
    margin: 0 -20px;

    @media (min-width: 600px) {
      width: 100%;
      margin: 0;
    }
  }
}
</style>
