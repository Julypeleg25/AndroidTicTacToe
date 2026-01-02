package com.androidapp.tictactoe.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.tictactoe.R
import com.androidapp.tictactoe.board.Board
import com.androidapp.tictactoe.state.GameState
import com.androidapp.tictactoe.utils.GameConstants
import com.androidapp.tictactoe.utils.GameResult

class GameActivity : AppCompatActivity() {

    private lateinit var cells: Array<Array<Button>>
    private lateinit var statusText: TextView
    private lateinit var resetButton: Button

    private val gameState = GameState()
    private val board = Board(gameState)

    @SuppressLint("DiscouragedApi")
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_game)

        statusText = findViewById(R.id.statusText)
        statusText.text = getString(R.string.player_turn, GameConstants.PLAYER_X)
        resetButton = findViewById(R.id.resetButton)

        resetButton.visibility = View.GONE

        resetButton.setOnClickListener {
            statusText.text = getString(R.string.player_turn, GameConstants.PLAYER_X)
            board.reset()
            resetBoard()
            resetButton.visibility = View.GONE
            gameState.isFinished = false
        }

        cells = Array(GameConstants.BOARD_SIZE) { r ->
            Array(GameConstants.BOARD_SIZE) { c ->
                val id = resources.getIdentifier("cell_${r}${c}", "id", packageName)
                findViewById<Button>(id).apply {
                    setOnClickListener { handleClick(r, c, this) }
                }
            }
        }
    }

    private fun handleClick(row: Int, col: Int, button: Button) {
        if (gameState.isFinished) return

        val gameResult = board.play(row, col)
        button.text = gameState.grid[row][col].toString()

        when (gameResult) {
            is GameResult.Win -> {
                statusText.text = getString(R.string.player_won, gameResult.player)
                gameState.isFinished = true
                resetButton.visibility = View.VISIBLE
            }

            GameResult.Draw -> {
                statusText.text = getString(R.string.draw)
                gameState.isFinished = true
                resetButton.visibility = View.VISIBLE
            }

            GameResult.Continue -> {
                statusText.text = getString(R.string.player_turn, gameState.currentPlayer)
            }

            GameResult.None -> return
        }
    }

    private fun resetBoard() {
        for (r in cells.indices)
            for (c in cells[r].indices)
                cells[r][c].text = ""
    }

}