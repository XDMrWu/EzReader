package com.wulinpeng.ezreader.category.tab

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wulinpeng.ezreader.homepage.screen.IHomePageTab
import compose.icons.FeatherIcons
import compose.icons.feathericons.Grid
import org.koin.core.annotation.Factory

/**
 * @Author: wulinpeng
 * @Date: 2024/4/25 23:15
 * @Description:
 */
@Factory
class CategoryTab: IHomePageTab {
    override fun getVoyagerTab(): Tab {
        return object : Tab {

            override val key: ScreenKey = "CategoryTab"

            @Composable
            override fun Content() {
                Text("分类")
            }

            override val options: TabOptions
                @Composable
                get() {
                    val painter = rememberVectorPainter(FeatherIcons.Grid)
                    return remember {
                        TabOptions(
                            index = 2u,
                            title = "分类",
                            icon = painter
                        )
                    }
                }
        }
    }
}