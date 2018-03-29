package model

import java.util.*

class Board(rowsAmount: Int, columnsAmount: Int) {
    // Игровое поле
    private var field = Array(rowsAmount, {Array(columnsAmount, {0})})

    // Проверим что позиция попадает на доску
    public fun checkPositionIsInBoard(position: Position ): Boolean {
        // строки
        if ( position.row < 0 || field.lastIndex < position.row )
            return false
        // столбцы
        if (position.column < 0 || field[0].lastIndex < position.column)
            return false

        // Проверки закончились
        return true
    }

    // Проерим что позиция не занята
    public fun checkPositionIsFree(nextPosition: Position ): Boolean {
        // проверим что поле еще не занято
        try {
            if (field[nextPosition.row][nextPosition.column] == 1)
                return false
        } catch ( e: IndexOutOfBoundsException ){
            return false
        }

        // Проверки закончились
        return true
    }

    // Проверка что на доске нет свободных ячеек.
    // Если свободных ячеек нет то победа.
    public fun noPlaceToMove(): Boolean{
        for ( row in field ) {
            for (cell in row) {
                if (cell == 0)
                    return false
            }
        }
        return true
    }

    // Помечаем ячейку использованной
    public fun fillPosition(position: Position){
        field[position.row][position.column] = 1
    }

    // Помечаем ячейку НЕиспользованной
    public fun freePosition(position: Position){
        field[position.row][position.column] = 0
    }


    override fun toString(): String {
        var result = StringBuilder()
        for(row in field) {
            for(cell in row) {
                result.append(cell).append(" ")
            }
            result.append("\n")
        }
        return result.toString()
    }


}