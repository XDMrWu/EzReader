package com.wulinpeng.ezreader.book_shelf.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateIntSizeAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.book_shelf.ui.BookHistoryScreen
import com.wulinpeng.ezreader.book_shelf.ui.BookShelfScreen
import com.wulinpeng.ezreader.book_shelf.vm.BookHistoryVM
import com.wulinpeng.ezreader.book_shelf.vm.BookShelfMode
import com.wulinpeng.ezreader.book_shelf.vm.BookShelfVM
import com.wulinpeng.ezreader.homepage.screen.IHomePageTab
import com.wulinpeng.ezreader.search.screen.ISearchScreen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Book
import compose.icons.feathericons.Grid
import compose.icons.feathericons.List
import compose.icons.feathericons.MoreHorizontal
import compose.icons.feathericons.Search
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.annotation.Factory

/**
 * @Author: wulinpeng
 * @Date: 2024/4/25 23:15
 * @Description:
 */
@Factory
class BookShelfTab: IHomePageTab {
    override val index: Int = 0

    @OptIn(ExperimentalFoundationApi::class)
    override fun getVoyagerTab(): Tab {
        return object : Tab {

            override val key: ScreenKey = "BookShelfTab"

            @Composable
            override fun Content() {
                val pagerState = rememberPagerState { 2 }
                val searchScreen = koinInject<ISearchScreen>()
                val scope = rememberCoroutineScope()
                val localRootNavigator = LocalRootNavigator.current
                val bookShelfVM = rememberScreenModel { BookShelfVM() }
                val bookHistoryVM = rememberScreenModel { BookHistoryVM() }
                var showPopUp by remember { mutableStateOf(false) }
                var popUpOffset by remember { mutableStateOf(IntOffset(0, 0)) }
                Column(Modifier.fillMaxSize()) {
                    Row(Modifier.fillMaxWidth().wrapContentHeight()
                        .background(Color(241, 244, 252))
                        .padding(top = 30.dp)
                    ) {
                        @Composable
                        fun CustomTextTab(index: Int, title: String) {
                            val textSize by animateIntAsState(if (pagerState.currentPage == index) 20 else 16)
                            val textColor by animateColorAsState(if (pagerState.currentPage == index) Color.Black else Color.Gray)
                            Text(title, fontSize = textSize.sp, color = textColor, modifier = Modifier.clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }.padding(5.dp))
                        }
                        CustomTextTab(0, "书架")
                        CustomTextTab(1, "浏览历史")
                        Box(Modifier.weight(1f).height(1.dp))
                        Icon(
                            rememberVectorPainter(FeatherIcons.Search),
                            contentDescription = null,
                            Modifier.clickable {
                                localRootNavigator.push(searchScreen)
                            }.padding(10.dp)
                        )
                        Icon(
                            rememberVectorPainter(FeatherIcons.MoreHorizontal),
                            contentDescription = null,
                            Modifier.clickable {
                                showPopUp = true
                            }.onGloballyPositioned {
                                popUpOffset = IntOffset(it.positionInRoot().x.toInt(), it.positionInRoot().y.toInt() + it.size.height)
                            }.padding(10.dp)
                        )
                    }
                    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().weight(1f)) { page ->
                        when (page) {
                            0 -> {
                                BookShelfScreen(bookShelfVM)
                            }
                            1 -> {
                                BookHistoryScreen(bookHistoryVM)
                            }
                        }
                    }
                }
                if (showPopUp) {
                    Popup(offset = popUpOffset, properties = PopupProperties(focusable = true), onDismissRequest = {showPopUp = false }) {
                        Column(Modifier.background(Color.White)
                            .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                            .padding(5.dp)
                        ) {
                            Row(Modifier.clickable {
                                bookShelfVM.changeMode(BookShelfMode.List)
                                showPopUp = false
                            }.padding(0.dp, 5.dp)) {
                                Icon(
                                    rememberVectorPainter(FeatherIcons.List),
                                    contentDescription = null,
                                    Modifier.padding(end = 5.dp).align(Alignment.CenterVertically)
                                )
                                Text("列表模式", Modifier.align(Alignment.CenterVertically))
                            }
                            Row(Modifier.clickable {
                                bookShelfVM.changeMode(BookShelfMode.Grid)
                                showPopUp = false
                            }.padding(0.dp, 5.dp)) {
                                Icon(
                                    rememberVectorPainter(FeatherIcons.Grid),
                                    contentDescription = null,
                                    Modifier.padding(end = 5.dp).align(Alignment.CenterVertically)
                                )
                                Text("宫格模式", Modifier.align(Alignment.CenterVertically))
                            }
                        }
                    }
                }
            }

            override val options: TabOptions
                @Composable
                get() {
                    val painter = rememberVectorPainter(FeatherIcons.Book)
                    return remember {
                        TabOptions(
                            index = index.toUShort(),
                            title = "书架",
                            icon = painter
                        )
                    }
                }
        }
    }
}