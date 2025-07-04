package ru.tolerances.app.ui.tolerances_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
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
import ru.tolerances.app.components.tolerances_screen_component.dialogs.ITolerancesResultDialogComponent
import ru.tolerances.app.ui.theme.MintGreen
import ru.tolerances.app.ui.theme.OceanBlue
import ru.tolerances.app.ui.theme.bold16TextStyle
import ru.tolerances.app.ui.theme.regular14TextStyle

@Composable
fun ToleranceResultDialogView(component: ITolerancesResultDialogComponent) {

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
                val result = component.resultValue.value
                println("result = $result")
                val halfToler = ((result.maxToler ?: 0f) + (result.minToler ?: 0f)) / 2
                println("halfToler $halfToler")
                val middleResult = component.inputedSize.value + (halfToler)

                SelectionContainer {
                    Column {

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Номинальный размер: ",
                                style = regular14TextStyle()
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = component.inputedSize.value.toString(),
                                style = bold16TextStyle()
                            )
                        }

                        if (result.maxToler == null || result.minToler == null) {
                            Text("-")
                        } else {
                            SelectionContainer {
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "Верхнее отклонение: ",
                                            style = regular14TextStyle()
                                        )
                                        Text(
                                            modifier = Modifier.padding(horizontal = 8.dp),
                                            text = result.maxToler.toString(),
                                            style = bold16TextStyle()
                                        )
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "Нижнее отклонение: ",
                                            style = regular14TextStyle()
                                        )
                                        Text(
                                            modifier = Modifier.padding(horizontal = 8.dp),
                                            text = result.minToler.toString(),
                                            style = bold16TextStyle()
                                        )
                                    }
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                                    .border(BorderStroke(1.dp, OceanBlue), RoundedCornerShape(4.dp))
                                    .padding(vertical = 8.dp)
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Середина допуска: ")
                                SelectionContainer {
                                    Text(
                                        modifier = Modifier.background(
                                            MintGreen, RoundedCornerShape(4.dp)
                                        ).padding(4.dp),
                                        text = middleResult.toString(),
                                        style = TextStyle(
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                            lineHeight = 18.2.sp,
                                            letterSpacing = 0.42.sp
                                        ),
                                        maxLines = 1
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}