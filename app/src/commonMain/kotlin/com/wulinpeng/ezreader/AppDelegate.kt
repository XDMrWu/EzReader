package com.wulinpeng.ezreader.com.wulinpeng.ezreader

import com.wulinpeng.ezreader.launcher.Launcher
import org.koin.core.context.startKoin

/**
 * 三端 Application 生命周期统一代理
 */
object AppDelegate {
    fun onCreate() {
        initKoin()
        startLauncher()
    }

    /**
     * 每个模块提供一个 Module
     */
    private fun initKoin() {
        startKoin {
//            modules(coreModule)
        }
    }

    /**
     * 启动 Launcher
     */
    private fun startLauncher() {
        Launcher.launch()
    }
}