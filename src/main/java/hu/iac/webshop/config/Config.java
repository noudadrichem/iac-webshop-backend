package hu.iac.webshop.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@EnableJms
@Configuration
public class Config {

    @Bean
    public Queue orderQueue() {
        return new ActiveMQQueue("order.queue");
    }

    @Bean
    public Queue customerQueue() {
        return new ActiveMQQueue("customer.queue");
    }
}
