package fawry.internship.notificationsystem.service;

import fawry.internship.notificationsystem.model.OrderEventModel;

public interface OrderEventHandler {

    void handle(OrderEventModel orderEvent);
}
