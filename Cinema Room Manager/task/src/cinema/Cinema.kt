@file:Suppress("UNUSED_EXPRESSION")

package cinema


var countCinemaSeat = mutableListOf<String>()
var row: Int = 0
var seat: Int = 0
var ticket: Int = 0
var firstSeat = 0
var backSeat = 0
var countSeat: Int = 0
var createRow: Int = 0
val roomCinemaRowSeat: MutableList<MutableList<String>> = mutableListOf()
var listCurrentIncome = mutableListOf<Int>()
var currentIncome = 0
var totalIncome = 0
var userR = 0
var userS = 0
var totalSeat: Int = 0

fun main() {

    println("Enter the number of rows:")
    row = readln().toInt()
    println("Enter the number of seats in each row:")
    seat = readln().toInt()

    totalSeat = row * seat

    if (totalSeat <= 60){
        firstSeat = row
    }
    if (totalSeat > 60){
        when {
            row % 2 == 0 -> {
                firstSeat = row / 2
                backSeat = row / 2
            }
            else -> {
                firstSeat = (row - 1) / 2
                backSeat =  (row - firstSeat)

            }
        }
    }

    greatRoomCinema()
    userMenuCinemaManager()

}

fun userMenuCinemaManager() {
    println("\n1. Show the seats\n"+
            "2. Buy a ticket\n"+
            "3. Statistics\n"+
            "0. Exit")
    val userInput = readln().toInt()

    if (userInput == 1) {
        showRoom()
    }
    if (userInput == 2) {
        userChoseSeat()
    }
    if (userInput == 3) {
        staticsRoomCinema()
    }
    if (userInput == 0) {
        println()
    }
    else {
        userMenuCinemaManager()
    }
}

fun greatRoomCinema() {
    for (r in 0 until row) {
        roomCinemaRowSeat.add(mutableListOf())
    }
    repeat(row) {
        repeat(seat) {
            roomCinemaRowSeat[createRow] += "S"
        }
        createRow++
    }
}

fun showRoom() {
    for (i in 0..seat) {
        if (countCinemaSeat.size -1 == seat) break
        countCinemaSeat.add(countSeat.toString())
        countSeat++
        if (countCinemaSeat[0] == "0")
            countCinemaSeat[0] = " "
    }
    println("Cinema:")
    print("${countCinemaSeat.toString().replace(",", "").replace("[", "").replace("]", "")}\n")
    for(r in roomCinemaRowSeat.indices) {

        println("${r +1} ${roomCinemaRowSeat[r].toString().replace(",", "").replace("[", "").replace("]", "")}")

    }

}

fun userChoseSeat() {
    totalSeat = row * seat

    println("\nEnter a row number:")
    userR = readln()!!.toInt()
    println("Enter a seat number in that row:")
    userS = readln()!!.toInt()

    if ((userR > row && userS > seat) || (userR > row || userS > seat)) {
        println("\nWrong input!")
        userChoseSeat()
    }
    if (roomCinemaRowSeat[userR -1][userS -1] == "B") {
        println("\nThat ticket has already been purchased!")
        userChoseSeat()
    }
    if (roomCinemaRowSeat[userR -1][userS -1] == "S") {
        roomCinemaRowSeat[userR -1][userS -1] = "B"
    }

    when {
        userR <= firstSeat && backSeat == 0 -> {
            ticket = 10
        }
        userR <= firstSeat && userR < backSeat-> {
            ticket = 10
        }
        userR <= firstSeat && firstSeat == backSeat-> {
            ticket = 10
        }
        userR >= backSeat && userR > firstSeat-> {
            ticket = 8
        }

    }

    listCurrentIncome.add(ticket)
    println("\nTicket price: $$ticket" )
    userMenuCinemaManager()
}

fun staticsRoomCinema() {
    totalSeat = row * seat
    var countTicket = 0

    totalIncome = 0

    val firstSeatTotalIncome = (firstSeat * seat) * 10
    val backSeatTotalIncome = (backSeat * seat) * 8
    totalIncome = firstSeatTotalIncome + backSeatTotalIncome

    println(totalSeat)
    println(totalIncome)
    println(firstSeat)
    println(backSeat)
    currentIncome = 0
    for (i in listCurrentIncome.indices) {
        currentIncome += listCurrentIncome[i]
        countTicket++
    }

    val findPercentage = (countTicket.toDouble() / totalSeat) * 100.00
    val percentage: Float = findPercentage.toFloat()
    val formatPercentage = "%.2f".format(percentage)

    println("\nNumber of purchased tickets: $countTicket")
    println("Percentage: $formatPercentage%") // 0.00
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}