package fawry.internship.notificationsystem.Controller;

import fawry.internship.notificationsystem.repository.MailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mails")

@RequiredArgsConstructor
public class MailController {
    private final MailRepo mailRepo;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        return new ResponseEntity<>(mailRepo.findAll(), HttpStatus.OK);
    }

}
