package com.wulinpeng.ezreader.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author: wulinpeng
 * @Date: 2024/4/29 22:09
 * @Description:
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchFlowLayout(keys: List<String>, modifier: Modifier, onClick: (String) -> Unit) {
    FlowRow(modifier) {
        keys.forEach {
            Card(Modifier.padding(5.dp).clickable { onClick(it) }, shape = RoundedCornerShape(20.dp)) {
                Text(it, fontSize = 12.sp, modifier = Modifier.padding(start = 5.dp, end = 5.dp))
            }
        }
    }
}