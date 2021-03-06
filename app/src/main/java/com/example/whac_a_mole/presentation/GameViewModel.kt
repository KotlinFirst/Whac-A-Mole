package com.example.whac_a_mole.presentation

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.whac_a_mole.R
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private var timerEnd: CountDownTimer? = null
    private var timerMole: CountDownTimer? = null

    private var _observeFitishTimer = MutableLiveData<Boolean>()
    val observeFitishTimer: LiveData<Boolean>
        get() = _observeFitishTimer

    var result = MutableLiveData<Int>()
    var record: Int = 0

    private var _ovalWithMole = MutableLiveData<Int>()
    val ovalWithMole: LiveData<Int>
        get() = _ovalWithMole

    fun startGame(view: View) {
        startTimerEnd(view)
        startTimerMole()
        nextRandomOval()
    }

    fun filterOnClick() {
        if (result.value == null) {
            result.value = 1

        }
        Log.d("resultViewModel_FilterOnClick", result.value.toString())
        result.value = result.value!! + 1

        if (result.value!! > record) {
            record = result.value!!
        }
    }

    private val _formatedTime = MutableLiveData<String>()
    val formatedTime: LiveData<String>
        get() = _formatedTime


    fun nextRandomOval(): Int {
        _ovalWithMole.value = Random.nextInt(1, COUNT_OVALS + 1)
        return ovalWithMole.value!!
    }

    private fun startTimerMole() {
        timerMole = object : CountDownTimer(TIMER_APPEARANCE_MOLE, TIMER_APPEARANCE_MOLE) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                nextRandomOval()
                startTimerMole()
            }
        }
        timerMole?.start()
    }

    private fun startTimerEnd(view: View) {
        timerEnd = object : CountDownTimer(TIMER_MILLIS, MILLIS_IN_SECONDS) {
            override fun onTick(millisUntilFinished: Long) {
                _formatedTime.value = formatTime((millisUntilFinished))
            }

            override fun onFinish() {
                _observeFitishTimer.value = true
            }
        }
        timerEnd?.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }


    override fun onCleared() {
        super.onCleared()
        timerEnd?.cancel()
        timerMole?.cancel()
    }

    companion object {
        private const val TIMER_APPEARANCE_MOLE = 500L
        private const val TIMER_MILLIS = 30000L
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
        private const val COUNT_OVALS = 8
    }
}