import kotlin.random.Random

fun main() {
    val cities = listOf(
        "Бийск", "Барнаул", "Новосибирск", "Красноярск", "Томск",
        "Кемерово", "Иркутск", "Чита", "Улан-Удэ", "Абакан",
        "Братск", "Ачинск", "Минусинск", "Саянск", "Норильск"
    )

    while (true) {
        println("Хотите закончить работу? Введите 'EXIT' для выхода или 'GO' для продолжения.")
        val command = readLine() ?: continue
        if (command.equals("EXIT", ignoreCase = true)) break

        val direction = createDirection(cities)
        println("Направление создано: $direction")

        val passengers = sellTickets()
        println("Количество проданных билетов: $passengers")

        val train = formTrain(passengers)
        println("Поезд на направлении '$direction' состоит из ${train.size} вагонов и отправлен.")

        // Вывод информации о вагонах
        for ((index, wagon) in train.withIndex()) {
            val wagonCapacity = wagon.first // Вместимость вагона
            val passengersInWagon = wagon.second // Количество пассажиров в вагоне
            val freeSeats = wagonCapacity - passengersInWagon // Свободные места
            println("Вагон ${index + 1}: Вместимость = $wagonCapacity, Пассажиров = $passengersInWagon, Свободных мест = $freeSeats")
        }
    }
}

fun createDirection(cities: List<String>): String {
    val startCity = cities.random()
    val endCity = cities.filter { it != startCity }.random()
    return "$startCity - $endCity"
}

fun sellTickets(): Int {
    return Random.nextInt(5, 202) // Количество пассажиров от 5 до 201
}

fun formTrain(passengers: Int): List<Pair<Int, Int>> {
    val train = mutableListOf<Pair<Int, Int>>() // Список пар (вместимость вагона, количество пассажиров)
    var remainingPassengers = passengers

    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(5, 26) // Вместимость вагона от 5 до 25
        val passengersInWagon = if (remainingPassengers >= wagonCapacity) wagonCapacity else remainingPassengers
        train.add(Pair(wagonCapacity, passengersInWagon))
        remainingPassengers -= passengersInWagon
    }

    return train
}
