package com.wulinpeng.ezreader.appcontext.navigator

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

/**
 * 整个 App 底部导航栏的高度
 */
val LocalBottomBarHeight = staticCompositionLocalOf<Dp> { error("no navigator provided!") }