package com.sevenpeakssoftware.core_ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@Composable
fun NetworkImagePainter(
    modifier: Modifier = Modifier,
    url: String,
    @DrawableRes placeHolderImageResourceId: Int = R.drawable.ic_placeholder,
    contentScale: ContentScale = ContentScale.Fit
) {
    val baseUrl = "https://cars-sevenpeaks.web.app"
    AsyncImage(
        model = "$baseUrl/$url",
        contentDescription = null,
        error = painterResource(placeHolderImageResourceId),
        contentScale = contentScale,
        modifier = modifier,
    )
}
