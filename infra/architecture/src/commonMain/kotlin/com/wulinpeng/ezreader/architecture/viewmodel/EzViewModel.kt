package com.wulinpeng.ezreader.architecture.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 16:44
 * @Description:
 */
open class EzViewModel: ScreenModel, CoroutineScope, KoinComponent, CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler.Key
    override val coroutineContext: CoroutineContext = SupervisorJob() + this

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        exception.printStackTrace()
    }
}