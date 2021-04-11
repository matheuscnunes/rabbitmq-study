package matheus.nunes.study.rabbitmqconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import matheus.nunes.study.rabbitmqconsumer.bean.Animal
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import matheus.nunes.study.rabbitmqconsumer.util.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RabbitAnimalsConsumer(
        @Value("\${rabbitmq.message-queue:message}")
        private val queueName: String,
        private val objectMapper: ObjectMapper
) : RabbitDelayedInconsistentConsumer() {

    companion object {
        val logger by logger()
    }

    @RabbitListener(
            queues = [RabbitConstants.ALL_HORSES_QUEUE],
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveHorse(horse: Animal) {
        logger.info("Received horse! Processing it...")
        processAnimal(horse)
    }

    @RabbitListener(
            queues = [RabbitConstants.ALL_BLACK_ANIMALS_QUEUE],
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveBlackAnimal(blackAnimal: Animal) {
        logger.info("Received black animal! Processing it...")
        processAnimal(blackAnimal)
    }

    @RabbitListener(
            queues = [RabbitConstants.ALL_BLACK_ANIMALS_QUEUE],
            concurrency = "2",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveQuickBlackTurtles(quickBlackTurtle: Animal) {
        logger.info("Received quick black turtle! Processing it...")
        processAnimal(quickBlackTurtle)
    }

    private fun processAnimal(animal: Animal) {
        checkError(2)
        logger.info("Processed message ${objectMapper.writeValueAsString(animal)}")
    }
}