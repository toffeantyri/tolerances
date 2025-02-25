package ru.tolerances.app.ui.cutting_speed_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.tolerances.app.components.cutting_speed_screen_component.dialog.ICuttingTypeCalcResultComponent
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun CuttingResultDialogView(component: ICuttingTypeCalcResultComponent) {

    Dialog(onDismissRequest = component::onDismissAction) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            backgroundColor = Color.White
        ) {
            Box(
                modifier = Modifier.wrapContentSize().padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                val result = component.resultValue.value.toFloat()
                val factor = 10.0.pow(2.toDouble()).toFloat()
                Text(
                    ((result * factor).roundToInt() / factor).toString(), style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.2.sp,
                        letterSpacing = 0.42.sp
                    )
                )
            }
        }
    }
}