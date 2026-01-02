package com.androidapp.tictactoe.board

import com.androidapp.tictactoe.state.GameResult
import com.androidapp.tictactoe.state.GameState
import com.androidapp.tictactoe.utils.GameConstants

class Board(private val state: GameState) {

    fun play(row: Int, col: Int): GameResult {
        if (state.grid[row][col] != GameConstants.EMPTY_CELL) {
            return GameResult.None
        }

        state.grid[row][col] = state.currentPlayer

       //TODO: check result here

        if (isBoardFull()) {
            return GameResult.Draw
        }

        nextTurn();
        return GameResult.Continue
    }

    private fun isBoardFull(): Boolean =
        state.grid.all { row -> row.all { it != GameConstants.EMPTY_CELL } }

    private fun nextTurn() {
        val nextPlayer =  if (state.currentPlayer == GameConstants.PLAYER_X)
            GameConstants.PLAYER_O else GameConstants.PLAYER_X
        state.currentPlayer = nextPlayer
    }

    //TODO: play again and check winner
}
