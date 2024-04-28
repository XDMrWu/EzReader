package com.wulinpeng.ezreader

import androidx.compose.ui.graphics.vector.ImageVector
import com.wulinpeng.ezreader.commonres.Gpt
import com.wulinpeng.ezreader.commonres.User
import kotlin.collections.List as ____KtList

public object CommonRes

private var __AllIcons: ____KtList<ImageVector>? = null

public val CommonRes.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(User, Gpt)
    return __AllIcons!!
  }
