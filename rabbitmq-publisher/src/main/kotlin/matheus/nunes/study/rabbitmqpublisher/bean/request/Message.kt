package matheus.nunes.study.rabbitmqpublisher.bean.request

import matheus.nunes.study.rabbitmqpublisher.bean.MessageDTO

data class Message(
        val text: String,
        val exchangeType: ExchangeType,
        val extraInfo: Map<String, Any>?
) {

    fun toMessageDTO(): MessageDTO =
            MessageDTO(text = this.text, extraInfo = this.extraInfo)
}