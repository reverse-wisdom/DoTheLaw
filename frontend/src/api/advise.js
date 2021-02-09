import { posts } from './index';

function fetchAdviseLawyer(userData) {
  return posts.get('/api/matching/search/', userData);
}
function fetchAdviseMe(userData) {
  return posts.get(`/api/matching/contain?uuid=${userData}`);
}
function createAdvise(adviseData) {
  return posts.post('/api/matching/create/', adviseData);
}
function detailAdvise(adviseData) {
  return posts.get(`/api/matching/search/detail?matchingId=${adviseData}`);
}
function editAdvise(modifyData) {
  return posts.put('api/matching/update/', modifyData);
}
function deleteAdvise(userData, adviseData, userId) {
  return posts.delete(`api/matching/delete?matchingId=${adviseData}&uuid=${userData}`);
}
export { fetchAdviseLawyer, createAdvise, detailAdvise, editAdvise, deleteAdvise, fetchAdviseMe };
