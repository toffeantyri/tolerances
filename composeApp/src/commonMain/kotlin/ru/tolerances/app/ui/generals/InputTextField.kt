package ru.tolerances.app.ui.generals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.tolerances.app.ui.theme.RedAttention
import ru.tolerances.app.ui.theme.Transparent
import ru.tolerances.app.ui.theme.WetAsphalt
import tolerancestabledata.composeapp.generated.resources.Res
import tolerancestabledata.composeapp.generated.resources.unknown_error

@Composable
fun InputTextField(
    valueState: State<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: String? = null,
    showBottomErrorMessage: Boolean = true,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = {
        if (error?.isNotEmpty() == true) {
            Icon(Icons.Filled.Warning, "Error", tint = MaterialTheme.colorScheme.error)
        }
    },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    colors: TextFieldColors =
        OutlinedTextFieldDefaults.colors(
            focusedTextColor = WetAsphalt,
            unfocusedTextColor = WetAsphalt,
            focusedContainerColor = Transparent,
            unfocusedContainerColor = Transparent,
            focusedBorderColor = WetAsphalt,
            unfocusedBorderColor = WetAsphalt,
            errorBorderColor = RedAttention,
            errorLabelColor = RedAttention
        ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium
) {
    OutlinedTextField(
        placeholder = placeholder,
        value = valueState.value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        maxLines = 1,
        label = label,
        leadingIcon = leadingIcon,
        colors = colors,
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        isError = error.isNullOrEmpty().not(),
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        textStyle = textStyle

    )

    AnimatedVisibility(visible = (error?.isNotEmpty() ?: false) && showBottomErrorMessage) {
        Text(
            text = error ?: stringResource(Res.string.unknown_error),
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 32.dp, end = 32.dp),
            style = MaterialTheme.typography.displaySmall,
            color = RedAttention
        )
    }
}

