import axios from 'axios';
import store from '../store/index.js';

function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    headers: {
      Authorization: store.state.token, // store에서 불러오기
    },
  });
}

export const instance = createInstance();
