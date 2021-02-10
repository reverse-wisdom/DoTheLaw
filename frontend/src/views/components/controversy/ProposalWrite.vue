<template>
  <div class="text-right">
    <v-textarea label="댓글입력" rows="1" v-model="content" prepend-icon="mdi-comment"></v-textarea>
    <v-btn color="success" @click="onSubmit">댓글등록</v-btn>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'CommentWrite',
  data() {
    return {
      commentId: '',
      content: '',
      name: '',
    };
  },
  props: {
    boardId: Number,
  },
  methods: {
    onSubmit() {
      this.token = this.$store.state.token;
      axios
        .post('/api/comment/create', {
          boardId: this.boardId,
          content: this.content,
          uuid: this.$store.state.uuid,
        })
        .then((data) => {
          this.$emit('uploadComment');
          this.content = '';
        })
        .catch();
      //     .then(({ data }) => {
      //       this.$router.go(this.$router.currentRoute);
      //       // this.$router.go(-1);
      //     })
      //     .catch();
      // },
    },
  },
};
</script>

<style></style>
