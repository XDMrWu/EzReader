package com.wulinpeng.ezreader.storage

import com.wulinpeng.ezreader.launcher.EzPlatformContext

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 16:26
 * @Description:
 */
actual object StorageManager {

    private lateinit var context: EzPlatformContext
    actual var storageDir: String
        get() = context.context.cacheDir.path
        set(value) {}

    actual fun init(platformContext: EzPlatformContext) {
        context = platformContext
    }
}