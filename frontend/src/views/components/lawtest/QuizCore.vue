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
              <quiz-questions v-if="!finish" @nextQuestion="nextQuestion" :step="step" :items="items" />
              <result-content class="result" v-else @clickReset="reset" :total="total" />
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
      default: require('@/assets/img/bg_lawtest.jpg'),
    },
  },
  data() {
    return {
      items: [
        {
          quiz: `허락없이 남의 편지나 이메일을 보는 행위의 처벌규정으로 옳은 것은?\n`,
          image: require('@/assets/img/envelope.png'),
          example: {
            exp: { exp1: '최대 징역형 3년 또는 벌금 500만원', exp2: '최대 징역형 1년 또는 벌금 100만원' },
            penalty: 5000000,
            answer: '최대 징역형 3년 또는 벌금 500만원',
            remarks: '최대 징역형 3년 또는 벌금 500만원',
          },
        },
        {
          quiz: `부부끼리 휴대전화를 몰래 들여다보는 일은 현실에서도 흔히 있는 일입니다. 부부간 잠금장치를 해제해서 휴대전화를 몰래 훔쳐 보는 행위는 불법일까요?\n`,
          image: require('@/assets/img/smartphoneAdd.jpg'),
          example: {
            exp: { exp1: '부부내에서는 합법이지~', exp2: '부부도 예외없다. 불법이다!' },
            penalty: 50000000,
            answer: '부부도 예외없다. 불법이다!',
            remarks: '징역형 5년 또는 벌금 5000만원',
          },
        },
        {
          quiz: `츄리닝안경 네오는 혜지한테 두상이 연락처를 부탁했다. 혜지는 두상이에게 별도의 확인 없이 연락처를 내어주었다. 이는 불법일까? 아닐까?\n`,
          image: require('@/assets/img/phonenumber.png'),
          example: {
            exp: { exp1: '에이, 저정도는 흔한 일인데! 불법 아니다', exp2: '허락없이? 당연 불법이지!' },
            penalty: 50000000,
            answer: '허락없이? 당연 불법이지!',
            remarks: '징역형 5년 또는 벌금 5000만원',
          },
        },
        {
          quiz: `우회전 차선에 신호를 대기하고 있는데 뒷차가 우회전하고 싶다고 빵빵거릴 때 안 비켜줘도 된다(?)\n`,
          image: require('@/assets/img/right.png'),
          example: {
            exp: { exp1: '빵빵거리면 버티고 있을 순 없자? 비켜줘야지', exp2: '내가 먼저지!' },
            penalty: 300000,
            answer: '내가 먼저지!',
            remarks: '실제로 이런 상황에서 빵빵거린 뒤 차에게 난폭운전죄를 인정해서 30만원의 벌금을 선고했던 판례가 있음',
          },
        },
        {
          quiz: `원칙적으로 고속도로는 사람이 보행할 수 없는 구간입니다.\n고속도로상에서 보행하는 사람과 자동차가 충돌하는 사고가 일어났을 경우\n 이유없는 보행자와 자동차의 기본과실 비율은 어떻게 될까요?\n`,
          image: require('@/assets/img/accident1.jpg'),
          example: {
            exp: { exp1: '이유없는 보행자: 80% 자동차: 20%', exp2: '이유없는 보행자: 50% 자동차:50%' },
            penalty: 10000000,
            answer: '이유없는 보행자:80% 자동차: 20%',
            remarks:
              '고속도로를 걸어다니는 행위: 자동차(건설기계 포함)를 사용하지 아니하고 고속국도를 통행하거나 출입한 자는 1년 이하의 징역이나 1천만원 이하의 벌금에 처해진다(도로법 제115조 제1호).',
          },
        },
        {
          quiz: `반려견이 무지개다리를 건넜다. 사체를 쓰레기봉투에 폐기물로 처리해야할까? 땅에 묻어줘야할까?\n`,
          image: require('@/assets/img/bridge1.jpg'),
          example: {
            exp: { exp1: '내 집 앞마당에서는 땅에 묻어줘도 된다', exp2: '안타깝지만, 쓰레기봉투에 버려야한다' },
            penalty: 1000000,
            answer: '안타깝지만, 쓰레기봉투에 버려야한다',
            remarks:
              '동물사체는 폐기물관리법에 따라 허가 또는 승인받거나 신고된 폐기물 처리시설에서만 매립할수 있으며, 폐기물 처리시설이 아닌곳에 매립해서는 안된다 . 이를 위반하면 100만원이하읭 과태료가 부과된다. 폐기물 관리법 제 8조 제2항',
          },
        },

        {
          quiz: `특별한 지인을 위해 수제로 만든 캔들을 만들어 선물을 해주었다! 이 경우와 관련된 처벌규정 내용으로는?\n`,
          image: require('@/assets/img/candle1.png'),
          example: {
            exp: { exp1: '최대 징역형 1년 또는 벌금 1000만원', exp2: '최대 징역형 3년 또는 벌금 3000만원', exp3: '최대 징역형 5년 또는 벌금 5000만원', exp4: '최대 징역형 7년 또는 벌금 7000만원' },
            penalty: 70000000,
            answer: '최대 징역형 7년 또는 벌금 7000만원',
            remarks: '향초와 디퓨저는 화학물질의 노출을 유발할 가능성이 있는 제품으로 우리나라 안전 확인대상 생활화학제품에 속해 승인을 받아야함',
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
      this.total = 0;

      // if (isMobile().any) {
      //   this.scrollEl(this.$refs.QuizContainer);
      // }
    },
    reset() {
      this.intro = true;
      this.restart();

      // if (isMobile().any) {
      //   this.scrollEl(this.$refs.QuizContainer);
      // }
    },
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
