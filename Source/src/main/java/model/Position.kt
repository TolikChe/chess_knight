package model

class Position( var row: Int, var column: Int) {
    override fun toString(): String {
        return "Position(row=$row, column=$column)"
    }
}