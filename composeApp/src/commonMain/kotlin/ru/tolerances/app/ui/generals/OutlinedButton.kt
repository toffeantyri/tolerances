package ru.tolerances.app.ui.generals

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sarawan.delivery.ui.generals.text_fields.AutoResizeText
import ru.tolerances.app.ui.theme.mintGreen


@Composable
fun SaraOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(30.dp),
    border: BorderStroke = BorderStroke(1.dp, color = mintGreen),
    elevation: ButtonElevation? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    enabled: Boolean = true,
    textLabel: @Composable () -> Unit = {
        AutoResizeText(
            modifier = Modifier.padding(vertical = 4.dp),
            text = text,
            color = textColor,
            textStyleMain = MaterialTheme.typography.headlineMedium,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
) {
    /**
     *   elevation = null тк тень падает внутри кнопки если фон кнопки прозрачный
     */

    Button(
        onClick = onClick,
        shape = shape,
        enabled = enabled,
        border = border,
        colors = colors,
        elevation = elevation,
        modifier = modifier
            .wrapContentHeight()
    ) {
        textLabel()
    }
}
