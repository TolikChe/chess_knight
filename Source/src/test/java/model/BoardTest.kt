package model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class BoardTest {

    @Test
    fun checkPositionIsInBoard_positive() {
        var testee = Board(5,5)
        //
        var position1 = Position(0,0)
        assertTrue(testee.checkPositionIsInBoard(position1))

        var position2 = Position(4,4)
        assertTrue(testee.checkPositionIsInBoard(position2))

        var position3 = Position(0,4)
        assertTrue(testee.checkPositionIsInBoard(position3))

        var position4 = Position(4,0)
        assertTrue(testee.checkPositionIsInBoard(position4))
    }

    @Test
    fun checkPositionIsInBoard_negative() {
        var testee = Board(5,5)
        //
        var position1 = Position(6,6)
        assertFalse(testee.checkPositionIsInBoard(position1))


        var position2 = Position(5,0)
        assertFalse(testee.checkPositionIsInBoard(position2))


        var position3 = Position(0,5)
        assertFalse(testee.checkPositionIsInBoard(position3))

        var position4 = Position(5,5)
        assertFalse(testee.checkPositionIsInBoard(position4))

    }

    @Test
    fun checkMoveIsPossible_positive() {
        var testee = Board(5,5)
        var position1 = Position(3,3)
        // testee.fillPosition(position)
        assertTrue(testee.checkPositionIsFree(position1))

        var position2 = Position(2,2)
        assertTrue(testee.checkPositionIsFree(position2))

        var position3 = Position(0,1)
        assertTrue(testee.checkPositionIsFree(position3))

        var position4 = Position(1,0)
        assertTrue(testee.checkPositionIsFree(position4))
    }

    @Test
    fun checkMoveIsPossible_negative() {
        var testee = Board(5,5)
        var position1 = Position(3,3)
        testee.fillPosition(position1)
        assertFalse(testee.checkPositionIsFree(position1))

        var position2 = Position(2,2)
        testee.fillPosition(position2)
        assertFalse(testee.checkPositionIsFree(position2))

        var position3 = Position(0,1)
        testee.fillPosition(position3)
        assertFalse(testee.checkPositionIsFree(position3))

        var position4 = Position(1,0)
        testee.fillPosition(position4)
        assertFalse(testee.checkPositionIsFree(position4))
    }

    @Test
    fun noPlaceToMove_positive() {
        var testee = Board(2,2)
        var position1 = Position(0,0)
        testee.fillPosition(position1)
        var position2 = Position(0,1)
        testee.fillPosition(position2)
        var position3 = Position(1,0)
        testee.fillPosition(position3)
        var position4 = Position(1,1)
        testee.fillPosition(position4)
        //
        assertTrue(testee.noPlaceToMove())
    }

    @Test
    fun noPlaceToMove_negative() {
        var testee = Board(2,2)
        var position1 = Position(0,0)
        testee.fillPosition(position1)
        var position2 = Position(0,1)
        testee.fillPosition(position2)
        //
        assertFalse(testee.noPlaceToMove())
    }


    @Test
    fun fillPosition() {
        var testee = Board(5,5)
        var position1 = Position(3,3)
        var position2 = Position(4,4)
        testee.fillPosition(position1)
        assertFalse(testee.checkPositionIsFree(position1))
        assertTrue(testee.checkPositionIsFree(position2))
    }

    @Test
    fun freePosition() {
        var testee = Board(5,5)
        var position1 = Position(3,3)
        testee.fillPosition(position1)
        assertFalse(testee.checkPositionIsFree(position1))
        testee.freePosition(position1)
        assertTrue(testee.checkPositionIsFree(position1))
    }

}