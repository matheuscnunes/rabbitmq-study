package matheus.nunes.study.rabbitmqpublisher.controller

import matheus.nunes.study.rabbitmqpublisher.bean.request.Log
import matheus.nunes.study.rabbitmqpublisher.bean.request.Message
import matheus.nunes.study.rabbitmqpublisher.service.RabbitPublisher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/log")
class LogController(
        private val rabbitPublisher: RabbitPublisher
) {

    @PostMapping("/publish")
    fun postMessage(@RequestBody log: Log): Log {
        rabbitPublisher.publish(log)
        return log
    }
}