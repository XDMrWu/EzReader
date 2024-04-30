package com.wulinpeng.ezreader.launcher

/**
 * @Author: wulinpeng
 * @Date: 2024/4/15 23:24
 * @Description:
 */

enum class ILauncherTaskType {
    MAIN, IO, WORKER
}

expect class EzPlatformContext

interface ILauncherTask {
    fun type(): ILauncherTaskType
    fun doTask(context: EzPlatformContext)

    fun priority(): Int = 0
}