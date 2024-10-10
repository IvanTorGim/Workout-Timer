package com.ivtogi.workouttimer.domain.repository

import com.ivtogi.workouttimer.domain.model.Timer

interface LocalStorageRepository {
    suspend fun saveTimerWithExercises(timer: Timer): Long
    suspend fun getTimerWithExercisesById(id: Int): Timer
}