package com.wulinpeng.ezreader.network

import com.wulinpeng.ezreader.launcher.ILauncherTask
import com.wulinpeng.ezreader.launcher.ILauncherTaskType
import org.koin.core.annotation.Single

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 14:44
 * @Description:
 */
@Single
class NetworkInitTask: ILauncherTask {
    override fun type(): ILauncherTaskType = ILauncherTaskType.MAIN

    override fun doTask() {
        NetworkManager.init()
    }
}