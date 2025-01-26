package ru.tolerances.app.ui.generals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sarawan.delivery.ui.generals.text_fields.AutoResizeText
import ru.tolerances.app.ui.theme.ButtonGray
import ru.tolerances.app.ui.theme.OceanBlue
import ru.tolerances.app.ui.theme.White


@Composable
fun MyFilledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(30.dp),
    elevation: androidx.compose.material3.ButtonElevation? = androidx.compose.material3.ButtonDefaults.buttonElevation(
        defaultElevation = 2.dp
    ),
    colors: androidx.compose.material3.ButtonColors = androidx.compose.material3.ButtonDefaults.buttonColors(
        containerColor = OceanBlue,
        disabledContainerColor = ButtonGray
    ),
    isLoadingState: Boolean = false,
    enabled: Boolean = true,
    textLabel: @Composable () -> Unit = {
        AutoResizeText(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            text = text,
            color = if (isLoadingState) {
                OceanBlue
            } else {
                White
            },
            textStyleMain = MaterialTheme.typography.displayMedium,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
) {


    Button(
        onClick = {
            onClick()
        },
        elevation = elevation,
        shape = shape,
        colors = colors,
        modifier = modifier
            .wrapContentHeight(),
        enabled = enabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            textLabel()

            if (isLoadingState) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .alpha(0.8f)
                        .height(20.dp)
                        .aspectRatio(1f),
                    color = White
                )
            }
        }

    }
}
