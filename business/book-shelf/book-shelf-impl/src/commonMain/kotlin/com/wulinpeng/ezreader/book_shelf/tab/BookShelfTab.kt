package com.wulinpeng.ezreader.book_shelf.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.homepage.screen.IHomePageTab
import com.wulinpeng.ezreader.search.screen.ISearchScreen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Book
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

    override fun getVoyagerTab(): Tab {
        return object : Tab {

            override val key: ScreenKey = "BookShelfTab"

            @Composable
            override fun Content() {
                val searchScreen = koinInject<ISearchScreen>()
                val localRootNavigator = LocalRootNavigator.current
                Column {
                    Text("书架")
                    Button(onClick = {
                        localRootNavigator.push(searchScreen)
                    }) {
                        Text("搜索")
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