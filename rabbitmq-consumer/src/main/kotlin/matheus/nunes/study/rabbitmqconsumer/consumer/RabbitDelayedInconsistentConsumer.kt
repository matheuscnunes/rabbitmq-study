package matheus.nunes.study.rabbitmqconsumer.consumer

import kotlin.random.Random

abstract class RabbitDelayedInconsistentConsumer {

    fun delayExecutionByDotsNumber(text: String) {
        for (ch in text.toCharArray()) {
            if (ch == '.') Thread.sleep(1000)
        }
    }

    fun checkError(chanceInTen: Int) {
        if (isError(chanceInTen)) {
            throw Exception("INTERNAL ERROR CONSUMING LOG")
        }
    }

    private fun isError(chanceInTen: Int) = Random.nextInt(0, 10) < chanceInTen
}