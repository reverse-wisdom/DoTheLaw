<template>
  <div class="wrapper">
    <div class="md-layout">
      <div class="md-layout-item" style="text-align: center; ">
        <!-- 테이블 -->
        <table class="styled-table kor" style="width: 100%; table-layout: fixed;">
          <thead style="text-align: center;">
            <tr>
              <th>제목</th>
              <th>내용</th>
              <th>종료일시</th>
              <th>찬/반비율</th>
            </tr>
          </thead>
          <tbody v-for="list in listArray" :key="list.controversyId" style="text-align:center">
            <tr :list="list">
              <td>{{ list.title }}</td>
              <td>{{ list.content }}</td>
              <td>{{ list.endDate }}</td>
              <td>
                <DonutChart
                  :list="{
                    agree: list.agree,
                    opposition: list.opposition,
                  }"
                />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import DonutChart from '@/views/components/DonutChart';

export default {
  name: 'controversy',
  components: {
    DonutChart,
  },
  data() {
    return {};
  },
  props: {
    listArray: {
      type: Array,
      required: true,
    },
  },
  mounted() {},

  methods: {},
};
</script>

<style lang="scss" scoped>
// 한글 폰트 설정
.kor {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: none !important;
}

.page {
  height: 30px;
}
</style>
