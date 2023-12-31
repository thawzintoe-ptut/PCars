package com.sevenpeakssoftware.core_ui.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

const val SHIMMER_LIST_ITEM_COUNT = 5
@Composable
fun ShimmerLoading(modifier: Modifier) {
    LazyColumn(modifier) {
        items(SHIMMER_LIST_ITEM_COUNT) {
            Column(
                Modifier
                    .background(
                        color = Color.Black
                    )
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(270.dp)
                        .shimmerEffect()
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(14.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(56.dp)
                        .shimmerEffect()
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(14.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(20.dp)
                        .shimmerEffect()
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(13.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(40.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        ),
        shape = RoundedCornerShape(10.dp)
    ).onGloballyPositioned {
        size = it.size
    }
}
