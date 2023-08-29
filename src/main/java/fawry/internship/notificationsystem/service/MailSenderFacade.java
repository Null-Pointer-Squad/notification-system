package fawry.internship.notificationsystem.service;

import fawry.internship.notificationsystem.entity.Mail;

public interface MailSenderFacade {
    void sendMail(Mail mail);
    void retryMail(Mail mail);
}
