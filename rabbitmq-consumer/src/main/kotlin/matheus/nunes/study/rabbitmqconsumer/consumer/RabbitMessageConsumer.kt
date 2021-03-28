package matheus.nunes.study.rabbitmqconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import matheus.nunes.study.rabbitmqconsumer.bean.Message
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import matheus.nunes.study.rabbitmqconsumer.util.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RabbitMessageConsumer(
        @Value("\${rabbitmq.message-queue:message}")
        private val queueName: String,
        private val objectMapper: ObjectMapper
) : RabbitDelayedInconsistentConsumer() {

    companion object {
        val logger by logger()
    }

    @RabbitListener(
            queues = [RabbitConstants.DIRECT_MESSAGE_QUEUE],
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveMessage(message: Message) = processMessage(message)

    @RabbitListener(
            queues = ["#{rabbitConfiguration.messagePersistenceQueueName}"],
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveFanOutMessage(message: Message) = processMessage(message)

    private fun processMessage(message: Message) {
        checkError(2)

        logger.info("Received message. Start processing it")
        delayExecutionByDotsNumber(message.text)
        logger.info("Processed message ${objectMapper.writeValueAsString(message)}")
    }
}