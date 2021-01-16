package matheus.nunes.study.rabbitmqpublisher.controller

import matheus.nunes.study.rabbitmqpublisher.bean.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rabbit")
class RabbitMQController(
        private val rabbitTemplate: RabbitTemplate
) {

    @PostMapping("/publish")
    fun postMessage(@RequestBody message: Message): Message {
        rabbitTemplate.convertAndSend("","message", message)
        return message
    }
}