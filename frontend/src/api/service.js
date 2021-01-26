import { instance } from './index';

// 뉴스 RSS 가져오기
function newsParsing() {
  var test = instance.get('api/rss/news');
  console.log(test);
  return test;
  // return instance.get('api/rss/news');
}

export { newsParsing };
