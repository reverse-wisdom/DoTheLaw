import { posts } from './index';

function fetchAdviseList() {
  return posts.get('/api/board/search/all');
}
function createAdvise(postData) {
  return posts.post('/api/board/create', postData);
}
function detailAdvise(boardId) {
  return posts.get(`/api/board/search/detail?boardId=${boardId}`);
}
function editAdvise(modifyData, memberRole) {
  return posts.put(`/api/board/update?role=${memberRole}`, modifyData);
}
function deleteAdvise(boardId, roleData, userId) {
  console.log(boardId, roleData, userId);
  return posts.delete(`/api/board/delete/?boardId=${boardId}&role=${roleData}&uuid=${userId}`);
}
export { fetchAdviseList, createAdvise, detailAdvise, editAdvise, deleteAdvise };
