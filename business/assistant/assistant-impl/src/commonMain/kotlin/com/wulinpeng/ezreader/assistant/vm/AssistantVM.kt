package com.wulinpeng.ezreader.assistant.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import com.wulinpeng.ezreader.architecture.viewmodel.EzViewModel
import com.wulinpeng.ezreader.assistant.repo.AssistantRepo
import compose.icons.FeatherIcons
import compose.icons.feathericons.Book
import compose.icons.feathericons.Search
import compose.icons.feathericons.UserX
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 14:57
 * @Description:
 */

data class AssistantUIState(
    val isAnswering: MutableState<Boolean> = mutableStateOf(false),
    val defaultHints: List<DefaultHint> = emptyList(),
    val conversations: MutableList<Conversation> = mutableStateListOf()
)

enum class DefaultHintType {
    RECOMMEND, // 推荐小说
    COMMENT, // 评价小说
    SUMMARY, // 总结小说
    INTRODUCE // 介绍作者
}
data class DefaultHint(
    val content: String,
    val inputContent: String,
    val inputSelection: TextRange,
    val icon: ImageVector,
    val hintType: DefaultHintType,
    val background: Color
)

data class Conversation(
    val content: MutableState<String>,
    val type: ConversationType,
    val isAnswering: MutableState<Boolean>
)

enum class ConversationType(val displayName: String) {
    USER("用户"),
    ASSISTANT("AI 助手")
}

class AssistantVM: EzViewModel() {
    val uiState = AssistantUIState(defaultHints = loadDefaultHints())
    val repo = AssistantRepo()

    private fun loadDefaultHints(): List<DefaultHint> {
        return listOf(
            DefaultHint("帮我推荐一本小说", "推荐一本小说,要求:", TextRange(12), FeatherIcons.Search, DefaultHintType.RECOMMEND, Color(215, 220, 242)),
            DefaultHint("帮我评价一本小说", "评价一下《》这本小说", TextRange(5), FeatherIcons.Book, DefaultHintType.COMMENT, Color(226, 244, 230)),
            DefaultHint("帮我总结小说内容", "总结一下《》这本小说", TextRange(5), FeatherIcons.Book, DefaultHintType.SUMMARY, Color(253, 241, 208)),
            DefaultHint("给我介绍作者信息", "介绍一下作者:耳根", TextRange(7, 9), FeatherIcons.UserX, DefaultHintType.INTRODUCE, Color(230, 230, 230))
        )
    }

    fun clearConversations() {
        coroutineContext.cancelChildren()
        uiState.conversations.clear()
    }

    fun sendMessage(content: String) {
        uiState.conversations.add(Conversation(mutableStateOf(content), ConversationType.USER, mutableStateOf(false)))
        val answerConversation = Conversation(mutableStateOf(""), ConversationType.ASSISTANT, mutableStateOf(true))
        uiState.conversations.add(answerConversation)
        launch {
            uiState.isAnswering.value = true
            repo.getAnswer(content).collect {
                answerConversation.content.value += it
            }
            answerConversation.isAnswering.value = false
            uiState.isAnswering.value = false
        }.invokeOnCompletion {
            uiState.isAnswering.value = false
        }
    }

}