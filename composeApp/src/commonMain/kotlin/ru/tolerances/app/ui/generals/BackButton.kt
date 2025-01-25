package ru.tolerances.app.ui.generals

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onPop: () -> Unit
) {
    IconButton(
        modifier = modifier
            .wrapContentHeight()
            .aspectRatio(1f),
        onClick = onPop,
        content = {
            Icon(
                modifier = Modifier
                    .fillMaxSize(),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "",
                tint = MaterialTheme.colors.onBackground
            )
        }
    )
}
