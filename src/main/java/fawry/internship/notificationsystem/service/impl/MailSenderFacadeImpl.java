package fawry.internship.notificationsystem.service.impl;


import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;
import fawry.internship.notificationsystem.exception.MailFailedException;
import fawry.internship.notificationsystem.service.MailLogger;
import fawry.internship.notificationsystem.service.MailSender;
import fawry.internship.notificationsystem.service.MailSenderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderFacadeImpl implements MailSenderFacade {

    private final MailSender mailSender;
    private final MailLogger mailLogger;
    @Override
    public void sendMail(Mail mail)
    {
       mail =  mailLogger.log(mail);


        try{
            mailSender.sendMail(mail);
            mailLogger.changeState(mail.getId(), MailStatus.SENT);}
        catch (MailFailedException ex)
        {
            mailLogger.changeState(mail.getId(),MailStatus.FAILED);
        }

    }

    @Override
    public void retryMail(Mail mail)
    {

        try {
            mailSender.sendMail(mail);
            mailLogger.changeState(mail.getId(), MailStatus.SENT);
        }
        catch (MailFailedException ex)
        {
            mailLogger.changeState(mail.getId(), MailStatus.KILLED);
        }



    }
}
