module.exports = {
  devServer: {
    https: true,
    proxy: {
      //프록시 테이블 설정
      // "/webrtc": {
      //   target: "https://rtcmulticonnection.herokuapp.com:443/",
      //   changeOrigin: true
      // }
    }
  }
};
