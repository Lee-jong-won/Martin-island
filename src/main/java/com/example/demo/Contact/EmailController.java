package com.example.demo.Contact;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class EmailController {

    private final EmailSenderService emailSenderService;

    @PostMapping("/blog/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody MailDTO mailDTO)
    {
        try {
            emailSenderService.sendMail(mailDTO);
            return ResponseEntity.ok("Email sent successfully");
        }catch(MailException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }

}
