package com.devtyagi.maalgaadi.service;

import com.devtyagi.maalgaadi.dao.User;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${email.apikey}")
    private String API_KEY;

    @Value("${email.sender}")
    private String sender;

    public void sendOtpViaEmail(User user, int otp) throws IOException {
        String subject = "OTP For MaalGaadi Login";
        String emailBody = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\n" +
                "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\n" +
                "    <div style=\"border-bottom:1px solid #eee\">\n" +
                "      <h2 style=\"color: #00466a\">Maal Gaadi</h2>\n" +
                "    </div>\n" +
                "    <p style=\"font-size:1.1em\">Hi, "+ user.getName() +"</p>\n" +
                "    <p>Thank you for choosing MaalGaadi. Use the following OTP to login. OTP is valid for 5 minutes</p>\n" +
                "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">"+ otp +"</h2>\n" +
                "    <p style=\"font-size:0.9em;\">Regards,<br />TheTwoNoobs</p>\n" +
                "    <hr style=\"border:none;border-top:1px solid #eee\" />\n" +
                "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\n" +
                "      <p>MaalGaadi</p>\n" +
                "      <p>Dev Pratap Tyagi and Hritik Rai</p>\n" +
                "      <p>Flipr Hackathon</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>";
        sendEmail(user.getEmail(), subject, emailBody);
    }

    public String sendEmail(String receiver, String subject, String body) throws IOException {
        Email from = new Email(sender);
        Email to = new Email(receiver);
        Content content = new Content("text/html", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);

        return response.getBody();
    }

}
