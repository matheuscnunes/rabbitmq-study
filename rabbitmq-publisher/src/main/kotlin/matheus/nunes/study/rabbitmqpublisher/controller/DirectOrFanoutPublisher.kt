package matheus.nunes.study.rabbitmqpublisher.controller

import matheus.nunes.study.rabbitmqpublisher.bean.request.Message
import matheus.nunes.study.rabbitmqpublisher.service.RabbitPublisher
import matheus.nunes.study.rabbitmqpublisher.util.logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
class DirectOrFanoutPublisher(
        private val rabbitPublisher: RabbitPublisher
) {
    companion object {
        val logger by logger()
    }

    @PostMapping("/publish")
    fun postMessage(@RequestBody message: Message): Message {
        logger.info("Received publish request for message $message")
        rabbitPublisher.publish(message)
        return message
    }
}