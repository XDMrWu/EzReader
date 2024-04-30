package com.wulinpeng.ezreader.storage

import com.wulinpeng.ezreader.launcher.EzPlatformContext
import com.wulinpeng.ezreader.launcher.ILauncherTask
import com.wulinpeng.ezreader.launcher.ILauncherTaskType
import org.koin.core.annotation.Single

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 16:24
 * @Description:
 */
@Single
class StorageInitTask: ILauncherTask {
    override fun type(): ILauncherTaskType = ILauncherTaskType.MAIN

    override fun doTask(context: EzPlatformContext) {
        StorageManager.init(context)
    }
}