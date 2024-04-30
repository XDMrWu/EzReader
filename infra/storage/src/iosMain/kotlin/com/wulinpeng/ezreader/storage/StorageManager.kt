package com.wulinpeng.ezreader.storage

import com.wulinpeng.ezreader.launcher.EzPlatformContext
import io.github.xxfast.kstore.file.utils.CachesDirectory
import io.github.xxfast.kstore.utils.ExperimentalKStoreApi
import platform.Foundation.NSFileManager

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 16:26
 * @Description:
 */
actual object StorageManager {

    private lateinit var context: EzPlatformContext
    @OptIn(ExperimentalKStoreApi::class)
    actual var storageDir: String
        get() = NSFileManager.defaultManager.CachesDirectory?.relativePath ?: ""
        set(value) {}

    actual fun init(platformContext: EzPlatformContext) {
        context = platformContext
    }
}