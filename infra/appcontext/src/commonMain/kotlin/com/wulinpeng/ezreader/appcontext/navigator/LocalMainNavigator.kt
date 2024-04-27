package com.wulinpeng.ezreader.appcontext.navigator

import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.voyager.navigator.Navigator

/**
 * 整个 App 根布局的 Navigator
 */
val LocalRootNavigator = staticCompositionLocalOf<Navigator> { error("no navigator provided!") }