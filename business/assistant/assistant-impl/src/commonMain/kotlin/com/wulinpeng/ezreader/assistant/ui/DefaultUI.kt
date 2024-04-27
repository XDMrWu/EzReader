package com.wulinpeng.ezreader.assistant.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 16:20
 * @Description:
 */
@Composable
fun DefaultScreen(hints: List<DefaultHint>, modifier: Modifier, onClick: (DefaultHint) -> Unit) {
    Column(modifier.fillMaxWidth().wrapContentHeight()) {
        Text("Hi，晚上好",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        Text("我是你的专属小助手，可以帮你寻找想看的小说\n开始和我交流吧\uD83D\uDC47",
            color = Color.Gray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        hints.forEach {
            DefaultHintComponent(it.icon, it.content, it.background, Modifier.align(Alignment.CenterHorizontally), onClick = {
                onClick(it)
            })
        }
    }
}

@Composable
fun DefaultHintComponent(icon: ImageVector, text: String, background: Color, modifier: Modifier, onClick: () -> Unit) {
    Box(modifier.height(60.dp).width(300.dp)
        .padding(5.dp)
        .background(background, RoundedCornerShape(5.dp))
        .clickable {
            onClick()
        }
    ) {
        Row(Modifier.wrapContentSize().align(Alignment.Center)) {
            Icon(rememberVectorPainter(icon), contentDescription = null, Modifier.align(Alignment.CenterVertically))
            Text(text, color = Color.Gray, fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 5.dp)
            )
        }
    }
}