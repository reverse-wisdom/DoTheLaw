import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    authToken: null,
  },
  getters: {},
  mutations: {
    LOGIN(state, { authToken }) {
      state.authToken = authToken;
    },
    LOGOUT(state) {
      state.authToken = null;
    },
  },
  actions: {
    LOGIN({ commit }, { email, password }) {
      // return axios.post(`${resourceHost}/login`, { email, password }).then(({ data }) => commit("LOGIN", data));
    },
    LOGOUT({ commit }) {
      commit('LOGOUT');
    },
  },
  // state 값을 localStorage에 저장하는 플러그인
  // 새로고침 시에도 state 값 유지
  plugins: [createPersistedState()],
});
