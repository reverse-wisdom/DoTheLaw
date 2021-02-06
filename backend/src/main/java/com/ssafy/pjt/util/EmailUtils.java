package com.ssafy.pjt.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ssafy.pjt.core.service.dto.MemberDTO;

public class EmailUtils {
	public static void gmailSend(MemberDTO taget,String pass) {
		String host = "smtp.naver.com";
		String user = "entkd25@naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "VKFfkels@2";   // 패스워드
        
        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties(); 
        props.put("mail.smtp.host", host); 
        props.put("mail.smtp.port", 587); 
        props.put("mail.smtp.auth", "true");

        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(taget.getEmail())); 

            // Subject
            message.setSubject("법대로합시다 임시 비밀번호"); //메일 제목을 입력
    
            // Text    
            StringBuilder msg = new StringBuilder();
            msg.append("<div align='left'>");
            msg.append("<h3>");
            msg.append(taget.getName());
            msg.append("님의 임시 비밀번호입니다.<br>비밀번호를 변경하여 사용하세요.</h3>");
            msg.append("<p>임시 비밀번호 : ");
            msg.append(pass);
            msg.append("</p></div>");
            msg.append("<div>홈페이지로 이동하기 : <a href=\"https://i4d103.p.ssafy.io\">법대로합시다</a></div>");
            

            message.setContent(msg.toString(), "text/html;charset=utf-8");  //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; 
		int idx = 0; StringBuffer sb = new StringBuffer(); 
		System.out.println("charSet.length :::: "+charSet.length); 
		for (int i = 0; i < len; i++) { 
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거) 
			System.out.println("idx :::: "+idx); 
			sb.append(charSet[idx]); 
			} 
		return sb.toString(); 
		}


}
