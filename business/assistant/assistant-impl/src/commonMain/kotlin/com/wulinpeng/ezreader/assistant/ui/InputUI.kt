package com.wulinpeng.ezreader.assistant.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.wulinpeng.ezreader.appcontext.navigator.LocalBottomBarHeight
import compose.icons.FeatherIcons
import compose.icons.feathericons.Send

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 16:21
 * @Description:
 */
@Composable
fun InputComponent(enable: Boolean, content: String, onValueChange: (String) -> Unit, onSend: (String) -> Unit) {
    val bottomBarHeight = LocalBottomBarHeight.current
    val bottom = with(LocalDensity.current) {
        max(WindowInsets.ime.getBottom(LocalDensity.current).toDp() - bottomBarHeight, 0.dp)
    }
    // 输入框
    Row(Modifier.fillMaxWidth().padding(bottom = bottom).wrapContentHeight()) {
        TextField(
            content,
            onValueChange,
            Modifier.wrapContentHeight()
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(5.dp),
            maxLines = 10,
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,),
        )
        Icon(
            rememberVectorPainter(FeatherIcons.Send),
            contentDescription = null,
            Modifier.align(Alignment.CenterVertically).clickable(enable) {
                onSend(content)
            }.padding(PaddingValues(end = 8.dp))
        )
    }
}