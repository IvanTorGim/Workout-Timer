package com.ivtogi.workouttimer.domain.repository

import com.ivtogi.workouttimer.domain.model.Timer

interface LocalStorageRepository {
    suspend fun saveTimer(timer: Timer): Long
    suspend fun getTimerById(id: Int): Timer
}