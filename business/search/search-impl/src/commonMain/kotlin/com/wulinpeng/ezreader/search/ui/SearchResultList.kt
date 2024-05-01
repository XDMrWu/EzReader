package com.wulinpeng.ezreader.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.book_detail.screen.IBookDetailScreen
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.model.EzResult
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 15:31
 * @Description:
 */
@Composable
fun SearchResultList(searchResult: EzResult<List<Book>>, modifier: Modifier) {
    val localNavigator = LocalRootNavigator.current
    when (searchResult) {
        is EzResult.Error -> Text("Error")
        is EzResult.Loading -> {
            Box(Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is EzResult.Success -> {
            Column(modifier.verticalScroll(rememberScrollState())) {
//                items(searchResult.data) {
//                    BookItem(it)
//                }
                searchResult.data.forEach {
                    val detailScreen = koinInject<IBookDetailScreen> {
                        parametersOf(it.bookId)
                    }
                    BookItem(it) {
                        localNavigator.push(detailScreen)
                    }
                }
            }
        }
        EzResult.None -> {}
    }
}

@Composable
fun BookItem(book: Book, onClick: () -> Unit = {}) {
    Row(Modifier.fillMaxWidth().height(100.dp).clickable {
        onClick()
    }.padding(10.dp, 5.dp)) {
        AsyncImage(model = book.image, contentDescription = null, modifier = Modifier.width(50.dp).height(80.dp))
        Column(Modifier.fillMaxHeight().padding(10.dp, 5.dp)) {
            Row {
                Text(book.name, fontSize = 18.sp, modifier = Modifier.align(Alignment.CenterVertically), maxLines = 1)
                Box(Modifier.width(5.dp).height(1.dp).align(Alignment.CenterVertically))
                Text(book.author, fontSize = 15.sp, color = Color.Gray, maxLines = 1,
                    modifier = Modifier.align(Alignment.CenterVertically) )
            }
            Box(Modifier.height(5.dp))
            Text(book.desc?.trim() ?: "",
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis, lineHeight = 15.sp)
        }
    }
}
