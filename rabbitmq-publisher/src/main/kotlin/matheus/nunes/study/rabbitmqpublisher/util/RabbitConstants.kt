package matheus.nunes.study.rabbitmqpublisher.util

class RabbitConstants {
    companion object {
        const val DIRECT_MESSAGE_QUEUE: String = "direct-message-queue"
        const val EXCHANGE_FANOUT: String = "message-persistence"
    }
}