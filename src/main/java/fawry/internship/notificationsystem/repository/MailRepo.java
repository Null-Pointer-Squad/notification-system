package fawry.internship.notificationsystem.repository;

import fawry.internship.notificationsystem.entity.Mail;
import fawry.internship.notificationsystem.entity.MailStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MailRepo extends MongoRepository<Mail,String> {

     Page<Mail> findAllByStatus(MailStatus mailStatus, Pageable pageable);
}
