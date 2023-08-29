package fawry.internship.notificationsystem.service;

import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;
import fawry.internship.notificationsystem.exception.MailFailedException;
import fawry.internship.notificationsystem.model.OrderEventModel;
import fawry.internship.notificationsystem.service.impl.OrderEventHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderEventHandlerTest {


    @Mock
    MailSenderFacade mailSenderFacade;
    OrderEventHandler orderEventHandler;


    @BeforeEach
    void setUp()
    {
        orderEventHandler = new OrderEventHandlerImpl(mailSenderFacade);
    }

    @Test
    public void GivenOrderEvent_WhenHandlingOrderEvent_ThenLogAndSend()
    {
        OrderEventModel orderEvent = new OrderEventModel(1,"anyMail@dummy.com","anyMail@dummy.com");

        Mail merchantMail = new Mail(null,orderEvent.getMerchantMail(),"any content", MailStatus.ATTEMPT);
        Mail customerMail = new Mail(null,orderEvent.getCustomerMail(),"any content", MailStatus.ATTEMPT);
        orderEventHandler.handle(orderEvent);
        verify(mailSenderFacade,times(2)).sendMail(Mockito.any());

    }




}
