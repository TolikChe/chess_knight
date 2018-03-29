package model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class KnightTest {

    @Test
    fun init() {
        // Given
        var testee = Knight()
        var board = Board(5,5)
        var position = Position(1,1)

        // When
        testee.init(position,board)

        // Then
        assertEquals( position, testee.currentPosition)
        assertEquals(1, testee.path.size)
        assertEquals( position, testee.path[0])
        assertFalse( testee.board.checkPositionIsFree(position))
        assertFalse(testee.runLog)
    }

    @Test
    fun init_default() {
        // Given
        var testee = Knight()
        var board = Board(5,5)
        var position = Position(1,1)

        // When
        testee.init(position,board, true)

        // Then
        assertEquals( position, testee.currentPosition)
        assertEquals(1, testee.path.size)
        assertEquals( position, testee.path[0])
        assertFalse( testee.board.checkPositionIsFree(position))
        assertTrue(testee.runLog)
    }

    @Test
    fun makeMove(){
        // Given
        var testee = Knight()
        var board = Board(5,5)
        var position = Position(0,0)
        testee.init(position,board)
        var nextPosition = Position(1,1)

        // When
        testee.makeMove(nextPosition)

        //Then
        assertEquals(nextPosition, testee.currentPosition)
        assertEquals(2, testee.path.size)
        assertEquals(position, testee.path[0])
        assertEquals(nextPosition, testee.path[testee.path.lastIndex])
        assertFalse(testee.board.checkPositionIsFree(position))
        assertFalse(testee.board.checkPositionIsFree(nextPosition))
    }


    @Test
    fun cancelMove() {
        // Given
        var testee = Knight()
        var board = Board(5, 5)
        var position = Position(0, 0)
        testee.init(position, board)
        // first step
        var nextPosition = Position(1, 1)
        testee.makeMove(nextPosition)

        // When
        testee.cancelMove(nextPosition)

        //Then
        assertEquals(position, testee.currentPosition)
        assertEquals(1, testee.path.size)
        assertEquals(position, testee.path[0])
        assertEquals(position, testee.path[testee.path.lastIndex])
        assertFalse(testee.board.checkPositionIsFree(position))
        assertTrue(testee.board.checkPositionIsFree(nextPosition))
    }


    @Test
    fun constructPossiblePosition_full(){
        // Given
        var testee = Knight()
        var board = Board(5,5)
        var position = Position(2,2)
        testee.init(position,board)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(8, result.size)
        // 1
        assertEquals(1, result[0].row)
        assertEquals(0, result[0].column)
        // 2
        assertEquals(0, result[1].row)
        assertEquals(1, result[1].column)
        // 3
        assertEquals(0, result[2].row)
        assertEquals(3, result[2].column)
        // 4
        assertEquals(1, result[3].row)
        assertEquals(4, result[3].column)
        // 5
        assertEquals(3, result[4].row)
        assertEquals(4, result[4].column)
        // 6
        assertEquals(4, result[5].row)
        assertEquals(3, result[5].column)
        // 7
        assertEquals(4, result[6].row)
        assertEquals(1, result[6].column)
        // 8
        assertEquals(3, result[7].row)
        assertEquals(0, result[7].column)
    }

    @Test
    fun constructPossiblePosition_2(){
        // Given
        var testee = Knight()
        var board = Board(5,5)
        var position = Position(0,0)
        testee.init(position,board)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(2, result.size)
        // 1
        assertEquals(1, result[0].row)
        assertEquals(2, result[0].column)
        // 2
        assertEquals(2, result[1].row)
        assertEquals(1, result[1].column)
    }

    @Test
    fun constructPossiblePosition_41(){
        // Given
        var testee = Knight()
        var board = Board(5,5)
        var position = Position(2,0)
        testee.init(position,board)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(4, result.size)
        // 1
        assertEquals(0, result[0].row)
        assertEquals(1, result[0].column)
        // 2
        assertEquals(1, result[1].row)
        assertEquals(2, result[1].column)
        // 3
        assertEquals(3, result[2].row)
        assertEquals(2, result[2].column)
        // 4
        assertEquals(4, result[3].row)
        assertEquals(1, result[3].column)
    }

    @Test
    fun constructPossiblePosition_31(){
        // Given
        var testee = Knight()
        var board = Board(4,4)
        var position = Position(2,0)
        testee.init(position,board)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(3, result.size)
        // 1
        assertEquals(0, result[0].row)
        assertEquals(1, result[0].column)
        // 2
        assertEquals(1, result[1].row)
        assertEquals(2, result[1].column)
        // 3
        assertEquals(3, result[2].row)
        assertEquals(2, result[2].column)
    }


    @Test
    fun constructPossiblePosition_32(){
        // Given
        var testee = Knight()
        var board = Board(4,4)
        var position = Position(1,0)
        testee.init(position,board)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(3, result.size)
        // 1
        assertEquals(0, result[0].row)
        assertEquals(2, result[0].column)
        // 2
        assertEquals(2, result[1].row)
        assertEquals(2, result[1].column)
        // 3
        assertEquals(3, result[2].row)
        assertEquals(1, result[2].column)
    }

    @Test
    fun constructPossiblePosition_negative(){
        // Given
        var testee = Knight()
        var board = Board(2,2)
        var position = Position(0,0)
        testee.init(position,board)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(0, result.size)
    }

    @Test
    fun constructPossiblePosition_21(){
        // Given
        var testee = Knight()
        var board = Board(3,3)
        var position = Position(0,0)
        testee.init(position,board, true)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(2, result.size)
        // 1
        assertEquals(1, result[0].row)
        assertEquals(2, result[0].column)
        // 2
        assertEquals(2, result[1].row)
        assertEquals(1, result[1].column)
    }

    @Test
    fun constructPossiblePosition_22(){
        // Given
        var testee = Knight()
        var board = Board(3,3)
        var position = Position(1,2)
        testee.init(position,board, true)

        // When
        var result = testee.constructPossiblePositions()

        // Then
        assertEquals(2, result.size)
        // 1
        assertEquals(0, result[0].row)
        assertEquals(0, result[0].column)
        // 2
        assertEquals(2, result[1].row)
        assertEquals(0, result[1].column)
    }

    @Test
    fun small_run() {
        // Given
        var testee = Knight()
        var board = Board(2,2)
        var position = Position(0,0)
        testee.init(position,board, true)

        // When
        var result = testee.run()

        // Then
        assertEquals(EnumResult.FAIL, result)
    }


    @Test
    fun seven_step_fail_run() {
        // Given
        var testee = Knight()
        var board = Board(3,3)
        var position = Position(0,0)
        testee.init(position,board, true)

        // When
        var result = testee.run()

        // Then
        assertEquals(EnumResult.FAIL, result)
    }


    @Test
    fun showPath() {
        // Given
        var testee = Knight()
        var board = Board(3,3)
        var position1 = Position(0,0)
        var position2= Position(1,1)
        testee.init(position1,board, true)
        testee.makeMove(position2)

        //Then
        testee.showPath()
    }


    @Test
    fun test_run_without_fails_1() {
        // Given
        var testee = Knight()
        var board = Board(3,3)
        var position1 = Position(0,0)
        testee.init(position1,board)
        //Then
        if (testee.run()==EnumResult.SUCCESS) testee.showPath()
    }

    @Test
    fun test_run_without_fails_2() {
        // Given
        var testee = Knight()
        var board = Board(6,6)
        var position1 = Position(0,0)
        testee.init(position1,board, true)
        //Then
        if (testee.run()==EnumResult.SUCCESS) testee.showPath()
    }

}