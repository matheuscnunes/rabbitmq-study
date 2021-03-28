package matheus.nunes.study.rabbitmqpublisher.service

import matheus.nunes.study.rabbitmqpublisher.bean.request.ExchangeType
import matheus.nunes.study.rabbitmqpublisher.bean.request.Log
import matheus.nunes.study.rabbitmqpublisher.bean.request.Message
import matheus.nunes.study.rabbitmqpublisher.util.RabbitConstants
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitPublisher(
        private val rabbitTemplate: RabbitTemplate
) {

    fun publish(message: Message) {
        val messageDTO = message.toMessageDTO()
        when (message.exchangeType) {
            ExchangeType.DIRECT -> rabbitTemplate.convertAndSend("", RabbitConstants.DIRECT_MESSAGE_QUEUE,
                    messageDTO)
            ExchangeType.FANOUT -> rabbitTemplate.convertAndSend(RabbitConstants.FANOUT_EXCHANGE, "",
                    messageDTO)
        }
    }

    fun publish(log: Log) {
        val logDTO = log.toLogDTO()
        rabbitTemplate.convertAndSend(RabbitConstants.LOGS_EXCHANGE, logDTO.level.name, logDTO)
    }
}