package com.wulinpeng.ezreader.profile.tab

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wulinpeng.ezreader.homepage.screen.IHomePageTab
import compose.icons.FeatherIcons
import compose.icons.feathericons.User
import org.koin.core.annotation.Factory

/**
 * @Author: wulinpeng
 * @Date: 2024/4/25 23:15
 * @Description:
 */
@Factory
class ProfileTab: IHomePageTab {
    override fun getVoyagerTab(): Tab {
        return object : Tab {

            override val key: ScreenKey = "ProfileTab"

            @Composable
            override fun Content() {
                Text("我的")
            }

            override val options: TabOptions
                @Composable
                get() {
                    val painter = rememberVectorPainter(FeatherIcons.User)
                    return remember {
                        TabOptions(
                            index = 3u,
                            title = "我的",
                            icon = painter
                        )
                    }
                }
        }
    }
}