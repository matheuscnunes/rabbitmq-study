package matheus.nunes.study.rabbitmqconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import matheus.nunes.study.rabbitmqconsumer.bean.Message
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class RabbitMessageConsumer(
        @Value("\${rabbitmq.message-queue:message}")
        private val queueName: String,
        private val objectMapper: ObjectMapper
) {

    @RabbitListener(
            queues = [RabbitConstants.DIRECT_MESSAGE_QUEUE],
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveMessage(message: Message) = processMessage(message)

    @RabbitListener(
            queues = ["#{rabbitConfiguration.controlQueueName}"],
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveFanOutMessage(message: Message) = processMessage(message)

    private fun processMessage(message: Message) {
        val randomErrorChance = Random.nextInt(0, 10)
        if (isError(randomErrorChance)) {
            throw Exception("INTERNAL ERROR CONSUMING")
        } else {
            println("Received message. Start processing it")
            delayExecutionByDotsNumber(message)
            println("Processed message ${objectMapper.writeValueAsString(message)}")
        }
    }

    private fun isError(randomErrorChance: Int) = randomErrorChance < 2

    private fun delayExecutionByDotsNumber(message: Message) {
        for (ch in message.text.toCharArray()) {
            if (ch == '.') Thread.sleep(1000)
        }
    }
}