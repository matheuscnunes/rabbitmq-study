package matheus.nunes.study.rabbitmqconsumer.consumer

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException
import org.springframework.stereotype.Component

@Component
class RabbitErrorHandler : RabbitListenerErrorHandler {

    override fun handleError(
            amqpMessage: Message?,
            message: org.springframework.messaging.Message<*>?,
            exception: ListenerExecutionFailedException?): Any {
            return "OK"
    }
}