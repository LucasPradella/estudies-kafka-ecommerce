package  br.com.pradella.ecommerce;

import br.com.pradella.ecommerce.model.Order;


import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var orderDispatcher = new KafkaDispatcher<Order>()) {
            try (var emailDispatcher = new KafkaDispatcher<String>()) {
                String email = Math.random() + "email.com.br";
                for (var i = 0; i < 10; i++) {
                    var orderId = UUID.randomUUID().toString();
                    var amount = new BigDecimal(Math.random() * 5000 + 1);

                    var order = new Order(email, orderId, amount );
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", email, order);

                    var emailMessage = "Thank you for your order! We are processing your order!";
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL", email, emailMessage);
                }
            }
        }
    }

}