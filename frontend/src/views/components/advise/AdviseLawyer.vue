<template>
  <!-- 자문 게시판 페이지 -->
  <li>
    <div class="post-title">
      {{ data.title }}
    </div>
    <div class="post-contents" v-html="data.content">
      {{ data.content }}
    </div>
    <div class="post-time">
      {{ data.createDate }}
      <i class="icon ion-md-create" @click="adviseDetail"></i>
      <!-- <i class="icon ion-md-trash" ></i> -->
    </div>
  </li>
  <!-- <v-card>
        <v-card-title>
          MyAdvise
          <v-spacer></v-spacer>
          <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details></v-text-field>
        </v-card-title>
        <v-data-table :headers="headers" :items-per-page="5" :items="values" :search="search" @click:row="adviseDetail" class="elevation-1"></v-data-table>
      </v-card> -->
</template>

<script>
export default {
  bodyClass: 'profile-page',
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      token: '',
      name: this.$store.state.name,
      search: '',
      // headers: [
      //   {
      //     text: '카테고리',
      //     align: 'start',
      //     value: 'category',
      //   },
      //   { text: '제목', value: 'title' },
      //   { text: '작성자', value: 'name' },
      //   { text: '진행상황', value: 'state' },
      //   { text: '업로드시간', value: 'createDate' },
      //   { text: '예약시간', value: 'reservationDate' },
      // ],
    };
  },
  methods: {
    adviseDetail(value) {
      var matchingId = this.data.matchingId;
      this.$router.push({ name: 'AdviseDetail', query: { matchingId: matchingId } });
    },
    // getColor(name) {
    //   var colors = ['red', 'green', 'blue', 'orange', 'yellow'];
    //   return colors[Math.floor(Math.random() * colors.length)];
    // },
  },
};
</script>

<style lang="scss" scoped>
li {
  list-style: none;
}
.post-title {
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 0.5rem;
}
.post-contents {
  height: 160px;
  overflow-y: auto;
  font-size: 18px;
}
.post-time {
  position: absolute;
  bottom: 4px;
  right: 5px;
  font-size: 14px;
  color: #9e9e9e;
}
li {
  position: relative;
  flex-grow: 1;
  width: 320px;
  height: 250px;
  margin: 7px;
  padding: 10px 20px;
  background: white;
  box-shadow: 0 20px 20px rgba(0, 0, 0, 0.08);
  border-radius: 3px;
}
.icon {
  font-size: 1.3rem;
  cursor: pointer;
  color: #364f6b;
  padding-right: 0.4rem;
}
.icon:hover {
  color: #3fc1c9;
}
.icon:active {
  color: #fc5185;
}
.ion-md-create {
  padding-left: 0.1rem;
}
// hr 설정
// #app {
//   background: gray;
// }
// .div-hr {
//   width: 80%;
// }

// 한글 폰트 설정
// .kor {
//   font-family: 'Nanum Gothic', sans-serif;
// }
// .btn-right {
//   text-align: right;
// }
// // table css
// .styled-table {
//   border-collapse: collapse;
//   margin: 25px 0;
//   font-size: 0.9em;
//   font-family: sans-serif;
//   min-width: 400px;
//   box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
// }
// .styled-table thead tr {
//   background-color: #98cec3;
//   color: #ffffff;
//   text-align: left;
//   width: 10rem;
// }
// .styled-table th,
// .styled-table td {
//   padding: 12px 15px;
// }
// .styled-table tbody tr {
//   border-bottom: 1px solid #dddddd;
// }
// //even 잡으면 호버 작동 안됨 어두운 부분.
// .styled-table tbody tr:nth-of-type {
//   background-color: #f3f3f3;
// }

// .styled-table tbody tr:last-of-type {
//   border-bottom: 2px solid #009879;
// }
// tr:hover {
//   color: rgb(10, 0, 0);
//   background-color: rgb(163, 203, 221);
// }
// .inn {
//   padding: 40px;
// }
// // select option css select,
// select,
// input {
//   width: 200px;
//   padding: 0.8em 0.5em;
//   border: 1px solid #999;
//   font-family: inherit;
//   border-radius: 0px;
//   -webkit-appearance: none;
//   -moz-appearance: none;
//   appearance: none;
// }
// select {
//   background: url('~@/assets/img/arrow.jpg') no-repeat 95% 50%;
// }

// select::-ms-expand {
//   display: none;
// }
</style>
