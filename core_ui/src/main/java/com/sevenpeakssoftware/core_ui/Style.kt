package com.sevenpeakssoftware.core_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Roboto_Regular = FontFamily(Font(R.font.roboto_regular))
val Roboto_Medium = FontFamily(Font(R.font.roboto_medium))

@Composable
fun topAppBarDate(): TextStyle {
    return TextStyle(
        fontFamily = Roboto_Medium,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
}

@Composable
fun topAppBarTitle(): TextStyle {
    return TextStyle(
        fontFamily = Roboto_Medium,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = Color.White
    )
}

@Composable
fun titleTextStyle(): TextStyle {
    return TextStyle(
        fontFamily = Roboto_Medium,
        fontSize = 20.sp,
        color = Color.White
    )
}

@Composable
fun dateTextStyle(): TextStyle {
    return TextStyle(
        fontFamily = Roboto_Regular,
        fontSize = 14.sp,
        color = Color(0xffacacac)
    )
}

@Composable
fun normalTextStyle(): TextStyle {
    return TextStyle(
        fontFamily = Roboto_Regular,
        fontSize = 14.sp,
        color = Color(0xffffffff)
    )
}
