package com.androidapp.tictactoe.utils

sealed class GameResult {
    object Continue : GameResult()
    object Draw : GameResult()

    object None : GameResult()

    data class Win(val player: Char) : GameResult()
}