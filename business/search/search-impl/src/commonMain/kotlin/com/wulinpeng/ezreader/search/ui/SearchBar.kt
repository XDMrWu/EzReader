package com.wulinpeng.ezreader.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.search.vm.SearchVM
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Search

/**
 * @Author: wulinpeng
 * @Date: 2024/4/29 21:50
 * @Description:
 */
@Composable
fun SearchBar(inputContent: String, onValueChange: (String) -> Unit, onSearch: (String) -> Unit, onBack: () -> Unit) {
    Row {
        Icon(
            rememberVectorPainter(FeatherIcons.ArrowLeft),
            contentDescription = null,
            Modifier.align(Alignment.CenterVertically)
                .padding(10.dp)
                .clickable {
                    onBack()
                }
        )
        TextField(
            inputContent,
            onValueChange,
            Modifier.wrapContentHeight()
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(top = 10.dp, bottom = 10.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    rememberVectorPainter(FeatherIcons.Search),
                    contentDescription = null,
                    Modifier.padding(10.dp)
                )
            },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,),
        )
        Text("搜索", color = Color.Red, modifier = Modifier.align(Alignment.CenterVertically)
            .padding(10.dp)
            .clickable {
                onSearch(inputContent)
            }
        )
    }
}