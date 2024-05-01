package com.wulinpeng.ezreader.book_shelf.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil3.compose.AsyncImage
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.book_shelf.vm.BookShelfMode
import com.wulinpeng.ezreader.book_shelf.vm.BookShelfUIState
import com.wulinpeng.ezreader.book_shelf.vm.BookShelfVM
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.search.screen.ISearchScreen
import compose.icons.FeatherIcons
import compose.icons.feathericons.MoreHorizontal
import compose.icons.feathericons.Plus
import org.koin.compose.koinInject

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 17:47
 * @Description:
 */
@Composable
fun BookShelfScreen(vm: BookShelfVM) {
    val uiState by vm.uiStateFlow.collectAsState(BookShelfUIState())
    val localNavigator = LocalRootNavigator.current
    val searchScreen = koinInject<ISearchScreen>()
    when (uiState.mode) {
        BookShelfMode.List -> {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                uiState.bookList.forEach {
                    BookListItem(it, {
                        // TODO 跳转阅读页面
                    }) {
                        vm.removeFromShelf(it)
                    }
                }
                AddBookListItem{
                    localNavigator.push(searchScreen)
                }
            }
        }
        BookShelfMode.Grid -> {
            LazyVerticalGrid(GridCells.Fixed(3), Modifier.fillMaxSize()) {
                items(uiState.bookList) {
                    BookGridItem(it, {
                        // TODO 跳转阅读页面
                    }) {
                        vm.removeFromShelf(it)
                    }
                }
                item {
                    AddBookGridItem {
                        localNavigator.push(searchScreen)
                    }
                }
            }
        }
    }
}


@Composable
fun BookListItem(book: Book, onClick: () -> Unit, onRemoveFromShelf: () -> Unit) {
    var showPopUp by remember { mutableStateOf(false) }
    var popUpOffset by remember { mutableStateOf(IntOffset(0, 0)) }
    Row(Modifier.fillMaxWidth().height(100.dp).clickable {
        onClick()
    }.padding(10.dp, 5.dp)) {
        AsyncImage(model = book.image, contentDescription = null, modifier = Modifier.width(50.dp).height(80.dp))
        Column(Modifier.fillMaxHeight().padding(10.dp, 5.dp)) {
            Row {
                Text(book.name, fontSize = 18.sp, maxLines = 1)
                Box(Modifier.weight(1f))
                Icon(
                    rememberVectorPainter(FeatherIcons.MoreHorizontal),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.onGloballyPositioned {
                        popUpOffset = IntOffset(it.positionInRoot().x.toInt(), it.positionInRoot().y.toInt() + it.size.height)
                    }.size(15.dp).clickable {
                        showPopUp = true
                    }
                )
            }
            Text(book.author, fontSize = 15.sp, color = Color.Gray, maxLines = 1)
            Row {
                if (!book.lastUpdateTime.isNullOrEmpty()) {
                    Text(book.lastUpdateTime!!, fontSize = 15.sp, color = Color.Gray, maxLines = 1,
                        modifier = Modifier.align(Alignment.CenterVertically) )
                }
                if (!book.lastUpdateChapter.isNullOrEmpty()) {
                    Text(book.lastUpdateChapter!!, fontSize = 15.sp, color = Color.Gray, maxLines = 1,
                        modifier = Modifier.align(Alignment.CenterVertically).padding(start = 10.dp) )
                }
            }
        }
    }
    if (showPopUp) {
        Popup(
            offset = popUpOffset,
            properties = PopupProperties(focusable = true),
            onDismissRequest = { showPopUp = false }) {
            Column(
                Modifier.background(Color.White)
                    .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                    .clickable {
                        onRemoveFromShelf()
                    }
                    .padding(5.dp)
            ) {
                Text("移除书架")
            }
        }
    }
}

@Composable
fun AddBookListItem(onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().height(100.dp).clickable {
        onClick()
    }.padding(10.dp, 5.dp)) {
        Box(modifier = Modifier.width(50.dp).height(80.dp).align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                rememberVectorPainter(FeatherIcons.Plus),
                contentDescription = null,
                tint = Color.Gray,
            )
        }
        Text("添加作品", fontSize = 15.sp, color = Color.Gray, maxLines = 1, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookGridItem(book: Book, onClick: () -> Unit, onRemoveFromShelf: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth().height(120.dp).combinedClickable(
        onClick = onClick,
        onLongClick = {
            showDialog = true
        }
    ).padding(10.dp, 5.dp)) {
        AsyncImage(model = book.image, contentDescription = null, modifier = Modifier.width(60.dp).height(80.dp).align(Alignment.CenterHorizontally))
        Text(book.name, fontSize = 15.sp, maxLines = 1, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
    if (showDialog) {
        Dialog(properties = DialogProperties(), onDismissRequest = { showDialog = false }) {
            Column(Modifier.clickable {
                onRemoveFromShelf()
                showDialog = false
            }.background(Color.White).padding(20.dp, 10.dp)) {
                Text("移除书架")
            }
        }
    }
}

@Composable
fun AddBookGridItem(onClick: () -> Unit) {
    Column(Modifier.fillMaxWidth().height(120.dp).clickable {
        onClick()
    }.padding(10.dp, 5.dp)) {
        Box(modifier = Modifier.width(60.dp).height(80.dp).align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center) {
            Icon(
                rememberVectorPainter(FeatherIcons.Plus),
                contentDescription = null,
                tint = Color.Gray,
            )
        }
        Text("", fontSize = 15.sp, maxLines = 1, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}
