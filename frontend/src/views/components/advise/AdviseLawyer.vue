<template>
  <!-- 자문 게시판 페이지 -->
  <div id="app">
    <div class="container">
      <v-card>
        <p>아이디:{{ $store.state.uuid }}</p>
        <p>유저타입:{{ $store.state.role }}</p>
        <v-card-title>
          MyAdvise
          <v-spacer></v-spacer>
          <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details></v-text-field>
        </v-card-title>
        <v-data-table :headers="headers" :items-per-page="5" :items="values" :search="search" @click:row="adviseDetail" class="elevation-1"></v-data-table>
      </v-card>
      <!-- <div class="btn-right">
        <md-button class="md-dense md-raised md-info" type="button" @click="writePage">
                글쓰기
              </md-button>
      </div> -->
    </div>
  </div>
</template>

<script>
import { fetchAdviseLawyer } from '@/api/advise';

export default {
  bodyClass: 'profile-page',
  data() {
    return {
      values: [],
      token: '',
      name: this.$store.state.name,
      search: '',
      headers: [
        {
          text: '카테고리',
          align: 'start',
          value: 'category',
        },
        { text: '제목', value: 'title' },
        { text: '작성자', value: 'name' },
        { text: '진행상황', value: 'state' },
        { text: '업로드시간', value: 'createDate' },
        { text: '예약시간', value: 'reservationDate' },
      ],
    };
  },
  async mounted() {
    try {
      const userData = this.$store.state.uuid;
      const { data } = await fetchAdviseLawyer(userData);
      console.log({ data });
      for (let i = 0; i < data.length; i++) {
        this.values.push({
          matchingId: data[i].matchingId,
          lawyerUuid: data[i].lawyerUuid,
          // category: data[i].category,
          uuid: data[i].uuid,
          title: data[i].title,
          state: data[i].state,
          name: data[i].name,
          // hit: data.client[i].hit,
          reservationDate: this.$moment(data[i].reservationDate).format('llll'),
          createDate: this.$moment(data[i].createDate).format('llll'),
        });
      }
      // console.log('test', this.values);
    } catch (err) {
      console.log(err);
    }
  },
  methods: {
    adviseDetail(value) {
      var matchingId = value.matchingId;
      this.$router.push({ name: 'AdviseDetail', query: { matchingId: matchingId } });
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
.btn-right {
  text-align: right;
}
// table css
.styled-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.styled-table thead tr {
  background-color: #98cec3;
  color: #ffffff;
  text-align: left;
  width: 10rem;
}
.styled-table th,
.styled-table td {
  padding: 12px 15px;
}
.styled-table tbody tr {
  border-bottom: 1px solid #dddddd;
}
//even 잡으면 호버 작동 안됨 어두운 부분.
.styled-table tbody tr:nth-of-type {
  background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
  border-bottom: 2px solid #009879;
}
tr:hover {
  color: rgb(10, 0, 0);
  background-color: rgb(163, 203, 221);
}
.inn {
  padding: 40px;
}
// select option css select,
select,
input {
  width: 200px;
  padding: 0.8em 0.5em;
  border: 1px solid #999;
  font-family: inherit;
  border-radius: 0px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}
select {
  background: url('~@/assets/img/arrow.jpg') no-repeat 95% 50%;
}

select::-ms-expand {
  display: none;
}
</style>
