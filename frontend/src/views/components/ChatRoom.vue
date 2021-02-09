<template>
  <div class="room">
    <h1>{{ title }}</h1>
    <hr />
    <div v-for="(m, idx) in msg" :key="idx">
      <div v-bind:class="m.style">
        <h5 style="margin:3px">
          {{ m.name }}
        </h5>
        {{ m.content }}
      </div>
    </div>
    <hr />
    <input type="text" v-model="content" placeholder="보낼 메세지" size="100" />
    <button @click="sendMessage()">SEND</button>
  </div>
</template>

<script>
import { createWebChatClient } from '@/api/chat';

export default {
  name: 'ChatRoom',
  data() {
    return {
      title: '',
      content: '',
      client: null,
      msg: [],
    };
  },
  props: ['roomId'],
  mounted() {
    this.client = createWebChatClient(this.roomId, this.$store.state.uuid, this.$store.state.name, this.msg);
    this.title = this.client.getRoomTitle(this.setTitle);
    this.client.getChatBeforeMessage();
    this.client.connect();
  },
  methods: {
    setTitle(title) {
      this.title = title;
    },
    sendMessage() {
      if (this.conent != '') {
        this.client.sendMessage(this.content);
        this.content = '';
      }
    },
  },
};
</script>

<style>
.my {
  text-align: right;
  color: gray;
}
.other {
  text-align: left;
}
</style>
