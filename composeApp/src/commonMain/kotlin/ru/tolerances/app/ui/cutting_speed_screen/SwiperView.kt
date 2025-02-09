package ru.tolerances.app.ui.cutting_speed_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.tolerances.app.domain.models.CuttingCalcType
import ru.tolerances.app.ui.theme.LightGray
import ru.tolerances.app.ui.theme.LiquidBlue
import ru.tolerances.app.ui.theme.MintGreen

@Composable
fun SwiperView(
    selectedCuttingType: State<CuttingCalcType>,
    onToggleCuttingType: (Boolean) -> Unit
) {

    val toggleState = remember(selectedCuttingType.value) {
        derivedStateOf { selectedCuttingType.value == CuttingCalcType.CalcV }
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 8.dp)
            .border(
                BorderStroke(1.dp, LightGray), RoundedCornerShape(22.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(toggleState.value) {
            Text(
                modifier = Modifier.fillMaxWidth(0.5f)
                    .clickable(interactionSource = null, indication = null) {
                        onToggleCuttingType(false)
                    }.padding(vertical = 10.dp),
                text = CuttingCalcType.CalcN.getTypeTitle(),
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
        OutlinedIconToggleButton(
            modifier = Modifier.fillMaxWidth(if (toggleState.value) 1f else 0.5f)
                .padding(horizontal = 4.dp).pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        when {
                            dragAmount > 0 -> {
                                if (!toggleState.value) {
                                    onToggleCuttingType(true)
                                }
                            }// Свайп вправо
                            dragAmount < 0 -> {
                                if (toggleState.value) {
                                    onToggleCuttingType(false)
                                }
                            } // Свайп влево
                        }
                    }
                },
            checked = toggleState.value,
            onCheckedChange = onToggleCuttingType,
            colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                checkedContainerColor = LiquidBlue,
                containerColor = MintGreen
            ),
            border = null
        ) {
            Text(
                text = selectedCuttingType.value.getTypeTitle(),
                maxLines = 1
            )
        }

        AnimatedVisibility(toggleState.value.not()) {
            Text(
                modifier = Modifier.fillMaxWidth(1f)
                    .clickable(interactionSource = null, indication = null) {
                        onToggleCuttingType(true)
                    }.padding(vertical = 10.dp),
                text = CuttingCalcType.CalcV.getTypeTitle(),
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }


}