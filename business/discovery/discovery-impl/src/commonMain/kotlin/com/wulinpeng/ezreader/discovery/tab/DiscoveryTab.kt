package com.wulinpeng.ezreader.discovery.tab

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wulinpeng.ezreader.homepage.screen.IHomePageTab
import compose.icons.FeatherIcons
import compose.icons.feathericons.Compass
import org.koin.core.annotation.Factory

/**
 * @Author: wulinpeng
 * @Date: 2024/4/25 23:15
 * @Description:
 */
@Factory
class BookShopTab: IHomePageTab {
    override fun getVoyagerTab(): Tab {
        return object : Tab {

            override val key: ScreenKey = "BookShopTab"

            @Composable
            override fun Content() {
                Text("发现")
            }

            override val options: TabOptions
                @Composable
                get() {
                    val painter = rememberVectorPainter(FeatherIcons.Compass)
                    return remember {
                        TabOptions(
                            index = 1u,
                            title = "发现",
                            icon = painter
                        )
                    }
                }
        }
    }
}