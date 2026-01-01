package com.androidapp.tictactoe.board

import com.androidapp.tictactoe.state.GameResult
import com.androidapp.tictactoe.state.GameState
import com.androidapp.tictactoe.utils.GameConstants

class Board(private val state: GameState) {

    fun play(row: Int, col: Int): GameResult {
        if (state.isFinished || state.grid[row][col] != GameConstants.EMPTY_CELL) {
            return GameResult.Invalid
        }

        state.grid[row][col] = state.currentPlayer

       //TODO: check result here

        if (false) { //TODO: CHECK IS BOARD FULL
            state.isFinished = true
            return GameResult.Draw
        }

         //TODO: next player turn
        return GameResult.Continue
    }

    //TODO: play again and check winner
}
