package com.example.whac_a_mole.domain

data class GameResult (
    val currentGameScore:Int,
    val recordGameScore:Int
        ){
    val record:Boolean = currentGameScore > recordGameScore
}