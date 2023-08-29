package fawry.internship.notificationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventModel {
    private Integer orderId;
    private String customerMail;
    private String merchantMail;

}


