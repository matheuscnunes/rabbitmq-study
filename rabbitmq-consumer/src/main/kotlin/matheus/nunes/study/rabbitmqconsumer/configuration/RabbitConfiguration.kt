package matheus.nunes.study.rabbitmqconsumer.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import matheus.nunes.study.rabbitmqconsumer.util.RabbitConstants
import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration(
        @Value("\${rabbit.message-persistence.queue-prefix}-\${random.uuid}")
        val controlQueueName: String
) {

    @Bean
    fun declarable(): Declarables {
        val fanoutExchange: FanoutExchange = ExchangeBuilder.fanoutExchange(RabbitConstants.EXCHANGE_FANOUT).build()
        val messagePersistenceQueue = Queue(controlQueueName, false, true, true)

        return Declarables(
                Queue(RabbitConstants.DIRECT_MESSAGE_QUEUE),
                fanoutExchange,
                messagePersistenceQueue,
                BindingBuilder
                        .bind(messagePersistenceQueue)
                        .to(fanoutExchange)
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
}