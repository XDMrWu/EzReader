package com.wulinpeng.ezreader.assistant.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wulinpeng.ezreader.assistant.ui.AssistantScreen
import com.wulinpeng.ezreader.assistant.vm.AssistantVM
import com.wulinpeng.ezreader.homepage.screen.IHomePageTab
import compose.icons.FeatherIcons
import compose.icons.feathericons.Star
import org.koin.core.annotation.Factory

/**
 * @Author: wulinpeng
 * @Date: 2024/4/25 23:15
 * @Description:
 */
@Factory
class AssistantTab: IHomePageTab {
    override val index: Int = 2

    override fun getVoyagerTab(): Tab {
        return object : Tab {

            override val key: ScreenKey = "AssistantTab"

            @Composable
            override fun Content() {
                val vm = rememberScreenModel { AssistantVM() }
                AssistantScreen(vm)
            }

            override val options: TabOptions
                @Composable
                get() {
                    val painter = rememberVectorPainter(FeatherIcons.Star)
                    return remember {
                        TabOptions(
                            index = index.toUShort(),
                            title = "AI 助手",
                            icon = painter
                        )
                    }
                }
        }
    }
}