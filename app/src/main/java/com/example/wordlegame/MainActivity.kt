package com.example.wordlegame

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()
        val sportsToGuess = FourLetterWordList.FourLetterSportsList.getRandomFourLetterWord()
        fun checkGuess(enterword: String) : String {
            var result = ""
            for (i in 0..3) {
                if (enterword[i] == wordToGuess[i]) {
                    result += "O"
                }
                else if (enterword[i] in wordToGuess) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            return result
        }
        fun hideKeyboard() {
            val view = this.currentFocus
            if (view != null){
                val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                hide.hideSoftInputFromWindow(view.windowToken,0)
            }
            else{
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            }
        }

       // enter button
        val button = findViewById<Button>(R.id.button)
        //reset button
        val button2 = findViewById<Button>(R.id.button2)

        //using edit text for user to input word
       // var enterword = findViewById<EditText>(R.id.enterWord)

        //first word
        var word1 = findViewById<TextView>(R.id.word1text)
        //check first word
        var word1Guess = findViewById<TextView>(R.id.word1check)

        //second word
        var word2= findViewById<TextView>(R.id.word2text)
        //check second word
        var word2Guess = findViewById<TextView>(R.id.word2check)

        //third word
        var word3= findViewById<TextView>(R.id.word3text)
        //check second word
        var word3Guess = findViewById<TextView>(R.id.word3check)

        var guessList= mutableListOf<String>()


        var answer = findViewById<TextView>(R.id.guesshere)
        answer.text= wordToGuess


        button.setOnClickListener {
           var enterword = findViewById<EditText>(R.id.enterWord)
            var word = enterword.getText().toString().uppercase()
            //Toast.makeText(this, word, Toast.LENGTH_SHORT).show()


           // if(word == ""){
            //    Toast.makeText(this, "please enter something", Toast.LENGTH_SHORT).show()
           // }

            if(word.length != 4){
                Toast.makeText(this, "word needs to be 4 characters", Toast.LENGTH_SHORT).show()

            }
            if(word.all{ it.isLetter()}){

                guessList += word


            }
            else {
                Toast.makeText(this, "word should only be letters", Toast.LENGTH_SHORT).show()

            }

            if (guessList.size==1){
                word1.text= guessList[0]
                word1Guess.text = checkGuess(guessList[0])

            }
            if (guessList.size==2){
                word2.text= guessList[1]
                word2Guess.text = checkGuess(guessList[1])

            }
            if (guessList.size==3){
                word3.text= guessList[2]
                word3Guess.text = checkGuess(guessList[2])
                button.visibility = View.INVISIBLE
                button2.visibility = View.VISIBLE
                answer.visibility = View.VISIBLE


            }


            hideKeyboard()
            enterword.getText().clear()







        }

        button2.setOnClickListener {
            button2.visibility= View.INVISIBLE
            button.visibility= View.VISIBLE
            guessList.clear()
            word1.setText("")
            word1Guess.setText("")
            word2.setText("")
            word2Guess.setText("")
            word3.setText("")
            word3Guess.setText("")
            wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()
            answer.text= wordToGuess
            answer.visibility= View.INVISIBLE

        }







       // val wordToGuess = findViewById<TextView>(R.id.wordToGuess)


        /**
         * Parameters / Fields:
         *   wordToGuess : String - the target word the user is trying to guess
         *   guess : String - what the user entered as their guess
         *
         * Returns a String of 'O', '+', and 'X', where:
         *   'O' represents the right letter in the right place
         *   '+' represents the right letter in the wrong place
         *   'X' represents a letter not in the target word
         */


    }
}