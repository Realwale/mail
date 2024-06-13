package com.charistech.mail.service;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {


    private final MailjetClient mailjetClient;

    public void sendEmail(String recipientEmail, String subject, String text) throws Exception {
        MailjetRequest request;
        MailjetResponse response;
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "SENDER_EMAIL")
                                        .put("Name", "CharisTech"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", recipientEmail)
                                                .put("Name", "Recipient Name")))
                                .put(Emailv31.Message.SUBJECT, subject)
                                .put(Emailv31.Message.TEXTPART, text)
                                .put(Emailv31.Message.HTMLPART, "<h3>" + text + "</h3>")));
        response = mailjetClient.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
