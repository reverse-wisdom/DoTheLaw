<script>
import { Doughnut } from 'vue-chartjs';

export default {
  extends: Doughnut,
  data() {
    return {
      chartData: {
        hoverBackgroundColor: 'red',
        hoverBorderWidth: 10,
        labels: ['찬성', '반대'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: [],
            data: [],
          },
        ],
      },
    };
  },
  props: {
    list: Object,
  },

  created() {
    this.chartData.datasets[0].data.push(this.list.agree);
    this.chartData.datasets[0].backgroundColor.push('blue');
    this.chartData.datasets[0].data.push(this.list.opposition);
    this.chartData.datasets[0].backgroundColor.push('red');
  },
  mounted() {
    // 실제 차트 랜더링 부분
    this.renderChart(this.chartData, {
      borderWidth: '10px',
      hoverBackgroundColor: 'red',
      hoverBorderWidth: '10px',
      width: '50%',
      height: '50%',
      rotation: 1 * Math.PI,
      circumference: 1 * Math.PI,
    });
  },
};
</script>
