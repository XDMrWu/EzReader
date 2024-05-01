package com.wulinpeng.ezreader.book_detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import coil3.compose.AsyncImage
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.book_detail.vm.BookDetailVM
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.model.Chapter
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.MoreHorizontal

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 00:25
 * @Description:
 */
@Composable
fun BookDetailUI(vm: BookDetailVM, book: Book, hasAddToShelf: Boolean) {
    val navController = LocalRootNavigator.current
    Column(Modifier.fillMaxSize().padding(10.dp)) {
        TopBar(navController)
        Column(Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState())) {
            Row(Modifier.fillMaxWidth()
                .height(100.dp)) {
                AsyncImage(model = book.image, contentDescription = null, modifier = Modifier.width(60.dp).height(100.dp))
                Column(Modifier.fillMaxHeight().padding(10.dp, 5.dp)) {
                    Text(book.name, fontSize = 18.sp, maxLines = 1)
                    Text(book.author, fontSize = 15.sp, color = Color.Gray, maxLines = 1)
                    Box(Modifier.height(5.dp))
                }
                if (!book.category.isNullOrEmpty()) {
                    Text(book.category!!, fontSize = 12.sp, color = Color.Gray, maxLines = 1)
                }
            }
            Text("书籍详情")
            Text(book.desc?.trim() ?: "",
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis, lineHeight = 15.sp)
            if (!book.chapterList.isNullOrEmpty()) {
                Text("目录")
                Column {
                }
                book.chapterList!!.forEachIndexed { index, chapter ->
                    Text("${index + 1}、${chapter.title}", fontSize = 15.sp, color = Color.Gray, maxLines = 1)
                }
            }
        }
        BottomBar(vm, book, hasAddToShelf)
    }
}

@Composable
private fun TopBar(navController: Navigator) {
    Row(Modifier.fillMaxWidth()) {
        Icon(
            rememberVectorPainter(FeatherIcons.ArrowLeft),
            contentDescription = null,
            Modifier.align(Alignment.CenterVertically)
                .clickable {
                    navController.pop()
                }
        )
        Box(Modifier.weight(1f).align(Alignment.CenterVertically))
        Icon(
            rememberVectorPainter(FeatherIcons.MoreHorizontal),
            contentDescription = null,
            Modifier.align(Alignment.CenterVertically)
                .clickable {
                    // TODO
                }
        )
    }
}
@Composable
fun BottomBar(vm: BookDetailVM, book: Book, hasAddToShelf: Boolean) {

    @Composable
    fun CustomButton(text: String, color: Color, onClick: () -> Unit) {
        Text(text, fontSize = 15.sp, textAlign = TextAlign.Center,
            modifier = Modifier
                .background(color, RoundedCornerShape(5.dp))
                .clickable {
                    onClick()
                }
                .width(100.dp).padding(top = 10.dp, bottom = 10.dp)
        )
    }

    Row(Modifier.fillMaxWidth().padding(top = 10.dp)) {
        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            if (hasAddToShelf) {
                CustomButton("移除书架", Color(231, 122, 57)) {
                    vm.removeFromShelf(book.bookId)
                }
            } else {
                CustomButton("加入书架", Color(241, 244, 252)) {
                    vm.addToShelf(book)
                }
            }
        }
        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            CustomButton("阅读", Color(90, 219, 235)) {

            }
        }
    }
}