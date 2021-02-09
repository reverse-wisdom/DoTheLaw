import { posts } from './index';

// 검색어 순위
function top() {
  return posts.get('/api/search/top');
}

// 판례검색
function lawSearch(query) {
  return posts.get(`/api/search/lawSearch?query=${query}`);
}

// 판례번호로 상세조회
function lawService(ID) {
  return posts.get(`/api/search/lawService?ID=${ID}`);
}

// 법률 단어 사전 가져오기
function lawDict() {
  return posts.get(`/api/search/dict`);
}

// 뉴스 RSS 가져오기
function newsParsing() {
  return posts.get('/api/rss/news');
}

function imageUpload(uuid, form) {
  return posts.post(`/api/member/image/update/${uuid}`, form, { 'Content-Type': 'multipart/form-data' });
}
// form, { 'Content-Type': 'multipart/form-data'}

export { top, lawService, lawSearch, lawDict, newsParsing, imageUpload };
