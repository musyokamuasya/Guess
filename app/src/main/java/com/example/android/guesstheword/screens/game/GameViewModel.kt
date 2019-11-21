package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel(){
    // The current word
   private val mutableWord = MutableLiveData<String>()
    val word:LiveData<String>
    get() = mutableWord

    // The current score
    private val mutableScore = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = mutableScore

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish:LiveData<Boolean>
    get() = _eventGameFinish

    private val _currentTime = MutableLiveData<Long>()
    val currentTime:LiveData<Long>
    get() = _currentTime

    val timer:CountDownTimer

//    Companion object for the timer
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewModel","GameViewModel started")
        resetList()
        nextWord()
        mutableScore.value = 0
        _eventGameFinish.value = false

        timer= object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFinish() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        timer.start()
    }

//    DateUtils.formatElapsedTime(newTime)
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
          resetList()
        }
        mutableWord.value = wordList.removeAt(0)

    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = false

    }

    /** Methods for buttons presses **/

   fun onSkip() {
        mutableScore.value = (score.value)?.minus(1)
        nextWord()
    }

  fun onCorrect() {
        mutableScore.value = (score.value)?.plus(1)
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}