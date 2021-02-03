package matheus.nunes.study.rabbitmqconsumer.bean

import java.time.Instant


data class Message(
        val text: String,
        val createdAt: Instant? = Instant.now(),
        val extraInfo: Map<String, Any>?
)