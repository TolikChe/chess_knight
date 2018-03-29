import model.Board
import model.EnumResult
import model.Knight
import model.Position
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main(args: Array<String>) {

    do {

        println("Game with chess knight.\n\n")

        println("Enter size of board (rows,columns). Example: 5,5")
        val (rows, columns) = readLine()!!.split(',')

        println("\n\nEnter start position of knight (row,column). Example: 0,0")
        val (knightStartRow, knightStartColumn) = readLine()!!.split(',')

        val startTime = LocalDateTime.now()
        println("${startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))} : start")

        // Инициализация доски
        var board = Board(rows.toInt(), columns.toInt())

        // Инициализация фигуры
        var knight = Knight()
        knight.init(Position(knightStartRow.toInt(), knightStartColumn.toInt()), board)

        // Запуск коня в путь
        if (knight.run() == EnumResult.SUCCESS) {
            println("SUCCESS")
            knight.showPath()
        } else {
            println("Fail :(")
        }

        val stopTime = LocalDateTime.now()

        println("${stopTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))} : stop. Duration: ${ChronoUnit.MILLIS.between(startTime, stopTime)} ms")

        println("Try again? (y/n)")
        val again = readLine()
    } while (again.equals("y", true))

}