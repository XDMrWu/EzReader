package com.wulinpeng.ezreader.launcher

/**
 * @Author: wulinpeng
 * @Date: 2024/4/15 23:24
 * @Description:
 */

enum class ILauncherTaskType {
    MAIN, IO, WORKER
}

interface ILauncherTask {
    fun type(): ILauncherTaskType
    fun doTask()

    fun priority(): Int = 0
}