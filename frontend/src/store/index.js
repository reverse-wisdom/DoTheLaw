import Vue from 'vue';
import Vuex from 'vuex';
import { loginUser, socialLoginUser } from '@/api/auth';
import createPersistedState from 'vuex-persistedstate';
import router from '../router'; // store vuex에서 라우터 사용시 다시 import 해줘야함!!

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    email: '',
    token: '',
    password: '',
    nickname: '',
    name: '',
    uuid: '',
    role: '',
  },
  getters: {
    isLogin(state) {
      return state.email !== '';
    },
  },
  mutations: {
    setEmail(state, email) {
      state.email = email;
    },
    clearEmail(state) {
      state.email = '';
    },
    setPassword(state, password) {
      state.password = password;
    },
    clearPassword(state) {
      state.password = '';
    },
    setNickname(state, nickname) {
      state.nickname = nickname;
    },
    clearNickname(state) {
      state.nickname = '';
    },

    setName(state, name) {
      state.name = name;
    },
    clearName(state) {
      state.name = '';
    },
    setRole(state, role) {
      state.role = role;
    },
    clearRole(state) {
      state.role = '';
    },
    setToken(state, token) {
      state.token = token;
    },
    clearToken(state) {
      state.token = '';
    },
    setUuid(state, uuid) {
      state.uuid = uuid;
    },
    clearUuid(state) {
      state.uuid = '';
    },
  },
  actions: {
    async LOGIN({ commit }, userData) {
      const { data } = await loginUser(userData);
      console.log(data);
      if (data.code == 'LOGIN_SUCCESS') {
        commit('setToken', data['message']);
        commit('setEmail', data.member.email);
        commit('setPassword', data.member.password);
        commit('setName', data.member.name);
        commit('setUuid', data.member.uuid);
        commit('setRole', data.member.role.substring(5).trim());
        router.push('/');
      } else {
        alert('로그인 실패! 이메일 및 비밀번호를 확인해 주세요!');
      }
    },
    async SOCIALLOGIN({ commit }, userData) {
      const res = await socialLoginUser(userData);
      console.log(res);
      if (res.data.code == 'LOGIN_SUCCESS') {
        commit('setToken', res.data['message']);
        commit('setEmail', userData.email);
        commit('setPassword', userData.password);
        commit('setName', userData.name);
        commit('setUuid', userData.uuid);
        router.push('/');
      } else {
        alert('로그인 실패! 이메일 및 비밀번호를 확인해 주세요!');
      }
    },
  },
});
