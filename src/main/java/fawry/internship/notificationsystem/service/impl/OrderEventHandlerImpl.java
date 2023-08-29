package fawry.internship.notificationsystem.service.impl;

import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;
import fawry.internship.notificationsystem.model.OrderEventModel;
import fawry.internship.notificationsystem.service.MailLogger;
import fawry.internship.notificationsystem.service.MailSenderFacade;
import fawry.internship.notificationsystem.service.OrderEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventHandlerImpl implements OrderEventHandler {

    private final MailSenderFacade mailSenderFacade;
    @Value("${sender.mail}")
    private String senderMail;
    @Override
    public void handle(OrderEventModel orderEvent)
    {
        Mail merchantMail = new Mail(senderMail,orderEvent.getMerchantMail(),"any content", MailStatus.ATTEMPT);
        Mail customerMail = new Mail(senderMail,orderEvent.getCustomerMail(),"any content", MailStatus.ATTEMPT);

        mailSenderFacade.sendMail(merchantMail);
        mailSenderFacade.sendMail(customerMail);

    }





    }



