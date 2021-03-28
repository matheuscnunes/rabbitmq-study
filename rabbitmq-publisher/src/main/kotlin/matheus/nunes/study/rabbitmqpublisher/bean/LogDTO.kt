package matheus.nunes.study.rabbitmqpublisher.bean

import matheus.nunes.study.rabbitmqpublisher.bean.request.LogLevel
import java.time.Instant


data class LogDTO(
        val level: LogLevel,
        val happenedAt: Instant? = Instant.now(),
        val message: String
)