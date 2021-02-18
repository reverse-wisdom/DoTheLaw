import { posts } from './index';

/** 생활법령 메인 카테고리 리스트 */
function getEasyLawMain(callback, errCallback) {
  posts
    .get('/api/easylaw/main')
    .then((response) => callback(response))
    .catch((err) => {
      if (errCallback) errCallback(err);
    });
}
/** 생활법령 카테고리별 법령 리스트 */
function getEasyLawList(type, callback, errCallback) {
  posts
    .get('/api/easylaw/list', { params: { type } })
    .then((response) => callback(response))
    .catch((err) => {
      if (errCallback) errCallback(err);
    });
}
/** 생활법령 상세 정보 */
function getEasyLawDetail(no, callback, errCallback) {
  return posts
    .get('/api/easylaw/detail', { params: { no } })
    .then((response) => callback(response))
    .catch((err) => {
      if (errCallback) errCallback(err);
    });
}

export { getEasyLawMain, getEasyLawList, getEasyLawDetail };
