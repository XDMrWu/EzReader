package com.wulinpeng.ezreader.storage

import com.wulinpeng.ezreader.launcher.EzPlatformContext

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 16:26
 * @Description:
 */
expect object StorageManager {

    var storageDir: String
    fun init(platformContext: EzPlatformContext)
}