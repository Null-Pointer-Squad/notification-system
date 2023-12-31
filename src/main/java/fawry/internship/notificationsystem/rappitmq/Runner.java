package fawry.internship.notificationsystem.rappitmq;


import fawry.internship.notificationsystem.model.OrderEventModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;


    public Runner( RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Config.topicExchangeName, "order.created.event", new OrderEventModel(2,"abdoo","kimoo"));
    }

}
