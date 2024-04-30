package com.wulinpeng.ezreader.storage

import com.wulinpeng.ezreader.launcher.EzPlatformContext
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 16:26
 * @Description:
 */
actual object StorageManager {

    private lateinit var context: EzPlatformContext
    actual var storageDir: String
        get() = File("build", "storage_dir").apply {
            if (parentFile.exists().not()) {
                parentFile.mkdirs()
            }
            if (exists().not()) {
                mkdirs()
            }
        }.toString()
        set(value) {}

    actual fun init(platformContext: EzPlatformContext) {
        context = platformContext
    }
}