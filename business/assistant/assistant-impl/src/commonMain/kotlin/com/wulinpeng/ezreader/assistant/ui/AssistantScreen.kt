package com.wulinpeng.ezreader.assistant.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.TextRange
import com.wulinpeng.ezreader.assistant.vm.AssistantVM
import kotlinx.coroutines.launch

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 14:54
 * @Description:
 */
@Composable
fun AssistantScreen(vm: AssistantVM) {
    val state = vm.uiState
    val scope = rememberCoroutineScope()
    var inputContent by remember { mutableStateOf("") }
    var textRange by remember { mutableStateOf(TextRange.Zero) }
    val focusRequester = remember { FocusRequester() }
    Column(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth().weight(1f)) {
            if (vm.uiState.conversations.isEmpty()) {
                // 初始状态
                DefaultScreen(state.defaultHints, Modifier.align(Alignment.Center)) {
                    inputContent = it.inputContent
                    textRange = it.inputSelection
                    scope.launch {
                        focusRequester.requestFocus()
                    }
                }
            } else {
                // 对话列表
                ConversationList(vm)
            }
        }
        // 输入框
        InputComponent(!state.isAnswering.value, inputContent, textRange, focusRequester, {
            inputContent = it.text
            textRange = it.selection
        }) {
            vm.sendMessage(it)
            // 清除输入内容
            inputContent = ""
        }
    }
}