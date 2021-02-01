import { posts } from './index';


function createBoard(postData) {
  return posts.post('/api/board/create', postData);
}
function detailBoard(boardId) {
  console.log(boardId)
  return posts.get(`/api/board/search/detail?boardId=${boardId}`);
}

export { createBoard, detailBoard};