import { instance } from './index';

// 회원가입 API
function registerUser(userData) {
  return instance.post('/api/member/signup', userData);
}

// 로그인 API
function loginUser(userData) {
  return instance.post('/api/member/login', userData);
}

// 소셜 로그인 API
function socialLoginUser(userData) {
  return instance.post('api/member/social', userData);
}

//회원정보 조회
function searchUser(userEmail) {
  return instance.get('/api/member/search', {
    params: {
      email: userEmail,
    },
  });
}
//이메일 중복 체크
function emailCheck(userEmail) {
  return instance.get('/api/member/check', {
    params: {
      email: userEmail,
    },
  });
}
//회원정보 수정
function editUser(userData) {
  // console.log(userData)
  return instance.put('/api/member/update', userData);
}
//회원탈퇴
function signoutUser(userData) {
  console.log(userData);
  return instance.delete('/api/member/signout', {
    params: {
      email: userData,
    },
  });
}

export { registerUser, loginUser, searchUser, editUser, signoutUser, emailCheck, socialLoginUser };
