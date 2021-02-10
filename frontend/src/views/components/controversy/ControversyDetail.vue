<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <hr />
        <div class="container">
          <v-card class="pa-md-4 mb-8 mx-lg-auto ">
            <v-img class="white--text align-end" height="200px" :src="controversyImg">
              <v-card-title>{{ list.title }}</v-card-title>
            </v-img>
            <v-row no-gutters>
              <v-col>
                <div class="endDate">찬성반대 기간 시작:{{ '    ' + $moment(list.startDate).format('llll') }}</div>
              </v-col>
              <v-col>
                <div class="text-right endDate">종료시간 시간:{{ '    ' + $moment(list.endDate).format('llll') }}</div>
              </v-col>
            </v-row>
            <v-card-text class="text--primary">
              <h4 class="text-left">{{ list.content }}</h4>
            </v-card-text>
            <!-- 댓글 -->
            <proposal-write :controversyId="list.controversyId" v-on:updateProposal="updateProposal" />
            <br />
            <ul v-for="(proposal, index) in proposals" :key="index">
              <proposal-row :proposal="proposal" :controversyId="list.controversyId" v-on:updateProposal="updateProposal" />
            </ul>
          </v-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProposalRow from '@/views/components/controversy/ProposalRow';
import ProposalWrite from '@/views/components/controversy/ProposalWrite';
import { detail, searchProposoal } from '@/api/controversy';
export default {
  bodyClass: 'profile-page',
  components: {
    ProposalRow,
    ProposalWrite,
  },
  data() {
    return {
      list: {},
      controversyImg: require('@/assets/img/controversy.png'),
      proposals: [],
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
  async created() {
    const controversyId = this.$route.query.controversyId;
    const { data } = await detail(controversyId);
    this.list = data;
    {
      const controversyId = this.$route.query.controversyId;
      const { data } = await searchProposoal(controversyId);
      this.proposals = data;
    }
  },
  methods: {
    async updateProposal() {
      const controversyId = this.$route.query.controversyId;
      const { data } = await searchProposoal(controversyId);
      this.proposals = data;
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

.endDate {
  font-family: 'Work Sans';
  letter-spacing: -1px;
  line-height: 110%;
  font-size: 20px;
}
</style>
