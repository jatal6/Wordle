package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import android.widget.EditText

import java.util.*

class MainActivity : AppCompatActivity() {
var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        val guessesField = findViewById<TextView>(R.id.guess_view)
        val editText = findViewById<EditText>(R.id.text_enter)
        val showAnswer = findViewById<TextView>(R.id.answer)
        val failView = findViewById<TextView>(R.id.info)
        val debugView = findViewById<TextView>(R.id.wrd_guess)


        val button = findViewById<Button>(R.id.button)
            var limit = 3
            var checkGuessDisplay = ""
            var userInputDisplay = ""

            // button logic
            button.setOnClickListener {
                // reset
                if (limit == 0){
                    finish()
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

                count++
                if (count >= 3) {
                    Toast.makeText(
                        it.context,
                        "You are out of tries!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (limit != 0) {

                    var isCorrect = false
                    val editTextString = editText.text.toString().uppercase(Locale.getDefault());
                    if (editTextString.length == 4) {
                        failView.text = ""
                        val result = checkGuess(editTextString, wordToGuess)
                        checkGuess(editTextString, wordToGuess)

                        if (checkGuessDisplay == "") {
                            checkGuessDisplay = result
                            userInputDisplay = editTextString
                        }
                        else {
                            checkGuessDisplay = checkGuessDisplay + "\n" + result
                            userInputDisplay = userInputDisplay + "\n" + editTextString
                        }

                        guessesField.text = checkGuessDisplay
                        debugView.text = userInputDisplay

                        if (result == "OOOO") {
                            failView.text = "Correct! \n: $wordToGuess"
                            button.text = "Reset"
                            isCorrect = true
                            limit = 0
                        } else {
                            limit--
                            if (limit == 0 && !isCorrect) {
                                failView.text = "There are no more attempts left\nAnswer: $wordToGuess"
                                button.text = "Reset"
                            }
                        }
                    }
                    else
                        failView.text = "It must be a four letter word"
                }
            }

        }
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    }



