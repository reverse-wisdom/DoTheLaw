import { instance, posts } from './index';

// 멤버 회원가입 API
function registerUser(userData) {
  return instance.post('/api/member/signup', userData);
}

// 변호사 회원가입 API
function registerLawyer(userData) {
  return instance.post('/api/member/signup/lawyer', userData);
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

// 닉네임 중복 체크
function nameCheck(userName) {
  return instance.get('/api/member/check/name', {
    params: {
      name: userName,
    },
  });
}

//이메일 중복 체크
function emailCheck(userEmail) {
  return instance.get('/api/member/check/email', {
    params: {
      email: userEmail,
    },
  });
}

// 비밀번호 찾기(임시비밀번호 생성)
function findPassword(userEmail) {
  return instance.get('/api/email/check', {
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
// 변호사 리스트
function LawyerList() {
  return posts.get('api/member/lookup/lawyer/all');
}

function LawyerDetail(lawyerData) {
  return posts.get(`api/member/lookup/lawyer?email=${lawyerData}`);
}
export { registerUser, registerLawyer, loginUser, searchUser, editUser, signoutUser, nameCheck, emailCheck, socialLoginUser, LawyerList, findPassword, LawyerDetail };
