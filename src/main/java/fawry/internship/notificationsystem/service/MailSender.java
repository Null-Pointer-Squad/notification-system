package fawry.internship.notificationsystem.service;

import fawry.internship.notificationsystem.entity.Mail;

public interface MailSender {

    void sendMail(Mail mail);
}
