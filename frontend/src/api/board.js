import { posts } from './index';

function fetchBoardList() {
  return posts.get('/api/board/search/all');
}
function createBoard(postData) {
  return posts.post('/api/board/create', postData);
}
function detailBoard(boardId) {
  return posts.get(`/api/board/search/detail?boardId=${boardId}`);
}
function editBoard(modifyData, memberRole) {
  return posts.put(`/api/board/update?role=${memberRole}`, modifyData);
}
function deleteBoard(boardId, roleData, userId) {
  console.log(boardId, roleData, userId);
  return posts.delete(`/api/board/delete/?boardId=${boardId}&role=${roleData}&uuid=${userId}`);
}
export { fetchBoardList, createBoard, detailBoard, editBoard, deleteBoard };
