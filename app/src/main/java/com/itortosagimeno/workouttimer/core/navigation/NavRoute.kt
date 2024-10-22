package com.itortosagimeno.workouttimer.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class SelectorRoute(val type: Int)

@Serializable
data class TimerRoute(val id: Int)