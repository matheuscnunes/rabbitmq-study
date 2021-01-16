package matheus.nunes.study.rabbitmqpublisher.configuration

import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfiguration {

    @Bean
    fun declarable(): Declarables =
            Declarables(Queue(RabbitConstants.MESSAGE_QUEUE))
}