package matheus.nunes.study.rabbitmqpublisher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
class RabbitmqPublisherApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqPublisherApplication>(*args)
}
