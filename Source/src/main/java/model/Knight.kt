package model

class Knight {
    // Текуще положение
    lateinit var currentPosition: Position
    // Путь
    var path = mutableListOf<Position>()
    // Доска по которой будет ходить рыцарь
    lateinit var board: Board

    // Включение логирования
    var runLog: Boolean = false
    //
    // Инициализация свойств
    fun init(startPosition: Position, board: Board, enableRunLog: Boolean = false) {
        // Заадем текущую позицию
        this.currentPosition = startPosition;
        // Первый шаг в пути будет текущей позицией
        path.add(currentPosition)
        // Подготовленная для прогулок доска
        this.board = board
        // Занимаем на доске место
        board.fillPosition(startPosition);
        // Включение логирования
        this.runLog = enableRunLog
    }

    // Относительно текущей позиции рассчитываем места куда может попасть лошадь.
    internal fun constructPossiblePositions():MutableList<Position> {
        var result = mutableListOf<Position>()
        var postition: Position? = null

        // Строим все 8 вариантов с проверкой выпадания за границы доски
        // 1.
        postition = createNextPosition(currentPosition.row-1, currentPosition.column-2 )
        postition?.let { result.add(postition as Position) }
        // 2.
        postition = createNextPosition(currentPosition.row-2, currentPosition.column-1 )
        postition?.let { result.add(postition as Position) }
        // 3.
        postition = createNextPosition(currentPosition.row-2, currentPosition.column+1 )
        postition?.let { result.add(postition as Position) }
        // 4.
        postition = createNextPosition(currentPosition.row-1, currentPosition.column+2 )
        postition?.let { result.add(postition as Position) }
        // 5.
        postition = createNextPosition(currentPosition.row+1, currentPosition.column+2 )
        postition?.let { result.add(postition as Position) }
        // 6.
        postition = createNextPosition(currentPosition.row+2, currentPosition.column+1 )
        postition?.let { result.add(postition as Position) }
        // 7.
        postition = createNextPosition(currentPosition.row+2, currentPosition.column-1 )
        postition?.let { result.add(postition as Position) }
        // 8.
        postition = createNextPosition(currentPosition.row+1, currentPosition.column-2 )
        postition?.let { result.add(postition as Position) }

        return result
    }

    // Проверяем чтосгенерированный код попадает на доску
    private fun createNextPosition(row: Int, column: Int): Position? {
        val result = Position(row, column)
        // Проверяем что попадаем на доску
        if (board.checkPositionIsInBoard(result))
            return result
        else return null
    }

    // Делаем ход
    internal fun makeMove(position: Position): Boolean{
        // Проверим что ход возможен
        logMessage("Check before make move - $position")
        if (board.checkPositionIsFree(position)) {
            // Занимаем ячейку
            board.fillPosition(position)
            // Делаем текущей позицией новою ячейку
            currentPosition = position
            // Добавляем в маршрут новый шаг
            path.add(currentPosition)

            // Если включено логирование
            logMessage("Make move - $position")

            return true
        } else {
            // Если включено логирование
            logMessage("Make move failed - $position")

            return false
        }
    }

    // Отменяем ход
    internal fun cancelMove(position: Position): Boolean{
        // Проверим что ход был сделан и ячейка занята
        if (!board.checkPositionIsFree(position)) {
            // Освобождаем ячейку
            board.freePosition(position)
            // Делаем текущей позицией предыдущую по маршруту
            currentPosition = path[path.lastIndex-1]
            // Удаляем из маршрута последний шаг
            path.removeAt(path.lastIndex)

            // Если включено логирование
            logMessage("Cancel move - $position")

            return true
        } else {
            // Если включено логирование
            logMessage("Cancel move failed - $position")

            return false
        }
    }

    // Старт пробежки
    fun run(): EnumResult {
        logMessage("+++Start turn from - $currentPosition", true)

        // Гереним ходы
        for (newPosition in constructPossiblePositions()){
            // Пробуем пошагать
            if (makeMove(newPosition)){
                // Если шагнули и доска заполнена то это конец и пора выходить
                // Если доска не заполнена то идем в рекурсию
                if (board.noPlaceToMove()) {
                    logMessage("SUCCESS")
                    return EnumResult.SUCCESS
                }
                else {
                    // Если вышли из рекурсии с кодом -1 то значит начиная с этой позиции ничего нет.
                    // Позицию надо отменить и попробовать зайти с другой стороны.
                    //
                    // Если вернулись с кодом 0 значит все ок и последний ход отменять не надо.
                    if (run() == EnumResult.FAIL) cancelMove(newPosition) else return EnumResult.SUCCESS
                }
            }
        }

        logMessage("---Generation of new level steps failed. Return on previous step.")

        // Если пришли сюда то ни один из сгенерированных шагов не подошел и надо выходить на уровень выше
        return EnumResult.FAIL
    }

    fun showPath(){
        println("Knight path:")
        for (x in path.indices ){
            println("Step $x  = (${path[x].row}, ${path[x].column})")
        }
    }

    private fun logMessage(message: String, withBoard: Boolean = false){
        if (runLog) {
            println(message)
            if (withBoard) println(board)
        }
    }
}