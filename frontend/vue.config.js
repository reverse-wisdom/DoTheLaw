module.exports = {
  // 개발 서버 설정
  devServer: {
    // 프록시 설정
    proxy: {
      // 프록시 요청을 보낼 api의 시작 부분
      '/api': {
        // 프록시 요청을 보낼 서버의 주소 (백엔드 서버로)
        target: 'http://localhost:8080',
      },
    },
    // vuejs 프론트엔드 개발 포트 지정 (환경변수 VUE_APP_PORT가 없으면 3000 포트)
    port: process.env.VUE_APP_PORT || 3000,
  },
  css: {
    loaderOptions: {
      css: {
        sourceMap: process.env.NODE_ENV !== 'production' ? true : false,
      },
    },
  },
};
