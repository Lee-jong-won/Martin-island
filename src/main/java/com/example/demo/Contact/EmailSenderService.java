package com.example.demo.Contact;

import com.example.demo.Contact.MailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public void sendMail(MailDTO mailDTO) throws MailException
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wwwl7749@gachon.ac.kr");
        message.setTo("dlwhddnjs951@naver.com");

        String body = "보낸이 : " + mailDTO.getName() + '\n'
                      +"학년 : " + mailDTO.getGrade() + '\n'
                      +"이메일 : " + mailDTO.getEmail() + '\n'
                      +"핸드폰 번호 :" + mailDTO.getPhoneNumber() + '\n'
                      +"메시지 내용 :" + mailDTO.getMessage();

        message.setText(body);
        message.setSubject("테스트 메일입니다");

        mailSender.send(message);
        System.out.println("Mail Sent successfully...");
    }
}
