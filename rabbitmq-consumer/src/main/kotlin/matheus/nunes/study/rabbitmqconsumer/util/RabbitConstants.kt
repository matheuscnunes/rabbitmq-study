package matheus.nunes.study.rabbitmqconsumer.util

class RabbitConstants {
    companion object {
        const val FANOUT_EXCHANGE: String = "message-persistence"
        const val LOGS_EXCHANGE: String = "logs-exchange"
        const val ANIMALS_EXCHANGE: String = "animal-exchange"

        const val DIRECT_MESSAGE_QUEUE: String = "direct-message-queue"
        const val DIRECT_COMMON_LOGS_QUEUE: String = "common-logs"
        const val DIRECT_ERROR_LOGS_QUEUE: String = "error-logs"
        const val ALL_HORSES_QUEUE: String = "horses"
        const val ALL_BLACK_ANIMALS_QUEUE: String = "quick-animals"
        const val QUICK_BLACK_TURTLES_QUEUE: String = "quick-black-turtles"

        const val INFO_ROUTING_KEY = "INFO"
        const val WARN_ROUTING_KEY = "WARN"
        const val ERROR_ROUTING_KEY = "ERROR"
        const val HORSES_ROUTING_KEY = "#.horse"
        const val BLACK_ANIMALS_ROUTING_KEY = "*.black.*"
        const val QUICK_BLACK_TURTLES_ROUTING_KEY = "quick.black.turtle"

        const val ERROR_HANDLER: String = "rabbitErrorHandler"
    }
}