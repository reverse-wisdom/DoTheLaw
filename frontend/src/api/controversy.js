import { posts } from './index';

// 찬반논쟁 게시글 전체 조회
function all() {
  return posts.get(`/api/controversy/search/all`);
}

// 찬반논쟁 게시글 일부 조회
function detail(controversyId) {
  return posts.get(`/api/controversy/search/detail?controversyId=${controversyId}`);
}

// 찬성 증가
function agree(controversyId, uuid) {
  return posts.put(`/api/controversy/agree`, {
    controversyId: controversyId,
    uuid: uuid,
  });
}

// 반대 증가
function opposition(controversyId, uuid) {
  return posts.put(`/api/controversy/opposition`, {
    controversyId: controversyId,
    uuid: uuid,
  });
}

// 찬반논쟁 의견 작성
function createProposoal(proposoalData) {
  return posts.post(`/api/proposal/create`, proposoalData);
}

// 찬반논쟁 의견 전체 조회
function searchProposoal(controversyId) {
  return posts.get(`/api/proposal/search?controversyId=${controversyId}`);
}

// 찬반논쟁 의견 삭제
function deleteProposoal(opinionId, uuid) {
  return posts.delete(`/api/proposal/delete?opinionId=${opinionId}&uuid=${uuid}`);
}

export { all, detail, agree, opposition, createProposoal, searchProposoal, deleteProposoal };
