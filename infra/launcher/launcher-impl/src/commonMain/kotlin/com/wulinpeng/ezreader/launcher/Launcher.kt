package com.wulinpeng.ezreader.launcher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

/**
 * @Author: wulinpeng
 * @Date: 2024/4/15 23:28
 * @Description:
 */
object Launcher: KoinComponent {

    private val tasks = getKoin().getAll<ILauncherTask>()
    private val coroutineScope = CoroutineScope(SupervisorJob())

    fun launch(context: EzPlatformContext) {
        tasks.sortedByDescending { it.priority() }.forEach {
            when (it.type()) {
                ILauncherTaskType.MAIN -> it.doTask(context)
                ILauncherTaskType.IO -> coroutineScope.launch(Dispatchers.IO) {
                    it.doTask(context)
                }
                ILauncherTaskType.WORKER -> coroutineScope.launch(Dispatchers.Default) {
                    it.doTask(context)
                }
            }
        }
    }
}