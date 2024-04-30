package com.wulinpeng.ezreader.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wulinpeng.ezreader.search.vm.SearchUIState
import com.wulinpeng.ezreader.search.vm.SearchVM
import compose.icons.FeatherIcons
import compose.icons.feathericons.Delete
import compose.icons.feathericons.Trash2

/**
 * @Author: wulinpeng
 * @Date: 2024/4/29 22:03
 * @Description:
 */
@Composable
fun SearchHistoryUI(history: List<String>, onClear: () -> Unit, onSearch: (String) -> Unit) {
    if (history.isEmpty()) {
        return
    }
    Column {
        Row {
            Text("历史搜索", fontSize = 16.sp, modifier = Modifier.padding(start = 5.dp))
            Box(modifier = Modifier.weight(1f))
            Icon(
                imageVector = FeatherIcons.Trash2,
                contentDescription = "delete",
                modifier = Modifier.padding(end = 5.dp).size(20.dp).clickable {
                    onClear()
                }
            )
        }
        SearchFlowLayout(history, Modifier.padding(10.dp)) {
            onSearch(it)
        }
    }
}