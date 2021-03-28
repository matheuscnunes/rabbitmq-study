package matheus.nunes.study.rabbitmqconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import matheus.nunes.study.rabbitmqconsumer.bean.Log
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import matheus.nunes.study.rabbitmqconsumer.util.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitLogConsumer(
        private val objectMapper: ObjectMapper
) : RabbitDelayedInconsistentConsumer() {

    companion object {
        val logger by logger()
    }

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

    private fun processLog(logRecord: Log) {
        checkError(1)
        logger.info("Received ${logRecord.level.name} log. Start processing it")
        delayExecutionByDotsNumber(logRecord.message)
        logger.info("Processed log ${objectMapper.writeValueAsString(logRecord)}")
    }
}