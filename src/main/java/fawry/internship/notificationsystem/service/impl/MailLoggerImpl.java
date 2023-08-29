package fawry.internship.notificationsystem.service.impl;

import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;
import fawry.internship.notificationsystem.repository.MailRepo;
import fawry.internship.notificationsystem.service.MailLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailLoggerImpl implements MailLogger {
    private final MailRepo mailRepo;
    @Override
    public Mail log(Mail mail) {

       return mailRepo.save(mail);

    }

    @Override
    public void changeState(String id, MailStatus newStatus)
    {
        //TODO : handle notFoundCase ???
      Mail mail =  mailRepo.findById(id).get();
      mail.setStatus(newStatus);
      mailRepo.save(mail);


    }
}
