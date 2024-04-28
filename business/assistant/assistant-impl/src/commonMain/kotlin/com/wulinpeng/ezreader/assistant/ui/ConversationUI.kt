package com.wulinpeng.ezreader.assistant.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.wulinpeng.ezreader.CommonRes
import com.wulinpeng.ezreader.commonres.Gpt
import com.wulinpeng.ezreader.commonres.User
import compose.icons.FeatherIcons
import compose.icons.feathericons.Trash
import compose.icons.feathericons.User
import compose.icons.feathericons.UserCheck
import io.github.skeptick.libres.compose.painterResource

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 16:20
 * @Description:
 */
@Composable
fun ConversationList(vm: AssistantVM) {
    val uiState = vm.uiState
    Column {
        Icon(rememberVectorPainter(FeatherIcons.Trash), contentDescription = null,
            Modifier.align(Alignment.End).padding(end = 10.dp).clickable {
                vm.clearConversations()
            }
        )
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            items(uiState.conversations) {
                ConversationItem(it)
            }
        }
        val imeHeight = WindowInsets.ime.getBottom(LocalDensity.current)
        LaunchedEffect(key1 = uiState.conversations.size, key2 = uiState.conversations.lastOrNull()?.content?.value, key3 = imeHeight) {
            scrollState.animateScrollToItem(uiState.conversations.size - 1, -1)
        }
    }
}

@Composable
fun ConversationItem(it: Conversation) {
    Column(Modifier.fillMaxWidth().wrapContentHeight().padding(20.dp)) {
        Row(Modifier.fillMaxWidth().wrapContentHeight()) {
            val painter = if (it.type == ConversationType.USER) {
                rememberVectorPainter(CommonRes.User)
            } else {
                rememberVectorPainter(CommonRes.Gpt)
            }
            Image(
                painter,
                null,
                Modifier.padding(end = 10.dp).size(20.dp).align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.weight(1f).align(Alignment.CenterVertically)) {
                Text(it.type.displayName)
            }
        }
        Text("${it.content.value}${if (it.isAnswering.value) "●" else ""}", modifier = Modifier.padding(start = 30.dp))
    }
}
