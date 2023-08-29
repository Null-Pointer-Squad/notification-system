package fawry.internship.notificationsystem.service.impl;

import fawry.internship.notificationsystem.entity.MailStatus;
import fawry.internship.notificationsystem.repository.MailRepo;
import fawry.internship.notificationsystem.service.MailSenderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FailedMailHandler {


    private final MailRepo mailRepo;
    private final MailSenderFacade mailSenderFacade;
    @Value("${sender.mail}")
    private String senderMail;

    @Value("${mail.retry.amount}")
    private Integer mailsToRetryAmount;

    @Scheduled(cron ="${mail.retry.cron}" )
    public void retryFailedMails()
    {
        mailRepo.findAllByStatus(MailStatus.FAILED, Pageable.ofSize(mailsToRetryAmount))
                .stream()
                .forEach((mail ->mailSenderFacade.retryMail(mail) ));

    }






}
