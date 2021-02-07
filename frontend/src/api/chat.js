import { posts } from './index';
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

class WebChatClient {
  constructor(roomId, uuid, msg) {
    this.roomId = roomId;
    this.uuid = uuid;
    this.msg = msg;
    this.stompClient = null;
  }
  //방 제목 가져오기
  getRoomTitle() {
    return posts.get('/api/chat/room/' + this.roomId);
  }

  // 채팅방 기존 내용 가져오기
  getChatBeforeMessage() {
    posts.get('/api/chat/room/message/' + this.roomId).then((res) => {
      this.msg.length = 0; //기존 메세지들 모두 삭제
      for (let i = res.data.length - 1; i > -1; i--) {
        let m = {
          name: res.data[i].name,
          content: res.data[i].content,
          style: res.data[i].senderId == this.uuid ? 'me' : 'other',
        };
        this.msg.push(m);
      }
    }),
      (err) => {
        console.log(err);
        alert('error : 이전 채팅을 가져오는데 실패했어요. 새로고침하세요');
      };
  }

  // 소켓 연결
  connect() {
    let socket = new SockJS('/api/ws');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect(
      {},
      (frame) => {
        console.log('success', frame);
        this.stompClient.subscribe('/sub/' + this.roomid, (res) => {
          let jsonBody = JSON.parse(res.body);
          let m = {
            name: jsonBody.name,
            content: jsonBody.content,
            style: jsonBody.uuid == this.uuid ? 'me' : 'other',
          };
          this.msg.push(m);
        });
      },
      (err) => {
        console.log('fail', err);
      }
    );
  }
  sendMessage(msg) {
    if (msg.trim() != '' && this.stompClient != null) {
      let chatMessage = {
        matchingId: this.roomid,
        uuid: this.id,
        content: msg,
      };
      this.stompClient.send('/pub/message', JSON.stringify(chatMessage), {});
    }
  }
}

export { WebChatClient };
