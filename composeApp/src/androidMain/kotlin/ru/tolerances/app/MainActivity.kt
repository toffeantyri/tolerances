package ru.tolerances.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.retainedComponent
import ru.tolerances.app.components.IRootComponent
import ru.tolerances.app.components.RootComponentImpl

class MainActivity : ComponentActivity() {

    private lateinit var rootComponent: IRootComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootComponent = retainedComponent { componentContext ->
            RootComponentImpl(componentContext)
        }

        setContent {
            RootApp(component = rootComponent)
        }
    }
}
