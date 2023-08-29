package fawry.internship.notificationsystem.service;

import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;

public interface MailLogger {

    Mail log(Mail email);
    void changeState(String id, MailStatus newStatus);
}
