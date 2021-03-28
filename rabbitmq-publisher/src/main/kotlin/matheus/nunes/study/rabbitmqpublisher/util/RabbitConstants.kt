package matheus.nunes.study.rabbitmqpublisher.util

class RabbitConstants {
    companion object {
        const val DIRECT_MESSAGE_QUEUE: String = "direct-message-queue"
        const val FANOUT_EXCHANGE: String = "message-persistence"
        const val LOGS_EXCHANGE: String = "logs-exchange"
        const val DIRECT_COMMON_LOGS_QUEUE: String = "common-logs"
        const val DIRECT_ERROR_LOGS_QUEUE: String = "error-logs"
    }
}