package matheus.nunes.study.rabbitmqconsumer.util

class RabbitConstants {
    companion object {
        const val DIRECT_MESSAGE_QUEUE: String = "direct-message-queue"
        const val EXCHANGE_FANOUT: String = "message-persistence"
        const val FANOUT_QUEUE_PREFIX: String = "message-persistence-"
        const val ERROR_HANDLER: String = "rabbitErrorHandler"
    }
}