import Vue from 'vue';
import Vuex from 'vuex';
import { getAuthFromCookie, getEmailFromCookie, getPwdFromCookie, saveAuthToCookie, saveEmailToCookie, savePwdToCookie } from '@/utils/cookies';
import { loginUser, socialLoginUser } from '@/api/auth';
// import { editUser, searchUser } from '../api/auth';
import createPersistedState from 'vuex-persistedstate';
import router from '../router'; // store vuex에서 라우터 사용시 다시 import 해줘야함!!

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    email: getEmailFromCookie() || '',
    token: getAuthFromCookie() || '',
    password: getPwdFromCookie() || '',
    nickname: '',
    name: ""
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
    setName(state, name) {
      state.name = name;
    },

    clearPwd(state) {
      state.password = '';
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
      const { data } = await loginUser(userData);
      // const response = await searchUser(userData.email);
      console.log(userData);

      console.log(data.message);
      if (data.code == 'LOGIN_SUCCESS') {
        commit('setToken', data['message']);
        commit('setEmail', userData.email);
        commit('setPassword', userData.password);
        // commit('setNickname', response.data.object.nickname);
        saveAuthToCookie(data.token);
        saveEmailToCookie(userData.email);
        savePwdToCookie(userData.password);
        router.push('/');
        // return data;
      } else {
        alert('로그인 실패! 이메일 및 비밀번호를 확인해 주세요!');
      }
    },
    async SOCIALLOGIN({commit}, userData) {
      const { data } = await socialLoginUser(userData);
      if (data.code == 'LOGIN_SUCCESS') {
      commit('setToken', data['message']);
      commit('setEmail', userData.email);
      commit('setPassword', userData.password);
      commit('setName', userData.name)
      console.log(data)
      } else {
        alert('로그인 실패! 이메일 및 비밀번호를 확인해 주세요!');
      }
    }
  },
});
