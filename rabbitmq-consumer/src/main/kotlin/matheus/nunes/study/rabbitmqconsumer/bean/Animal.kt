package matheus.nunes.study.rabbitmqconsumer.bean

data class Animal(
        val speed: Speed,
        val color: Color,
        val specie: Animals
)

enum class Color {
    BLACK, WHITE, ORANGE, YELLOW, GREEN
}

enum class Speed {
    QUICK, NORMAL, LAZY
}

enum class Animals {
    HORSE, TURTLE, MONKEY, BIRD, FOX
}