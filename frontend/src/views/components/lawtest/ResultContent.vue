<template>
  <div>
    <h2>당신의 벌금은 {{ result }}입니다</h2>
    <button @click="clickReset" class="button-submit ghost">처음으로 돌아가기</button>
  </div>
</template>

<script>
export default {
  name: 'ResultContent',
  props: {
    total: Number,
  },
  data() {
    return {
      result: '',
    };
  },
  methods: {
    clickRestart() {
      this.$emit('clickRestart');
    },
    clickReset() {
      this.$emit('clickReset');
    },
  },
  created() {
    if (this.total != 0) {
      const tmp = String(this.total);
      var hanA = new Array('', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10');
      var danA = new Array('', '십', '백', '천', '', '십', '백', '천', '', '십', '백', '천', '', '십', '백', '천');
      var result = '';
      for (var i = 0; i < tmp.length; i++) {
        var str = '';
        var han = hanA[tmp.charAt(tmp.length - (i + 1))];
        if (han != '') str += han + danA[i];
        if (i == 4) str += '만';
        if (i == 8) str += '억';
        if (i == 12) str += '조';
        result = str + result;
        // console.log(result);
      }
      if (tmp != 0) result = result + '원';
      this.result = result;
    }
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
