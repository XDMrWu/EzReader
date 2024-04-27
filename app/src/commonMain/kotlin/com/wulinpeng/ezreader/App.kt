package com.wulinpeng.ezreader

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.homepage.screen.HomePageScreen
import org.koin.compose.KoinContext

@Composable
fun App() {
    AppTheme {
        KoinContext {
            Navigator(HomePageScreen) { navigator ->
                CompositionLocalProvider(LocalRootNavigator provides navigator) {
                    CurrentScreen()
                }
            }
        }
    }
}