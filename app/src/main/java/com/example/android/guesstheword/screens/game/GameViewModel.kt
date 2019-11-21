package com.example.android.guesstheword.screens.game

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

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewModel","GameViewModel started")
        resetList()
        nextWord()
        mutableScore.value = 0
        _eventGameFinish.value = false
    }
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
            _eventGameFinish.value = true
        } else {
            mutableWord.value = wordList.removeAt(0)
        }

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
}