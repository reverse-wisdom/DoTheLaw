<template>
  <div v-bind:style="[checkDate ? { opacity: 0.4 } : {}]">
    <div @click="moveControversyDetail(list)">
      <v-img class="white--text align-end" height="200px" :src="controversyImg" :disabled="checkDate">
        <v-card-title class="conv-title">{{ list.title }}</v-card-title>
      </v-img>
      <v-card-text class="text--primary">
        <div class="text-right endDate conv-text">찬성반대 종료시간:{{ '    ' + $moment(list.endDate).format('llll') }}</div>
        <h3 class="text-left conv-text">{{ list.content }}</h3>
        <span class="t_blue">
          <b>찬성: {{ Math.round((list.agree / (list.agree + list.opposition)) * 100) }}</b>
          %
        </span>
        <span class="gBar">
          <span class="gAction4" v-bind:style="{ width: Math.round((list.agree / (list.agree + list.opposition)) * 100) + '%' }"></span>
          <span class="gAction2" v-bind:style="{ width: Math.round((list.opposition / (list.agree + list.opposition)) * 100) + '%' }"></span>
        </span>
        <span class="t_red">
          <b>반대: {{ Math.round((list.opposition / (list.agree + list.opposition)) * 100) }}</b>
          %
        </span>
      </v-card-text>
    </div>
    <v-row no-gutters>
      <v-col>
        <v-btn color="primary" x-large @click="agreeUp(list.controversyId)" :disabled="checkDate">
          <v-icon>mdi-pencil</v-icon>
          <div class="keep-spaces">&nbsp;찬성</div>
        </v-btn>
      </v-col>
      <v-col>
        <v-btn color="error" x-large class="text-right" @click="oppositionUp(list.controversyId)" :disabled="checkDate">
          <v-icon>mdi-pencil</v-icon>
          <div class="keep-spaces">&nbsp;반대</div>
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import ControversyDetail from '@/views/components/controversy/ControversyDetail';
import { agree, opposition } from '@/api/controversy';
export default {
  components: {
    ControversyDetail,
  },
  data() {
    return {
      controversyImg: require('@/assets/img/controversy.png'),
      checkDate: true,
    };
  },
  props: {
    list: {
      type: Object,
      required: true,
    },
  },
  mounted() {
    if (new Date(this.list.endDate) > Date.now()) {
      this.checkDate = false;
    }
  },
  methods: {
    moveControversyDetail(list) {
      if (!this.checkDate) this.$router.push({ name: 'controversydetail', query: { controversyId: list.controversyId } });
      else {
        this.$swal({
          icon: 'error',
          title: '종료된 논쟁입니다.!!',
        });
      }
    },
    async agreeUp(controversyId) {
      if (this.$store.state.name == null || this.$store.state.name == '') {
        this.$swal({
          icon: 'error',
          title: '로그인후 투표할 수 있습니다.!!',
        });
      } else {
        const { data } = await agree(controversyId, this.$store.state.uuid);
        if (data == 'SUCCESS') {
          this.$swal({
            icon: 'success',
            title: '투표완료.!!',
          });

          this.$emit('updateContent');
        } else {
          this.$swal({
            icon: 'error',
            title: '이미 투표하셨습니다.!!',
          });
        }
      }
    },
    async oppositionUp(controversyId) {
      if (this.$store.state.name == null || this.$store.state.name == '') {
        this.$swal({
          icon: 'error',
          title: '로그인후 투표할 수 있습니다.!!',
        });
      } else {
        const { data } = await opposition(controversyId, this.$store.state.uuid);
        if (data == 'SUCCESS') {
          this.$swal({
            icon: 'success',
            title: '투표완료.!!',
          });

          this.$emit('updateContent');
        } else {
          this.$swal({
            icon: 'error',
            title: '이미 투표하셨습니다.!!',
          });
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@font-face {
  font-family: 'Cafe24Ohsquare';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Cafe24Ohsquare.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'S-CoreDream-6Bold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-6Bold.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
.conv-text {
  font-family: 'Cafe24Ohsquare';
}
.conv-title {
  font-family: 'S-CoreDream-6Bold';
  font-size: 1.8rem;
}

// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: none !important;
}
// 공백문제 해결
.keep-spaces {
  white-space: pre-wrap;
}

.gBar {
  display: inline-block;
  width: 100%;
  height: 20px;
  margin: 0;
  border: none;
  border-radius: 20px;
}
.gAction4 {
  display: block;
  height: 20px;
  background: #4673e3 right 4px center no-repeat;
  background-size: 50px;
  margin: 0;
  border: none;
  border-radius: 20px 0 0 20px;
  float: left;
}
.gAction2 {
  display: block;
  height: 20px;
  background: #fe6b53 left 4px center no-repeat;
  background-size: 50px;
  margin: 0;
  border: none;
  border-radius: 0 20px 20px 0;
  float: right;
}
.t_blue {
  color: #4673e3;
}
.t_red {
  color: #fe6b53;
}
.t_blue,
.t_red {
  font-family: 'Work Sans';
  letter-spacing: -1px;
  line-height: 110%;
  font-size: 30px;
}

.endDate {
  letter-spacing: -1px;
  line-height: 110%;
  font-size: 20px;
}

.page {
  height: 30px;
}
.donut-inner {
  margin-top: -100px;
  margin-bottom: 100px;
}
.donut-inner h5 {
  margin-bottom: 20px;
  margin-top: 40px;
}
.donut-inner span {
  font-size: 20px;
}
.disabled {
  pointer-events: none;
  opacity: 0.4;
}
</style>
