<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <h2 class="title text-center kor">게시판 상세 내용</h2>

        <hr class="div-hr" />
        <div class="container">
          <table class="styled-table" style="width: 100%">
            <tr>
              <th scope="col">제목</th>
              <td>{{ value.title }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">작성자</th>
              <td>{{ value.name }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">작성시간</th>
              <td>{{ this.$moment(value.uploadDate).format('llll') }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">조회수</th>
              <td>{{ value.hit }}</td>
            </tr>
            <tr style="border-top: 1px solid;">
              <th scope="col">내용</th>
              <td v-html="value.content"></td>
            </tr>
          </table>

          <!-- 수정, 삭제는 자신이 쓴글일경우만 나타나게 처리함 -->
          <div v-if="$store.state.name == value.name" style="text-align:right">
            <md-button class="md-warning" @click="updatePage(value)">글수정</md-button>
            <md-button class="md-rose" @click="deletePage()">글 삭제</md-button>
            <md-button class="md-info" @click="moveBoard()">뒤로가기</md-button>
          </div>
          <!-- else -->
          <div v-if="$store.state.name != value.name" style="text-align:right">
            <md-button class="md-info" @click="moveBoard()">뒤로가기</md-button>
          </div>
          <!-- 댓글 -->
          <comment-write :boardId="value.boardId" @uploadComment="uploadComment" />
          <br />
          <ul v-for="(comment, index) in comments" :key="index">
            <comment-row :comment="comment" :boardId="value.boardId" @deleteComment="deleteComment" />
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { detailBoard, deleteBoard } from '@/api/board';
import CommentWrite from './CommentWrite';
import CommentRow from './CommentRow.vue';
import axios from 'axios';

export default {
  bodyClass: 'profile-page',
  components: {
    CommentWrite,
    CommentRow,
  },
  data() {
    return {
      value: '',
      comments: [],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/bg_board.jpg'),
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
    const postData = this.$route.query.boardId;
    const { data } = await detailBoard(postData);
    this.value = data;
    axios
      .get('/api/comment/search?boardId=' + this.$route.query.boardId, {})
      .then(({ data }) => {
        this.comments = data;
      })
      .catch();
  },
  methods: {
    moveBoard() {
      this.$router.push('/board');
    },
    async deletePage() {
      const boardId = this.value.boardId;
      const role = this.$store.state.role;
      const userId = this.$store.state.uuid;
      const { data } = await deleteBoard(boardId, role, userId);
      this.$swal({
        position: 'top-end',
        icon: 'success',
        title: '삭제성공!!',
        showConfirmButton: false,
        timer: 1500,
      });
      this.$router.push('/board');
    },
    updatePage(value) {
      var boardId = value.boardId;
      this.$router.push({ name: 'boardUpdate', query: { boardId: boardId } });
    },
    uploadComment(newComment) {
      axios
        .get('/api/comment/search?boardId=' + this.$route.query.boardId, {})
        .then(({ data }) => {
          this.comments = data;
        })
        .catch();
    },
    deleteComment(oldComment) {
      const commentIndex = this.comments.findIndex((comment) => {
        return comment.comment_id === oldComment.comment_id;
      });
      this.comments.splice(commentIndex, 1);
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
// table css
.styled-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.styled-table th {
  background-color: #98cec3;
  color: #ffffff;
  width: 10rem;
  text-align: center;
}
.styled-table th,
.styled-table td {
  padding: 12px 15px;
}
</style>
