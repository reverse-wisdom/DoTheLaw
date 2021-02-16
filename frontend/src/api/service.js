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

function getTodayYoutube() {
  return posts.get('/api/youtube');
}

// 이미지 업로드
function imageUpload(uuid, form) {
  return posts.post(`/api/member/image/update/${uuid}`, form, { 'Content-Type': 'multipart/form-data' });
}

// 이미지 불러오기
function saveImage(uuid) {
  return posts.get(`/api/member/image/${uuid}`);
}
export { top, lawService, lawSearch, lawDict, newsParsing, getTodayYoutube, imageUpload, saveImage };
