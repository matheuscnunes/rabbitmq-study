package matheus.nunes.study.rabbitmqpublisher.bean.request

import matheus.nunes.study.rabbitmqpublisher.bean.LogDTO


data class Log(
        val level: LogLevel,
        val message: String
) {

    fun toLogDTO(): LogDTO =
            LogDTO(message = this.message, level = this.level)
}