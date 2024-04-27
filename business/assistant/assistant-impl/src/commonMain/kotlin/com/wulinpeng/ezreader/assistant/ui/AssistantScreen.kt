package com.wulinpeng.ezreader.assistant.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 14:54
 * @Description:
 */
@Composable
fun AssistantScreen(vm: AssistantVM) {
    val state = vm.uiState
    Column(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth().weight(1f)) {
            if (vm.uiState.conversations.isEmpty()) {
                // 初始状态
                DefaultScreen(state.defaultHints, Modifier.align(Alignment.Center)) {
                    vm.onClickHint(it)
                }
            } else {
                // 对话列表
                ConversationList(vm)
            }
        }
        var inputContent by remember { mutableStateOf("") }
        // 输入框
        InputComponent(!state.isAnswering.value, inputContent, { inputContent = it }) {
            vm.sendMessage(it)
            // 清除输入内容
            inputContent = ""
        }
    }
}