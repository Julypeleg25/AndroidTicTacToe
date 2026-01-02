package com.androidapp.tictactoe.board

import com.androidapp.tictactoe.utils.GameResult
import com.androidapp.tictactoe.state.GameState
import com.androidapp.tictactoe.utils.GameConstants

class Board(private val state: GameState) {

    fun play(row: Int, col: Int): GameResult {
        if (state.grid[row][col] != GameConstants.EMPTY_CELL) {
            return GameResult.None
        }

        state.grid[row][col] = state.currentPlayer
        checkWinner()?.let {
            return GameResult.Win(it)
        }

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

    private fun checkWinner(): Char? {
        val g = state.grid

        for (i in 0 until GameConstants.BOARD_SIZE) {
            if (g[i].all { it == g[i][0] && it != GameConstants.EMPTY_CELL }) return g[i][0]
            if ((0 until GameConstants.BOARD_SIZE).all { g[it][i] == g[0][i] && g[it][i] != GameConstants.EMPTY_CELL })
                return g[0][i]
        }

        if ((0 until GameConstants.BOARD_SIZE).all { g[it][it] == g[0][0] && g[it][it] != GameConstants.EMPTY_CELL })
            return g[0][0]

        if ((0 until GameConstants.BOARD_SIZE).all {
                g[it][GameConstants.BOARD_SIZE - 1 - it] == g[0][GameConstants.BOARD_SIZE - 1] &&
                        g[it][GameConstants.BOARD_SIZE - 1 - it] != GameConstants.EMPTY_CELL
            }) return g[0][GameConstants.BOARD_SIZE - 1]

        return null
    }

    fun reset() {
        for (r in 0 until GameConstants.BOARD_SIZE)
            for (c in 0 until GameConstants.BOARD_SIZE)
                state.grid[r][c] = GameConstants.EMPTY_CELL

        state.currentPlayer = GameConstants.PLAYER_X
    }
}
