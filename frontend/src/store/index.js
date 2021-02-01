import Vue from 'vue';
import Vuex from 'vuex';
// import { getAuthFromCookie, getEmailFromCookie, getPwdFromCookie, saveAuthToCookie, saveEmailToCookie, savePwdToCookie,deleteCookie } from '@/utils/cookies';
import { loginUser, socialLoginUser } from '@/api/auth';
// import { editUser, searchUser } from '../api/auth';
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
    setPassword(state, password) {
      state.password = password;
    },
    setNickname(state, nickname) {
      state.nickname = nickname;
    },
    setUuid(state, uuid) {
      state.uuid = uuid;
    },
    setName(state, name) {
      state.name = name;
    },
    clearPwd(state) {
      state.password = '';
    },
    clearNickname(state) {
      state.nickname = '';
    },
    clearName(state) {
      state.Name = '';
    },
    clearEmail(state) {
      state.email = '';
    },
    setToken(state, token) {
      state.token = token;
    },
    clearToken(state) {
      state.token = '';
    },
    // async updateNickname(state, userData) {
    //   const {data} = await editUser(userData);
    //   console.log(data.object.nickname)
    //   state.nickname = userData.nickname
    // }
  },
  actions: {
    async LOGIN({ commit }, userData) {
      const response  = await loginUser(userData);
      // const response = await searchUser(userData.email);
      console.log(response.data);

      console.log(response.data.message);
      if (response.data.code == 'LOGIN_SUCCESS') {
        commit('setToken', response.data.message);
        commit('setEmail', userData.email);
        commit('setPassword', userData.password);
        commit('setName', response.data.member.name);
        commit('setUuid', response.data.member.uuid);
        // saveAuthToCookie(data.token);
        // saveEmailToCookie(userData.email);
        // savePwdToCookie(userData.password);
        router.push('/');
      } else {
        alert('로그인 실패! 이메일 및 비밀번호를 확인해 주세요!');
      }
    },
    async SOCIALLOGIN({ commit }, userData) {
      const { data } = await socialLoginUser(userData);
      if (data.code == 'LOGIN_SUCCESS') {
        commit('setToken', data['message']);
        commit('setEmail', userData.email);
        commit('setPassword', userData.password);
        commit('setName', userData.name);
        commit('setUuid', data.member.uuid);
        router.push('/');
      } else {
        alert('로그인 실패! 이메일 및 비밀번호를 확인해 주세요!');
      }
    },
  },
});
