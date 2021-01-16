package matheus.nunes.study.rabbitmqpublisher.bean

import java.time.Instant


data class Message(
        val text: String,
        val createdAt: Instant? = Instant.now(),
        val extraInfo: Map<String, Any>?
) : java.io.Serializable