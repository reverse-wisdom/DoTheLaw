<template>
  <!-- 게시판 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="question-title">법상식테스트</div>

        <div v-for="(item, idx) in items" :key="idx">
          <span>문제.{{ idx + 1 }}</span>
          <span>{{ item.quiz }}</span>
          <QuizExample :item="item" :idx="idx" @checkPenalty="checkPenalty" />
        </div>
      </div>
      <h1>{{ total }}</h1>
    </div>
  </div>
</template>

<script>
import QuizExample from '@/views/components/QuizExample';
// import RangeCounter from '../lawtest/RangeCounter.vue';

export default {
  bodyClass: 'profile-page',
  components: {
    QuizExample,
  },
  data() {
    return {
      total: 0,
      items: [
        {
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: {
            exp1: '정답',
            exp2: '오답',
            exp3: '보기3',
            exp4: '보기4',
          },
          answer: '정답',
          penalty: 200,
        },
        {
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: {
            exp1: '정답',
            exp2: '오답',
            exp3: '보기3',
            exp4: '보기4',
          },
          answer: '정답',
          penalty: 200,
        },
        {
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: {
            exp1: '정답',
            exp2: '오답',
            exp3: '보기3',
            exp4: '보기4',
          },
          answer: '정답',
          penalty: 200,
        },
      ],
      answer: [],
      model: 0,
      colors: ['primary', 'secondary', 'yellow darken-2', 'red', 'orange'],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    checkPenalty(updatePenalty) {
      this.total += updatePenalty;
    },
  },
};
</script>

<style lang="scss" scoped>
// hr 설정
.div-hr {
  width: 80%;
}

// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.btn-center {
  text-align: center;
}

img {
  justify-content: center;
  align-items: center;
}

.question-title {
  width: 50%;
  border-radius: 15px;
  padding: 20px;
  margin: 10px;
  box-shadow: 5px 5px 25px #888888;
}
li {
  list-style-type: none;
}
</style>
