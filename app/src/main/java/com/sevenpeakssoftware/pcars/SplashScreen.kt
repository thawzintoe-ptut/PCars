package com.sevenpeakssoftware.pcars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sevenpeakssoftware.core.navigation.Screen
import kotlinx.coroutines.delay

private const val SPLASH_LUNCH_TIME = 2000L

@Composable
fun SplashScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.Black)
        )
    }
    LaunchedEffect(Unit) {
        delay(SPLASH_LUNCH_TIME)
        navHostController.navigate(Screen.Login.route)
    }
}
