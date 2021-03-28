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
        val messagePersistenceQueueName: String
) {

    @Bean
    fun declarable(): Declarables {
        // Basic work queue, using default exchange
        val queue = Queue(RabbitConstants.DIRECT_MESSAGE_QUEUE)

        // Fanout Exchange (Publish/Subscriber)
        val fanoutExchange: FanoutExchange = ExchangeBuilder.fanoutExchange(RabbitConstants.FANOUT_EXCHANGE).build()
        val messagePersistenceFanoutQueue = Queue(messagePersistenceQueueName, false, true, true)
        val fanoutBinding = BindingBuilder.bind(messagePersistenceFanoutQueue).to(fanoutExchange)

        // Routing Exchange
        val directRoutingExchange: DirectExchange = ExchangeBuilder.directExchange(RabbitConstants.LOGS_EXCHANGE).build()
        val commonLogsQueue = Queue(RabbitConstants.DIRECT_COMMON_LOGS_QUEUE)
        val errorLogsQueue = Queue(RabbitConstants.DIRECT_ERROR_LOGS_QUEUE)
        val infoLogsBinding = BindingBuilder.bind(commonLogsQueue).to(directRoutingExchange).with("INFO")
        val warnLogsBinding = BindingBuilder.bind(commonLogsQueue).to(directRoutingExchange).with("WARN")
        val errorLogsBinding = BindingBuilder.bind(errorLogsQueue).to(directRoutingExchange).with("ERROR")

        return Declarables(
                queue,
                fanoutExchange,
                messagePersistenceFanoutQueue,
                fanoutBinding,
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
}