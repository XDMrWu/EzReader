package com.wulinpeng.ezreader.homepage.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.koin.compose.getKoin

/**
 * @Author: wulinpeng
 * @Date: 2024/4/24 22:39
 * @Description:
 */
object HomePageScreen: Screen {
    @Composable
    override fun Content() {
        val koin = getKoin()
        val tabs = remember { koin.getAll<IHomePageTab>().map { it.getVoyagerTab()} }
        TabNavigator(tabs.first()) {
            Scaffold(
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation {
                        tabs.forEach {
                            TabNavigationItem(it)
                        }
                    }
                }
            )
        }
    }

}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Column {
                Icon(tab.options.icon!!, tab.options.title, Modifier.align(Alignment.CenterHorizontally))
                Text(tab.options.title, Modifier.align(Alignment.CenterHorizontally))
            }
        },
    )
}