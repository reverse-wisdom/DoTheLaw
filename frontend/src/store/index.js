import Vue from 'vue';
import Vuex from 'vuex';
import { loginUser, socialLoginUser } from '@/api/auth';
import createPersistedState from 'vuex-persistedstate';
import router from '../router'; // store vuex에서 라우터 사용시 다시 import 해줘야함!!

import VueSweetalert2 from 'vue-sweetalert2';

Vue.use(Vuex);

const options = {
  confirmButtonColor: '#41b882',
  cancelButtonColor: '#ff7674',
};

Vue.use(VueSweetalert2, options); // alert API

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    email: '',
    token: '',
    password: '',
    nickname: '',
    name: '',
    // image: '',
    phone: '',
    uuid: '',
    role: '',
    lawuuid: '',
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
    setLawuuid(state, lawuuid) {
      state.lawuuid = lawuuid;
    },
    clearLawuuid(state) {
      state.lawuuid = '';
    },

    setPhone(state, phone) {
      state.phone = phone;
    },

    clearPhone(state) {
      state.phone = '';
    },
    // setImage(state, image) {
    //   state.image = image;
    // },

    // clearImage(state) {
    //   state.image = '';
    // },
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
      var data;
      try {
        data = await loginUser(userData);
        if (data.data.code == 'LOGIN_SUCCESS') {
          console.log('test');
          commit('setToken', data.data['message']);
          commit('setEmail', data.data.member.email);
          commit('setPassword', userData.password);
          commit('setName', data.data.member.name);
          commit('setUuid', data.data.member.uuid);
          commit('setPhone', data.data.member.phone);
          // commit('setImage', data.data.member.image);
          commit('setRole', data.data.member.role.substring(5).trim());
          router.push('/');
        }
      } catch (err) {
        Vue.swal({
          icon: 'error',
          title: '로그인 실패! 이메일 및 비밀번호를 확인해 주세요!',
        });
      }
    },
    async SOCIALLOGIN({ commit }, userData) {
      const { data } = await socialLoginUser(userData);
      console.log(data);
      if (data.code == 'LOGIN_SUCCESS') {
        commit('setToken', data['message']);
        commit('setEmail', userData.email);
        commit('setPassword', userData.password);
        commit('setUuid', data.member.uuid);
        commit('setName', data.member.name);
        commit('setPhone', data.member.phone);
        // commit('setImage', data.member.image);
        commit('setRole', data.member.role.substring(5).trim());
        router.push('/');
      } else {
        alert('로그인 실패! 이메일 및 비밀번호를 확인해 주세요!');
      }
    },
  },
});
