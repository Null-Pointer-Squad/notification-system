package fawry.internship.notificationsystem.service.impl;

import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.service.MailSender;
import org.springframework.stereotype.Service;

@Service
public class DummyMailSenderImpl implements MailSender {
    @Override
    public void sendMail(Mail mail)
    {

        System.out.println("mail sent to: "
                + mail.getTo()
                + " from: "
                + mail.getFrom()
                + " with message: "
                + mail.getMessage());
    }
}
