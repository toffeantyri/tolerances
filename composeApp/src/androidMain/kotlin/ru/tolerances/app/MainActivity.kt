package ru.tolerances.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.retainedComponent
import ru.tolerances.app.components.IRootComponent
import ru.tolerances.app.components.RootComponentImpl

class MainActivity : ComponentActivity() {

    private lateinit var rootComponent: IRootComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        rootComponent = retainedComponent { componentContext ->
            RootComponentImpl(componentContext)
        }
        enableEdgeToEdge()
        setContent {
            RootApp(component = rootComponent)
        }
    }
}
