package com.sevenpeakssoftware.core_ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sevenpeakssoftware.core_ui.LightBlack
import com.sevenpeakssoftware.core_ui.topAppBarTitle

@Composable
fun PCarToolbar(
    title: String,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    backgroundColor: Color = LightBlack
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = title,
                style = topAppBarTitle(),
                modifier = Modifier.align(
                    Alignment.CenterStart
                ).padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewPCarToolbar() {
    PCarToolbar(
        title = "Title"
    )
}
