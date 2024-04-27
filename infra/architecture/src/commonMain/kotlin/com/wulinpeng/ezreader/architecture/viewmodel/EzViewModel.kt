package com.wulinpeng.ezreader.architecture.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 16:44
 * @Description:
 */
open class EzViewModel: ScreenModel, CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob()
}