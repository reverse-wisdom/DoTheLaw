<script>
import { HorizontalBar } from 'vue-chartjs';
import { fetchAdviseLawyer } from '@/api/advise';

export default {
  extends: HorizontalBar,
  data() {
    return {
      isLoad: false,
      chartData: {
        hoverBackgroundColor: 'red',
        hoverBorderWidth: 10,
        labels: ['교통/운전', '가정', '근로/노동', '부동산', '금융', '정보통신/기술'],
        datasets: [
          {
            backgroundColor: [],
            data: [0, 0, 0, 0, 0, 0],
          },
        ],
        hoverOffset: 4,
      },
      values: [],
    };
  },
  props: {
    list: Array,
  },

  async created() {
    try {
      const userData = this.$store.state.lawuuid;
      const { data } = await fetchAdviseLawyer(userData);
      for (let i = 0; i < data.length; i++) {
        this.values.push({
          category: data[i].category,
        });
      }
    } catch (err) {}

    console.log(this.values);
    for (let index = 0; index < this.values.length; index++) {
      var category = this.values[index].category;
      console.log(category);
      if (category == '교통/운전') {
        this.chartData.datasets[0].data[0]++;
        this.$data._chart.update();
      } else if (category == '가정') {
        this.chartData.datasets[0].data[1]++;
        this.$data._chart.update();
      } else if (category == '근로/노동') {
        this.chartData.datasets[0].data[2]++;
        this.$data._chart.update();
      } else if (category == '부동산') {
        this.chartData.datasets[0].data[3]++;
        this.$data._chart.update();
      } else if (category == '금융') {
        this.chartData.datasets[0].data[4]++;
        this.$data._chart.update();
      } else if (category == '정보통신/기술') {
        this.chartData.datasets[0].data[5]++;
        this.$data._chart.update();
      }

      // 차트색 rgb 랜덤 추출
      var r = Math.floor(Math.random() * 255);
      var g = Math.floor(Math.random() * 255);
      var b = Math.floor(Math.random() * 255);
      this.chartData.datasets[0].backgroundColor.push('rgb(' + r + ',' + g + ',' + b + ')');
    }
    console.log(this.chartData);
  },
  mounted() {
    // 실제 차트 랜더링 부분
    this.renderChart(this.chartData, {
      borderWidth: '10px',
      hoverBackgroundColor: 'red',
      hoverBorderWidth: '10px',
      maintainAspectRatio: false,
      responsive: true,
      width: '50%',
      height: '50%',

      legend: {
        display: false,
      },
      tooltips: {
        callbacks: {
          label: (tooltipItem) => `${tooltipItem.yLabel}: ${tooltipItem.xLabel}`,
          title: () => null,
        },
      },
    });
  },
};
</script>
