<template>
  <!-- 회원가입 페이지 -->
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <hr />
        <div class="container text-center">
          <div class="md-layout">
            <div class="md-layout-item my-2">
              <a @click="dictAlert(alertTest)">alertTest</a>
              <v-btn x-large color="blue-grey" dark @click="regexp">
                정규식치환테스트
                <v-icon right dark>mdi-account-check</v-icon>
              </v-btn>
              <p v-html="raw"></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { lawDict } from '@/api/service';

export default {
  bodyClass: 'profile-page',
  data() {
    return {
      raw: `【피 고 인】 <br/>【상 고 인】 피고인<br/>【원심판결】 인천지법 2018. 2. 14. 선고 2017노4452 판결<br/>【주    문】<br/>  원심판결을 파기하고, 사건을 인천지방법원에 환송한다.<br/><br/>【이    유】  상고이유를 판단한다.<br/>  1. 명예훼손죄가 성립하기 위해서는 주관적 구성요소로서 타인의 명예를 훼손한다는 고의를 가지고 사람의 사회적 평가를 저하시키는 데 충분한 구체적 사실을 적시하는 행위를 할 것이 요구된다(<a href="#AJAX" onclick="javascript:showCase('2010도2877');" >대법원 2010. 10. 28. 선고 2010도2877 판결</a> 참조). 따라서 불미스러운 소문의 진위를 확인하고자 질문을 하는 과정에서 타인의 명예를 훼손하는 발언을 하였다면 이러한 경우에는 그 동기에 비추어 명예훼손의 고의를 인정하기 어렵다(<a href="#AJAX" onclick="javascript:showCase('85도588');" >대법원 1985. 5. 28. 선고 85도588 판결</a> 참조).<br/>  또한 명예훼손죄의 구성요건인 공연성은 불특정 또는 다수인이 인식할 수 있는 상태를 말한다. 비록 개별적으로 한 사람에 대하여 사실을 유포하였다고 하더라도 그로부터 불특정 또는 다수인에게 전파될 가능성이 있다면 공연성의 요건을 충족하지만 이와 달리 전파될 가능성이 없다면 특정한 한 사람에 대한 사실의 유포는 공연성이 없다고 할 것이다(<a href="#AJAX" onclick="javascript:showCase('99도5622');" >대법원 2000. 5. 16. 선고 99도5622 판결</a>, <a href="#AJAX" onclick="javascript:showCase('2010도7497');" >대법원 2011. 9. 8. 선고 2010도7497 판결</a> 등 참조). 한편 위와 같이 전파가능성을 이유로 명예훼손죄의 공연성을 인정하는 경우에는 적어도 범죄구성요건의 주관적 요소로서 미필적 고의가 필요하므로 전파가능성에 대한 인식이 있음은 물론 나아가 그 위험을 용인하는 내심의 의사가 있어야 한다. 그 행위자가 전파가능성을 용인하고 있었는지 여부는 외부에 나타난 행위의 형태와 상황 등 구체적인 사정을 기초로 일반인이라면 그 전파가능성을 어떻게 평가할 것인가를 고려하면서 행위자의 입장에서 그 심리상태를 추인하여야 할 것이다(대법원 2005. 5. 27. 선고 2004도8914 판결, <a href="#AJAX" onclick="javascript:showCase('2010도2877');" >대법원 2010. 10. 28. 선고 2010도2877 판결</a> 등 참조).<br/>  2. 이 사건 공소사실의 요지는 ○○○마트의 운영자인 피고인이 위 마트에 아이스크림을 납품하는 공소외인을 불러 “다른 업체에서는 마트에 입점하기 위하여 200만 원, 400만 원 등 입점비를 준다고 하던데, 아이스크림은 입점비를 얼마나 줬냐? 점장(피해자)이 여러 군데 업체에서 입점비를 돈으로 받아 해먹었고, 지금 뒷조사 중이다.”라고 말하여 공연히 허위의 사실을 적시하여 피해자의 명예를 훼손하였다는 것이다.<br/>  이에 대하여 원심은 피고인이 자신도 현금으로 입점비 명목의 돈을 받아서 유용하고자 하는 의도로 거래처 직원인 공소외인에게 공소사실 기재와 같은 말을 하였다는 이유로 이 사건 공소사실을 유죄로 판단하였다.<br/>  3. 그러나 원심의 위와 같은 판단은 다음과 같은 이유에서 수긍하기 어렵다.<br/>  가. 기록에 의하면 다음과 같은 사정을 알 수 있다.<br/>  ① 피고인은 2013. 1.경 3층 상가를 신축하고, 1층은 ○○○마트로, 2층은 마트의 사무실로 사용하였다.<br/>  ② 피고인은 2014. 7. 10. ○○○마트의 영업을 시작하면서 피해자를 점장으로 고용하여 피해자에게 마트의 관리를 맡겼다.<br/>  ③ 피고인은 2014. 12. 20. 마트의 재고조사를 시행한 후 일부 품목과 금액의 손실이 발견되자 그때부터 피해자를 의심하면서 마트 관계자들을 상대로 피해자의 비리 여부를 확인하고 다녔다.<br/>  ④ 그러던 중 피고인은 피해자가 마트 납품업자들로부터 현금으로 입점비를 받았다는 이야기를 듣고 2015. 5. 20.경 마트에 아이스크림을 납품하는 업체 직원인 공소외인을 2층 사무실로 불러 피해자에게 입점비를 얼마 주었느냐고 질문하였다. 이에 공소외인이 입점비 지급사실을 부인하자, 피고인은 “다 알고 물어보는 것이니 정확히 답하라, 피해자가 여러 업체로부터 뒷돈을 받아서 조사 중이니 솔직히 답하라.”라며 질문을 계속하였다.<br/>  ⑤ 피고인은 대화를 마치면서 공소외인에게 자신이 이런 것을 물어보았다는 것을 피해자에게 절대 말하지 말고 혼자만 들은 것으로 하라고 당부하였다.<br/>  ⑥ 그 후 공소외인은 피해자에게 피고인이 위와 같은 질문을 한 사실을 이야기하였다.<br/>  나. 위와 같은 사정을 종합하여 보면, 피고인은 피해자가 납품업체들로부터 입점비를 받아 개인적으로 착복하였다는 소문을 듣고 납품업체 직원인 공소외인을 불러 그 소문의 진위를 확인하면서 공소외인도 그와 같은 입점비를 피해자에게 주었는지 질문하는 과정에서 공소사실 기재와 같은 말을 한 것으로 보아야 한다.<br/>  따라서 이와 같은 사실을 앞서 본 법리에 비추어 살펴보면, 피고인은 피해자의 사회적 평가를 저하시킬 의도를 가지거나 그러한 결과가 발생할 것을 인식한 상태에서 위와 같은 말을 한 것이 아니라 단지 피해자의 입점비 수수 여부에 관한 진위를 확인하기 위한 질문을 하는 과정에서 그런 말을 한 것에 지나지 아니하므로, 피고인에게 명예훼손의 고의를 인정하기 어렵다.<br/>  원심은 피고인이 자신도 입점비를 받아 개인적으로 유용하려는 목적에서 이와 같은 말을 하였다고 판시하였으나, 피고인이 피해자의 입점비 수수 사실을 확인하고자 한 진정한 의도가 무엇이었든지 피고인이 진위 확인을 위한 질문을 하는 과정에서 이와 같은 말을 한 것은 분명한 이상 원심이 판시한 피고인의 의도는 명예훼손의 고의를 판단하는 데에 영향을 미칠 수 없다.<br/>  한편 피고인이 아무도 없는 사무실로 공소외인을 불러 단둘이 이야기를 하였던 점, 피해자의 입점비 수수 여부에 관한 질문을 한 후 공소외인에게 자신이 그와 같은 질문을 하였다는 사실을 피해자에게 말하지 말고 혼자만 알고 있으라고 당부하였던 점, 공소외인이 그 후 피해자에게는 이와 같은 사실을 이야기하였으나 피해자 외의 다른 사람들에게도 이러한 사실을 이야기한 정황은 찾아볼 수 없는 점 등을 고려하면, 피고인에게 전파가능성에 대한 인식과 그 위험을 용인하는 내심의 의사가 있었다고 보기도 어렵다.<br/>  다. 그럼에도 원심은 그 판시와 같은 이유만으로 명예훼손의 고의와 공연성을 모두 인정하여 이 사건 공소사실을 유죄로 판단하였다. 따라서 원심판단에는 명예훼손죄에서의 고의와 공연성 또는 전파가능성에 관한 법리를 오해하여 판결에 영향을 미친 잘못이 있다. 이 점을 지적하는 취지의 상고이유 주장은 정당하다.<br/>  4. 그러므로 원심판결을 파기하고, 사건을 다시 심리·판단하도록 원심법원에 환송하기로 하여, 관여 대법관의 일치된 의견으로 주문과 같이 판결한다.<br/><br/><div style='text-align:center'>대법관 김소영(재판장) 고영한 권순일 조재연(주심) </div>`,
      dictTemp: [
        {
          word: '공연성',
          mean: `<a title="공연성이란 불특정 다수가 인식 가능한 상태를 말합니다.\n한사람에게 이야기했는데 여러 사람에게 전파될 가능성이 있다면 공연성이 성립합니다.">공연성</a>`,
        },
        {
          word: '선고',
          mean: `<a title="검사의 구형과 피고인의 변호사의 변론내용 등을 종합하여 판사의 재량에 따라 형을 확정지어 판결을 내리는 것을 말한다.\n쉽게 말하자면, 피고인에 대한 직접적인 형별의 판결로 재판의 효력이 생기게 되는것이다.">선고</a>`,
        },
      ],
      dict: [],
      alertTest: '쀼쀼쀼',
      swal: null,
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
    },
    city: {
      type: String,
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  async mounted() {
    const { data } = await lawDict();
    for (let i = 0; i < data.length; i++) {
      this.dict.push({
        word: data[i].word,
        mean: String.raw`${data[i].mean}`.replace(/\\n/g, '\n'),
      });
    }
    var newText = this.raw.replace(/<(\/a|a)([^>]*)>/gi, '');

    this.swal = this.$swal;
    this.raw = newText;
    this.regexp();
  },
  methods: {
    regexp() {
      this.dict.forEach((element) => {
        var regEx = new RegExp(element.word, 'g');
        // var replaced_str = this.raw.replace(regEx, `<a @click="dictAlert(alertTest)">alertTest</a>`);
        // var replaced_str = this.raw.replace(regEx, `<a @click="dictAlert(${element.mean})">${element.word}</a>`);
        var replaced_str = this.raw.replace(regEx, `<a data-title="${element.mean}">${element.word}</a>`);
        this.raw = replaced_str;
      });
    },
    dictAlert(mean) {
      // console.log(mean);
      this.swal({
        // icon: 'error',
        title: mean,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
}
.section {
  padding: 0;
}
</style>
