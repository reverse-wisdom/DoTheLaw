import { posts } from './index';

// 찬반논쟁 게시글 전체 조회
function all() {
  return posts.get(`/api/controversy/search/all`);
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

export { all, agree, opposition };
