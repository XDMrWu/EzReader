package com.wulinpeng.ezreader.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author: wulinpeng
 * @Date: 2024/4/29 22:03
 * @Description:
 */
@Composable
fun SearchHotKeysUI(hotKeys: List<String>, onSearch: (String) -> Unit) {
    if (hotKeys.isEmpty()) {
        return
    }
    Column {
        Text("推荐搜索", fontSize = 16.sp, modifier = Modifier.padding(start = 5.dp))
        SearchFlowLayout(hotKeys, Modifier.padding(10.dp)) {
            onSearch(it)
        }
    }
}