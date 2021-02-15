<template>
  <v-row class="mb-1">
    <v-col>
      <v-card border-variant="info" class="mb-2" no-body>
        <template>
          <v-row class="m-1">
            <v-col class="text-left ml-3">
              <strong>{{ comment.name }}</strong>
            </v-col>
            <template v-if="comment.uuid == $store.state.uuid">
              <v-col class="text-right mr-3">
                <v-btn color="rgb(33,133,89)" class="mr-1" dark @click="modifyComment">수정</v-btn>
                <v-btn color="red" dark @click="deleteComment">삭제</v-btn>
              </v-col>
            </template>
          </v-row>
        </template>
        <v-text-field v-model="comment.content" class="px-3" :readonly="disabled == 1" color="black"></v-text-field>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import axios from 'axios';
export default {
  name: 'CommentRow',
  props: {
    comment: Object,
  },
  data() {
    return {
      disabled: 1,
      btnCnt: 0,
    };
  },

  methods: {
    deleteComment() {
      axios
        .delete('/api/comment/delete?commentId=' + this.comment.commentId + '&uuid=' + this.comment.uuid)
        .then(({ data }) => {
          this.$emit('deleteComment', data);
        })
        .catch();
    },

    modifyComment() {
      this.btnCnt++;
      if (this.btnCnt == 1) {
        this.disabled = (this.disabled + 1) % 2;
      } else {
        axios.put('/api/comment/update', {
          commentId: this.comment.commentId,
          content: this.comment.content,
          uuid: this.$store.state.uuid,
        });
        this.btnCnt = 0;
        this.disabled = 1;
        this.$emit('modifyComment');
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
