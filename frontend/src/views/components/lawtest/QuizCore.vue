<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <article class="quizzes-container" ref="QuizContainer">
            <start-content v-if="intro" @introEnd="start" />
            <template v-else>
              <range-counter @allQuestionFinish="result" :step="step" :stepRange="stepRange" />
              <quiz-questions v-if="!finish" v-on:pickedArrayPush="pickedArrayPush" @nextQuestion="nextQuestion" :step="step" :items="items" />
              <result-content class="result" v-else @clickRestart="restart" @clickReset="reset" v-bind:total="total" />
              <result-loader v-if="resultLoading" />
            </template>
          </article>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EventBus from '@/views/components/lawtest/EventBus';
import QuizQuestions from '@/views/components/lawtest/QuizQuestions';
import StartContent from '@/views/components/lawtest/StartContent';
import ResultContent from '@/views/components/lawtest/ResultContent';
import RangeCounter from '@/views/components/lawtest//RangeCounter';
import ResultLoader from '@/views/components/lawtest/ResultLoader';

export default {
  name: 'QuizCore',
  bodyClass: 'profile-page',
  components: {
    QuizQuestions,
    StartContent,
    ResultLoader,
    ResultContent,
    RangeCounter,
  },
  props: {
    // id: Number && String,
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
    },
  },
  data() {
    return {
      items: [
        {
          quiz: '1.허락없이 남의 편지나 이메일을 보는 행위',
          example: {
            exp: { exp1: '징역형 3년 또는 벌금 500만원', exp2: '징역형 1년 또는 벌금 100만원' },
            penalty: 500,
            answer: '징역형 3년 또는 벌금 500만원',
          },
        },
        {
          quiz: '2.허락없이 남의 핸드폰을 보는 행위',
          example: {
            exp: { exp1: '징역형 5년 또는 벌금 5000만원', exp2: '징역형 3년 또는 벌금 2000만원' },
            penalty: 5000,
            answer: '징역형 5년 또는 벌금 5000만원',
          },
        },
        {
          quiz: '3.허락없이 남의 연락처를 제3자한테 알려주는 행위',
          example: {
            exp: { exp1: '징역형 5년 또는 벌금 5000만원', exp2: '징역형 3년 또는 벌금 2000만원' },
            penalty: 5000,
            answer: '징역형 5년 또는 벌금 5000만원',
          },
        },
        {
          quiz: '4.반려동물은 땅에 묻어주어도 된다? 인된다?',
          example: {
            exp: { exp1: '된다', exp2: '안된다' },
            penalty: 100,
            answer: '안된다',
          },
        },
      ],
      total: 0,
      executed: true, // ajax 통신이 완료되었을때 true
      wpdata: null, // 외부(wordpress) 데이터 바인딩
      pickedArray: [], // 고른 항목에 대한 '값' 배열
      resultIndex: [], // picked에 push될 고른 항목에 대한 '값'
      resultFinalArray: [], // resultArray에서 정제된 결과 값 (가장 많이 선택된 값에 대한 결과 유형 에서만 사용)
      resultLoading: false, // 결과 값 계산 전 로딩에 대한 상태 값 정의
      step: 1, // 문제가 몇 단계인지
      intro: true, // false 시 문제 풀기 시작
      finish: false, // true 시 문제 풀기 끝
      notFound: false, // 해당 퀴즈 콘텐츠를 찾을 수 없을 때
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
    stepRange: function(items) {
      return this.items.length;
    },
  },
  methods: {
    result() {
      this.quizFinish();
      return;
    },
    scrollEl(el) {
      const positionTop = el.offsetTop;
      window.scrollTo(0, positionTop);
    },
    nextQuestion() {
      this.step++;
    },
    // pickedArrayPush(v) {
    //   this.pickedArray.push(v);
    // },
    start() {
      this.intro = false;
      this.$refs.QuizContainer;
    },
    quizFinish() {
      this.resultLoading = true;
      setTimeout(() => {
        this.resultLoading = false;
        this.finish = true;
        // if (isMobile().any) {
        //   this.scrollEl(this.$refs.QuizContainer);
        // }
      }, 4000);
    },
    restart() {
      this.pickedArray = [];
      this.resultIndex = [];
      this.resultFinalArray = [];
      this.step = 1;
      this.finish = false;

      if (isMobile().any) {
        this.scrollEl(this.$refs.QuizContainer);
      }
    },
    reset() {
      this.intro = true;
      this.restart();

      if (isMobile().any) {
        this.scrollEl(this.$refs.QuizContainer);
      }
    },
    //벌금계산
    checkPenalty(updatePenalty) {
      this.total += updatePenalty;
    },
  },
  watch: {
    $route() {
      this.dataFetch();
      this.reset();
    },
  },
  created() {
    EventBus.$on('nextQuestion', () => {
      this.nextQuestion();
    });

    EventBus.$on('checkPenalty', (v) => {
      this.checkPenalty(v);
    });
    console.log(this.total);
  },
};
</script>

<style lang="scss">
main {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  margin: 0 -10px;
  .col {
    -ms-flex-preferred-size: calc(100% - 10px);
    flex-basis: calc(100% - 10px);
    margin: 20px 10px 0;
  }
  @media (min-width: 900px) {
    > .col {
      min-height: 504px;

      &:nth-child(1) {
        -ms-flex-preferred-size: calc(68% - 20px);
        flex-basis: calc(68% - 20px);
      }

      &:nth-child(2) {
        -ms-flex-preferred-size: calc(32% - 20px);
        flex-basis: calc(32% - 20px);
      }
    }
  }
}

.quizzes-container {
  overflow: hidden;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  width: 100%;
  min-height: 500px;
  padding: 30px 20px;
  background-color: white;

  @media (min-width: 600px) {
    padding: 30px;
  }

  h1 {
    font-size: 20px;
    font-weight: 500;
    margin: 0 0 20px 0;
    padding: 0;
  }

  .button-submit {
    display: block;
    width: 80%;
    max-width: 360px;
    height: 50px;
    border-radius: 25px;
    text-align: center;
    color: #ffffff;
    font-size: 20px;
    border: 1px solid #016afd;
    background-color: #016afd;
    margin: 20px auto;
    &.ghost {
      background-color: transparent;
      color: #016afd;
    }
  }
}
</style>
