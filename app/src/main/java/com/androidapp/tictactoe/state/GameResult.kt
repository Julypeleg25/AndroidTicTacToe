package com.androidapp.tictactoe.state

sealed class GameResult {
    object Continue : GameResult()
    object Draw : GameResult()

    object Invalid : GameResult()


    data class Win(val player: Char) : GameResult()
}