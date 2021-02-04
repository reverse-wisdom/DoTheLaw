<template>
  <!-- 게시판 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="question-title">법상식테스트</div>
        <ul>
          <li class="question-item" v-for="item in items" :key="item.no">
            <p>{{ item.no + 1 }}.</p>
            <p>{{ item.quiz }}</p>
            <button class="seletor" @click.prevent="clickQueizeNext(index)">
              {{ item.example }}
            </button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  bodyClass: 'profile-page',
  data() {
    return {
      items: [
        {
          no: '0',
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: ``,
        },
        {
          no: '1',
          quiz: '허락없이 남의 핸드폰을 보는 행위',
          example: `<a title="공연성이란 불특정 다수가 인식 가능한 상태를 말합니다.\n한사람에게 이야기했는데 여러 사람에게 전파될 가능성이 있다면 공연성이 성립합니다.">공연성</a>`,
        },
      ],
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
  methods: {},
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
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 15px;
  padding: 20px;
  margin: 10px;
  box-shadow: 5px 5px 25px #888888;
}
</style>
