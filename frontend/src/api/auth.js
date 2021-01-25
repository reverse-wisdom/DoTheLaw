import { instance } from './index';

// 회원가입 API
function registerUser(userData) {
  return instance.post('api/v1/singup', userData);
}

// 로그인 API
function loginUser(userData) {
  return instance.post('api/v1/login', userData);
}

//회원정보 조회
function searchUser(userEmail) {
  return instance.get('account/search', {
    params: {
      email: userEmail,
    },
  });
}
//이메일 중복 체크
function emailCheck(userEmail) {
  return instance.get('api/v1/singup/check', {
    params: {
      email: userEmail,
    },
  });
}
//회원정보 수정
function editUser(userData) {
  // console.log(userData)
  return instance.put('account/update', userData);
}
//회원탈퇴
function signoutUser(userData) {
  console.log(userData);
  return instance.delete('account/signout', {
    params: {
      email: userData,
    },
  });
}
export { registerUser, loginUser, searchUser, editUser, signoutUser, emailCheck };
