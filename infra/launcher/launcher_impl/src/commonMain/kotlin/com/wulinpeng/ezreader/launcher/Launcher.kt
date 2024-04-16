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

    // TODO: 没有 Task 注入时通过 get 获取会崩溃
    private val tasks = runCatching { get<List<ILauncherTask>>() }.getOrElse { emptyList() }
    private val coroutineScope = CoroutineScope(SupervisorJob())

    fun launch() {
        tasks.sortedByDescending { it.priority() }.forEach {
            when (it.type()) {
                ILauncherTaskType.MAIN -> it.doTask()
                ILauncherTaskType.IO -> coroutineScope.launch(Dispatchers.IO) {
                    it.doTask()
                }
                ILauncherTaskType.WORKER -> coroutineScope.launch(Dispatchers.Default) {
                    it.doTask()
                }
            }
        }
    }
}