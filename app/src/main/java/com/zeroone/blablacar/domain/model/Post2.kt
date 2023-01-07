package com.zeroone.blablacar.domain.model

import android.graphics.Point
import android.location.Address
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.zeroone.blablacar.domain.model.google_map.direction.Direction

data class Post2(
    val fromAddress: String="",
    val toAddress: String="",
    val points: String?=null,
    val date: String="",
    val time: String="",
    val personCount: Int=0,
    val price: Float=0f,
)