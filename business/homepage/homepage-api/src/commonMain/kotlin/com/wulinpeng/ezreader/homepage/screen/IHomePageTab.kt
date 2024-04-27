package com.wulinpeng.ezreader.homepage.screen

import cafe.adriel.voyager.navigator.tab.Tab

/**
 * @Author: wulinpeng
 * @Date: 2024/4/25 23:05
 * @Description:
 */
interface IHomePageTab {

    val index: Int
    fun getVoyagerTab(): Tab
}