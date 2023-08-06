package com.sevenpeakssoftware.core_ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sevenpeakssoftware.core_ui.titleTextStyle

@Composable
fun PCarButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = text,
            style = titleTextStyle().copy(color = Color.White),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewRwsButton() {
    PCarButton(text = "RESERVE NOW")
}
