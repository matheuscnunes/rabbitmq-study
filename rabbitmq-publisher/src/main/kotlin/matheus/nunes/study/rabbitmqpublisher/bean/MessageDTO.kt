package matheus.nunes.study.rabbitmqpublisher.bean

import java.time.Instant

data class MessageDTO(
        val text: String,
        val createdAt: Instant = Instant.now(),
        val extraInfo: Map<String, Any>?
)