package matheus.nunes.study.rabbitmqpublisher.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import matheus.nunes.study.rabbitmqpublisher.util.RabbitConstants
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration {

    @Bean
    fun declarable(): Declarables {
        // basic work queue
        val queue = Queue(RabbitConstants.DIRECT_MESSAGE_QUEUE)

        // Fanout Exchange (Publish/Subscriber)
        val fanoutExchange = ExchangeBuilder.fanoutExchange(RabbitConstants.FANOUT_EXCHANGE).build<Exchange>()

        // Routing Exchange - all logs and error logs specific queues
        val directRoutingExchange: DirectExchange = ExchangeBuilder.directExchange(RabbitConstants.LOGS_EXCHANGE).build()
        val commonLogsQueue = Queue(RabbitConstants.DIRECT_COMMON_LOGS_QUEUE)
        val errorLogsQueue = Queue(RabbitConstants.DIRECT_ERROR_LOGS_QUEUE)
        val infoLogsBinding = BindingBuilder.bind(commonLogsQueue).to(directRoutingExchange).with("INFO")
        val warnLogsBinding = BindingBuilder.bind(commonLogsQueue).to(directRoutingExchange).with("WARN")
        val errorLogsBinding = BindingBuilder.bind(errorLogsQueue).to(directRoutingExchange).with("ERROR")

        return Declarables(
                queue,
                fanoutExchange,
                directRoutingExchange,
                commonLogsQueue,
                errorLogsQueue,
                infoLogsBinding,
                warnLogsBinding,
                errorLogsBinding
        )
    }

    @Bean
    fun jackson2MessageConverter(objectMapper: ObjectMapper): Jackson2JsonMessageConverter =
            Jackson2JsonMessageConverter(objectMapper)

    @Bean
    fun objectMapper(): ObjectMapper =
            ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                    .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                    .registerModule(JavaTimeModule())
                    .registerKotlinModule()

    @Bean
    fun rabbitTemplate(
            connectionFactory: ConnectionFactory,
            messageConverter: Jackson2JsonMessageConverter
    ): RabbitTemplate =
            RabbitTemplate(connectionFactory).also {
                it.messageConverter = messageConverter
            }
}