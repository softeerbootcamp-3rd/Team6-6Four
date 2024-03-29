package com.softeer.team6four.data.remote.payment.dto

import kotlinx.serialization.Serializable

@Serializable
data class PointDetailList(
    val content: List<PointDetail>,
    val hasNext: Boolean,
    val size: Int
)