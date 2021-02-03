package matheus.nunes.study.rabbitmqpublisher.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import matheus.nunes.study.rabbitmqpublisher.util.RabbitConstants
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration {

    @Bean
    fun declarable(): Declarables =
            Declarables(
                    Queue(RabbitConstants.DIRECT_MESSAGE_QUEUE),
                    ExchangeBuilder.fanoutExchange(RabbitConstants.EXCHANGE_FANOUT).build()
            )

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