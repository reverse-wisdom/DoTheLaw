<template>
  <v-row class="mb-1">
    <v-col>
      <v-card border-variant="info" class="mb-2" no-body>
        <template>
          <v-row class="m-1">
            <v-col class="text-left ml-3">
              <strong>{{ proposal.name }}</strong>
            </v-col>
            <template v-if="proposal.uuid == $store.state.uuid">
              <v-col class="text-right mr-3">
                <md-button class="md-rose" @click="deleteProposal">삭제</md-button>
              </v-col>
            </template>
          </v-row>
        </template>
        <v-text-field color="black" :value="proposal.content" id="content"></v-text-field>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { deleteProposoal } from '@/api/controversy';
export default {
  name: 'CommentRow',
  props: {
    proposal: Object,
  },
  data() {
    return {};
  },

  methods: {
    async deleteProposal() {
      var uuid = this.$store.state.uuid;
      const { data } = await deleteProposoal(this.proposal.opinionId, uuid);
      if (data == 'SUCCESS') {
        this.$swal({
          position: 'top-end',
          icon: 'success',
          title: '의견 삭제 성공!!',
          showConfirmButton: false,
          timer: 1500,
        });
        this.$emit('updateProposal');
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
