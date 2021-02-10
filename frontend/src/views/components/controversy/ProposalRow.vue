<template>
  <v-row class="mb-1">
    <v-col>
      <v-card border-variant="info" class="mb-2" no-body>
        <template>
          <v-row class="m-1">
            <v-col class="text-left ml-3">
              <strong>{{ proposal.name }}</strong>
            </v-col>
            <v-col class="text-right mr-3">
              <!-- <md-button class="md-rose" @click="deleteComment">삭제</md-button> -->
            </v-col>
          </v-row>
        </template>
        <v-text-field color="black" :value="proposal.content" id="content"></v-text-field>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import axios from 'axios';
export default {
  name: 'CommentRow',
  props: {
    proposal: Object,
  },
  data() {
    return {
      disabled: 1,
      btnCnt: 0,
    };
  },

  methods: {
    // deleteComment() {
    //   axios
    //     .delete('/api/comment/delete?commentId=' + this.comment.commentId + '&uuid=' + this.comment.uuid)
    //     .then(({ data }) => {
    //       // this.$router.go(this.$router.currentRoute);
    //       // this.$emit('deleteComment', data);
    //     })
    //     .catch();
    // },
  },
};
</script>

<style lang="scss" scoped></style>
