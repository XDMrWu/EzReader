package com.wulinpeng.ezreader

import com.wulinpeng.ezreader.assistant.AssistantModule
import com.wulinpeng.ezreader.book_detail.BookDetailModule
import com.wulinpeng.ezreader.book_shelf.BookShelfModule
import com.wulinpeng.ezreader.discovery.DiscoveryModule
import com.wulinpeng.ezreader.homepage.HomePageModule
import com.wulinpeng.ezreader.network.NetworkModule
import com.wulinpeng.ezreader.profile.ProfileModule
import com.wulinpeng.ezreader.search.SearchModule
import com.wulinpeng.ezreader.storage.StorageModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module


/**
 * @Author: wulinpeng
 * @Date: 2024/4/19 20:12
 * @Description:
 */
@Module([
    HomePageModule::class,
    BookShelfModule::class,
    DiscoveryModule::class,
    AssistantModule::class,
    ProfileModule::class,
    SearchModule::class,
    NetworkModule::class,
    StorageModule::class,
    BookDetailModule::class
])
@ComponentScan("com.wulinpeng.ezreader")
class AppModule