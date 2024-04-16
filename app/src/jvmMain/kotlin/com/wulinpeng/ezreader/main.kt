package com.wulinpeng.ezreader

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import com.wulinpeng.ezreader.App
import com.wulinpeng.ezreader.com.wulinpeng.ezreader.AppDelegate

fun main() = application {
    AppDelegate.onCreate()
    Window(
        title = "EzReader",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        App()
    }
}