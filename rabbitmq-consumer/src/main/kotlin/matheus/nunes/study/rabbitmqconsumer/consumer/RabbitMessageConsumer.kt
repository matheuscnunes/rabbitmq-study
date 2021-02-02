package matheus.nunes.study.rabbitmqconsumer.consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import matheus.nunes.study.rabbitmqpublisher.bean.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import kotlin.random.Random

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
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveMessage(message: Message) =
            Random.nextInt(0, 10).run {
                if (this < 3) {
                    println("Internal error consuming")
                    throw Exception("INTERNAL ERROR CONSUMING")
                } else {
                    println("Received message: ${jacksonObjectMapper.writeValueAsString(message)}")
                }
            }
}