package com.softeer.team6four.data.remote.reservation.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReservationTime(
    val endTime: String,
    val startTime: String
)