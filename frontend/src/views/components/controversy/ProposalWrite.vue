<template>
  <div class="text-right">
    <v-textarea label="의견입력" rows="1" v-model="content" prepend-icon="mdi-comment"></v-textarea>
    <v-btn color="success" @click="onSubmit">의견등록</v-btn>
  </div>
</template>

<script>
import { createProposoal } from '@/api/controversy';

export default {
  name: 'CommentWrite',
  data() {
    return {
      content: '',
    };
  },
  props: {
    controversyId: Number,
  },
  methods: {
    async onSubmit() {
      const proposoalData = {
        content: this.content,
        controversyId: this.controversyId,
        uuid: this.$store.state.uuid,
      };
      const { data } = await createProposoal(proposoalData);

      if (data == 'SUCCESS') {
        this.$swal({
          position: 'top-end',
          icon: 'success',
          title: '의견 등록 성공!!',
          showConfirmButton: false,
          timer: 1500,
        });
        this.content = '';

        this.$emit('updateProposal');
      }
    },
  },
};
</script>

<style></style>
