package matheus.nunes.study.rabbitmqconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import matheus.nunes.study.rabbitmqconsumer.bean.Log
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RabbitLogConsumer(
        @Value("\${rabbitmq.message-queue:message}")
        private val queueName: String,
        private val objectMapper: ObjectMapper
) : RabbitDelayedInconsistentConsumer() {

    @RabbitListener(
            queues = [RabbitConstants.DIRECT_COMMON_LOGS_QUEUE],
            concurrency = "1",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveCommonLogMessage(log: Log) = processLog(log)

    @RabbitListener(
            queues = [RabbitConstants.DIRECT_ERROR_LOGS_QUEUE],
            concurrency = "1",
            errorHandler = RabbitConstants.ERROR_HANDLER
    )
    fun receiveErrorLogsMessage(log: Log) = processLog(log)

    private fun processLog(log: Log) {
        checkError(1)
        println("Received ${log.info} log. Start processing it")
        delayExecutionByDotsNumber(log.message)
        println("Processed log ${objectMapper.writeValueAsString(log)}")
    }
}