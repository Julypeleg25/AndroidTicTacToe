package com.androidapp.tictactoe.state

import com.androidapp.tictactoe.utils.GameConstants

data class GameState (
    var currentPlayer: Char = GameConstants.PLAYER_X,
    val grid: Array<CharArray> =
        Array(GameConstants.BOARD_SIZE) { CharArray(GameConstants.BOARD_SIZE) { GameConstants.EMPTY_CELL } },
)
