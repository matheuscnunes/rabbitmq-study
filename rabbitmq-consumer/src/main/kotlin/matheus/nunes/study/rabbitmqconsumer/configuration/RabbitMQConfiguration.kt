package matheus.nunes.study.rabbitmqconsumer.configuration

import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfiguration {

//    @Bean
//    fun queue(@Value("spring.rabbitmq.queue-name:message.queue") queueName: String): Queue =
//            Queue(queueName, false)
//
//    @Bean
//    fun exchange(@Value("spring.rabbitmq.exchange:message.exchange") exchangeName: String): TopicExchange =
//            TopicExchange(exchangeName)
//
//    @Bean
//    fun binding(queue: Queue, exchange: TopicExchange): Binding =
//            BindingBuilder.bind(queue).to(exchange).with("message")
//
//    @Bean
//    fun listenerAdapter(receiver: MessageReceiver): MessageListenerAdapter =
//            MessageListenerAdapter(receiver, "")
//
//    @Bean
//    fun container(connectionFactory: ConnectionFactory): SimpleMessageListenerContainer {
//        val container = SimpleMessageListenerContainer()
//        container.connectionFactory
//
//    }

}