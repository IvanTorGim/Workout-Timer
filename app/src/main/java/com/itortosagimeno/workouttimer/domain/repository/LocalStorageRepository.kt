package com.itortosagimeno.workouttimer.domain.repository

import com.itortosagimeno.workouttimer.domain.model.Timer

interface LocalStorageRepository {
    suspend fun saveTimer(timer: Timer): Long
    suspend fun getTimerById(id: Int): Timer
}