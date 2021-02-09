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
        hoverOffset: 4,
      },
    };
  },
  props: {
    list: Object,
  },

  created() {
    let agree = Math.round((this.list.agree / (this.list.agree + this.list.opposition)) * 100);
    let opposition = Math.round((this.list.opposition / (this.list.agree + this.list.opposition)) * 100);

    this.chartData.datasets[0].data.push(agree);

    this.chartData.datasets[0].backgroundColor.push('blue');
    this.chartData.datasets[0].data.push(opposition);
    this.chartData.datasets[0].backgroundColor.push('red');
  },
  mounted() {
    // 실제 차트 랜더링 부분
    this.renderChart(this.chartData, {
      borderWidth: '10px',
      hoverBackgroundColor: 'red',
      hoverBorderWidth: '10px',
      width: '100%',
      height: '100%',
      rotation: 1 * Math.PI,
      circumference: 1 * Math.PI,
      // pieceLabel: { mode: 'label', position: 'outside', fontSize: 11, fontStyle: 'bold' },

      tooltips: {
        enabled: true,
        mode: 'index',
        callbacks: {
          label: function(i, data) {
            return data.datasets[0].data[i.index] + ' %';
          },
        },
      },
    });
  },
};
</script>
