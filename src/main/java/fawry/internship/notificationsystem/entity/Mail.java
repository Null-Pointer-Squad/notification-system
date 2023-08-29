package fawry.internship.notificationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    @Id
    String id;
    private String from;
    private String to;
    private String message;
    private MailStatus status;

    public Mail(String from, String to, String message, MailStatus status) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.status = status;
    }
}
