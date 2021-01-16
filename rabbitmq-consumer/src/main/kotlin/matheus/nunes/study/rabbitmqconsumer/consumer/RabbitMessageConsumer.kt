package matheus.nunes.study.rabbitmqconsumer.consumer

import matheus.nunes.study.rabbitmqpublisher.bean.Message
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RabbitMessageConsumer(
        @Value("\${rabbitmq.message-queue:message}")
        private val queueName: String
) {

    companion object {
        val jacksonObjectMapper = jacksonObjectMapper()
    }

    @RabbitListener(
            queues = [RabbitConstants.MESSAGE_QUEUE],
            concurrency = "3",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveMessage(message: Message) =
        println("Received message: ${jacksonObjectMapper.writeValueAsString(message)}")
}