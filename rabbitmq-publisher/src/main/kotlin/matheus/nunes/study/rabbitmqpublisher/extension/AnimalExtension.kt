package matheus.nunes.study.rabbitmqpublisher.extension

import matheus.nunes.study.rabbitmqpublisher.bean.request.Animal

fun Animal.toRoutingKey(): String =
        this.speed.name.toLowerCase() + this.color.name.toLowerCase() + this.specie.name.toLowerCase()