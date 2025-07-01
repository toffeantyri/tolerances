package ru.tolerances.app.utils

import androidx.compose.foundation.focusable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type


fun Modifier.onKeyEnter(focusRequester: FocusRequester, onKeyEnterAction: () -> Unit) =
    this.onKeyEvent {
        if (it.key == Key.Enter && it.type == KeyEventType.KeyUp) {
            onKeyEnterAction()
            true
        } else false
    }.focusRequester(focusRequester).focusable()
