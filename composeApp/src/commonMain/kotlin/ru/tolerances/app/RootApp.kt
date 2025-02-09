package ru.tolerances.app

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.stack.animation.isFront
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.androidPredictiveBackAnimatable
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimator
import com.arkivanov.essenty.backhandler.BackHandler
import ru.tolerances.app.components.IRootComponent
import ru.tolerances.app.ui.cutting_speed_screen.CuttingSpeedScreenView
import ru.tolerances.app.ui.generals.TitleText
import ru.tolerances.app.ui.theme.AppTheme
import ru.tolerances.app.ui.theme.LiquidBlue
import ru.tolerances.app.ui.theme.bold18TitleTextStyle
import ru.tolerances.app.ui.tolerances_screen.TolerancesScreenView

@Composable
fun RootApp(modifier: Modifier = Modifier, component: IRootComponent) {

    val selectedBottomTabState = component.selectedBottomTab.collectAsState()

    val bottomItemsList = listOf(
        BottomItemData(
            Icons.AutoMirrored.Outlined.List,
            "Допуски",
            component::onTolerancesScreen
        ),
        BottomItemData(
            Icons.Filled.Search,
            "Калькулятор",
            component::onCuttingSpeedScreen
        )
    )
    AppTheme {

        Scaffold(
            topBar = {
                TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = LiquidBlue) {
                    TitleText(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Справочник",
                        textStyle = bold18TitleTextStyle(),
                        textAlign = TextAlign.Center
                    )
                }
            },
            bottomBar = {
                MainBottomNavigation(
                    bottomTabs = bottomItemsList,
                    selectedIndexState = selectedBottomTabState
                )
            }
        ) { innerPadding ->
            Children(
                stack = component.childStackBottom,
                modifier = modifier
                    .padding(innerPadding)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                animation = backAnimation(
                    backHandler = component.backHandler,
                    onBack = component::onExitClicked
                )
            ) {
                when (val item = it.instance) {
                    is IRootComponent.Child.OnTolerancesScreenChild -> TolerancesScreenView(item.component)
                    is IRootComponent.Child.OnCuttingSpeedScreenChild -> CuttingSpeedScreenView(item.component)
                }
            }

        }


    }
}

@OptIn(ExperimentalDecomposeApi::class)
fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T> {
    return predictiveBackAnimation(
        backHandler = backHandler,
        fallbackAnimation = stackAnimation(iosLikeSlide()),
        selector = { backEvent, _, _ -> androidPredictiveBackAnimatable(backEvent) },
        onBack = onBack
    )
}


fun iosLikeSlide(animationSpec: FiniteAnimationSpec<Float> = tween()): StackAnimator =
    stackAnimator(animationSpec = animationSpec) { factor, direction, content ->
        content(
            Modifier
                .then(if (direction.isFront) Modifier else Modifier.fade(factor + 1F))
                .offsetXFactor(factor = if (direction.isFront) factor else factor * 0.5F)
        )
    }

fun Modifier.slideExitModifier(progress: Float): Modifier =
    offsetXFactor(progress)

fun Modifier.slideEnterModifier(progress: Float): Modifier =
    fade(progress).offsetXFactor((progress - 1f) * 0.5f)

private fun Modifier.fade(factor: Float) =
    drawWithContent {
        drawContent()
        drawRect(color = Color(red = 0F, green = 0F, blue = 0F, alpha = (1F - factor) / 4F))
    }

private fun Modifier.offsetXFactor(factor: Float): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.placeRelative(x = (placeable.width.toFloat() * factor).toInt(), y = 0)
        }
    }


data class BottomItemData(
    val icon: ImageVector,
    val localizedName: String,
    val onNavigate: () -> Unit
)

@Composable
private fun MainBottomNavigation(
    bottomTabs: List<BottomItemData>,
    selectedIndexState: State<Int>,
) {

    Surface(elevation = 5.dp) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            bottomTabs.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, null, modifier = Modifier.alpha(0.5f)) },
                    label = { Text(item.localizedName) },
                    selected = index == selectedIndexState.value,
                    colors = NavigationBarItemDefaults.colors(indicatorColor = LiquidBlue),
                    onClick = item.onNavigate
                )
            }
        }
    }

}

