package matheus.nunes.study.rabbitmqconsumer.bean

import java.time.Instant


data class Log(
        val level: LogLevel,
        val happenedAt: Instant? = Instant.now(),
        val message: String
)