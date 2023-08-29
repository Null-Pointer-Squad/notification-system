package fawry.internship.notificationsystem.service;


import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;
import fawry.internship.notificationsystem.exception.MailFailedException;
import fawry.internship.notificationsystem.model.OrderEventModel;
import fawry.internship.notificationsystem.service.impl.MailSenderFacadeImpl;
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
public class MailSenderFacadeTest
{
    @Mock
    MailLogger mailLogger;
    @Mock
    MailSender mailSender;

    MailSenderFacade mailSenderFacade;


    @BeforeEach
    void setUp()
    {
        mailSenderFacade = new MailSenderFacadeImpl(mailSender,mailLogger);
    }

    @Test
    public void GivenMailSuccess_WhenSendingMail_ThenLogAndSend()
    {

        Mail mail = new Mail("anyMail@dummy.com","anyMail@dummy.com", "message", MailStatus.ATTEMPT);
        Mockito.when(mailLogger.log(Mockito.any())).thenReturn(mail);
        mailSenderFacade.sendMail(mail);
        verify(mailSender,times(1)).sendMail(Mockito.any());
        verify(mailLogger,times(1)).changeState(Mockito.any(),Mockito.eq(MailStatus.SENT));

    }

    @Test
    public void GivenMailFailedException_WhenHandlingOrderEvent_ThenChangeStateToFailed()
    {
        Mail mail = new Mail("anyMail@dummy.com","anyMail@dummy.com", "message",MailStatus.ATTEMPT);

        Mockito.when(mailLogger.log(Mockito.any())).thenReturn(mail);
        Mockito.doThrow(MailFailedException.class).when(mailSender).sendMail(Mockito.any());

        mailSenderFacade.sendMail(mail);

        verify(mailSender,times(1)).sendMail(Mockito.any());
        verify(mailLogger,times(1)).changeState(Mockito.any(),Mockito.eq(MailStatus.FAILED));




    }

    @Test
    public void GivenMailSuccess_WhenRetryingSendingMail_ThenSendAndChangeState()
    {
        Mail mail = new Mail("anyMail@dummy.com","anyMail@dummy.com", "message", MailStatus.ATTEMPT);
        mailSenderFacade.retryMail(mail);
        verify(mailSender,times(1)).sendMail(Mockito.any());
        verify(mailLogger,times(1)).changeState(Mockito.any(),Mockito.eq(MailStatus.SENT));


    }

    @Test
    public void GivenMailFailedException_WhenRetryingSendingMail_ThenChangeStateToKilled()
    {
        Mail mail = new Mail("anyMail@dummy.com","anyMail@dummy.com", "message", MailStatus.ATTEMPT);
        Mockito.doThrow(MailFailedException.class).when(mailSender).sendMail(Mockito.any());
        mailSenderFacade.retryMail(mail);
        verify(mailSender,times(1)).sendMail(Mockito.any());
        verify(mailLogger,times(1)).changeState(Mockito.any(),Mockito.eq(MailStatus.KILLED));




    }


    }
