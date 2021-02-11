<template>
  <v-col cols="4" sm="6" md="4">
    <v-dialog ref="dialog" v-model="dateModal" :return-value.sync="date" persistent width="400px">
      <template v-slot:activator="{ on }">
        <v-text-field v-model="date" :label="`${label}`" prepend-icon="event" readonly v-on="on"></v-text-field>
      </template>
      <v-date-picker v-model="date" scrollable>
        <v-spacer></v-spacer>
        <v-btn color="primary" @click="dateModal = false">취소</v-btn>
        <v-btn color="primary" @click="timeModal = true">다음</v-btn>
      </v-date-picker>
    </v-dialog>

    <v-dialog ref="dialog2" v-model="timeModal" :return-value.sync="time" persistent width="400px">
      <v-time-picker v-if="timeModal" v-model="time" full-width>
        <v-spacer></v-spacer>
        <v-btn color="primary" @click="timeModal = false">취소</v-btn>
        <v-btn color="primary" @click="set()">완료</v-btn>
      </v-time-picker>
    </v-dialog>
  </v-col>
</template>

<script>
export default {
  name: 'DateTimePicker',
  props: ['label'],
  data: () => ({
    date: '',
    dateModal: false,
    time: '',
    timeModal: false,
    schedule: '',
  }),
  methods: {
    set() {
      this.date = this.date + ' ' + this.time;
      this.$refs.dialog.save(this.date);
      this.$refs.dialog2.save(this.time);
      this.$emit('date', this.date);
      // console.log(this.date);
    },
  },
};
</script>
