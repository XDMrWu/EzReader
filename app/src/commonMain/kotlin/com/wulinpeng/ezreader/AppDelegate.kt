package com.wulinpeng.ezreader.com.wulinpeng.ezreader

import com.wulinpeng.ezreader.AppModule
import com.wulinpeng.ezreader.launcher.EzPlatformContext
import com.wulinpeng.ezreader.launcher.Launcher
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

/**
 * 三端 Application 生命周期统一代理
 */
object AppDelegate {
    fun onCreate(context: EzPlatformContext) {
        initKoin()
        startLauncher(context)
    }

    /**
     * 每个模块提供一个 Module
     */
    private fun initKoin() {
        startKoin {
            modules(AppModule().module)
        }
    }

    /**
     * 启动 Launcher
     */
    private fun startLauncher(context: EzPlatformContext) {
        Launcher.launch(context)
    }

}