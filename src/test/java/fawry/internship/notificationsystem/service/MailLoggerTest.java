package fawry.internship.notificationsystem.service;

import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;
import fawry.internship.notificationsystem.repository.MailRepo;
import fawry.internship.notificationsystem.service.impl.MailLoggerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MailLoggerTest {

    @Mock
    MailRepo mailRepo;
    MailLogger mailLogger;


    @BeforeEach
    void setUp()
    {
         mailLogger = new MailLoggerImpl(mailRepo);
    }

    @Test
    public void GivenValidId_WhenChangingState_ThenChangeStateSuccessfully()
    {
        Mail mail = new Mail("anyMail@dummy.com","anyMail@dummy.com", "message", MailStatus.ATTEMPT);

        Mockito.when(mailRepo.findById(Mockito.anyString())).thenReturn(Optional.of(mail));
        mailLogger.changeState("dummy",MailStatus.SENT);

        verify(mailRepo,times(1)).findById("dummy");
        assertThat(mail.getStatus()).isEqualTo(MailStatus.SENT);
        verify(mailRepo,times(1)).save(mail);

    }

    @Test
    public void GivenNewMail_WhenLoggingNewMail_ThenLogAndReturnMailWithGeneratedId()
    {
        Mail mail = new Mail("anyMail@dummy.com","anyMail@dummy.com", "message", MailStatus.ATTEMPT);

        Mail mailWithId = new Mail("dummyId","anyMail@dummy.com","anyMail@dummy.com", "message", MailStatus.ATTEMPT);

        Mockito.when(mailRepo.save(Mockito.any())).thenReturn(mailWithId);

        Mail loggedMail = mailLogger.log(mail);

        verify(mailRepo,times(1)).save(mail);
        assertThat(loggedMail).isEqualTo(mailWithId);


    }

}
