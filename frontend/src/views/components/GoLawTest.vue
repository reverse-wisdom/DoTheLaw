<template>
  <!-- 게시판 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="question-title">법상식테스트</div>

        <div id="wrapper">
          <div class="bxslider question-item" v-for="(item, idx) in items" :key="idx">
            <div>
              <QuizGroup :item="item" :idx="idx" @checkedPenalty="checkedPenalty" />
            </div>
          </div>
        </div>
      </div>
      <h1>{{ total }}</h1>
    </div>
  </div>
</template>

<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/jquery.bxslider.js"></script>
<script>
$(function() {
  $('.bxslider').bxSlider({
    captions: true,
    slideWidth: 600,
    auto: true,
    autoControls: false,
    stopAutoOnclick: true,
  });
});
</script>
<script>
import QuizGroup from '@/views/components/QuizGroup';

export default {
  bodyClass: 'profile-page',
  components: {
    QuizGroup,
  },
  data() {
    return {
      total: 0,
      items: [
        {
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: {
            exp1: '합법',
            exp2: '위법',
          },
          answer: '합법',
          penalty: 500,
        },
        {
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: {
            exp1: '합법',
            exp2: '위법',
          },
          answer: '합법',
          penalty: 300,
        },
        {
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: {
            exp1: '합법',
            exp2: '위법',
            exp3: '보기3',
            exp4: '보기4',
          },
          answer: '합법',
          penalty: 200,
        },
      ],
      answer: [],
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
    checkedPenalty(money) {
      this.total += money;
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
