import { posts } from './index';


function createBoard(postData) {
  return posts.post('/api/board/create', postData);
}
function detailBoard(boardId) {
  // console.log(boardId)
  return posts.get(`/api/board/search/detail?boardId=${boardId}`);
}
function editBoard(modifyData, memberRole) {
  // console.log(modifyData, memberRole)
  return posts.put(`/api/board/update?role=${memberRole}`, modifyData);
}
function deleteBoard(boardId,roleData,userId) {
  console.log(boardId,roleData,userId)
  return posts.delete(`/api/board/delete/?boardId=${boardId}&role=${roleData}&uuid=${userId}`)
}
export { createBoard, detailBoard, editBoard, deleteBoard};