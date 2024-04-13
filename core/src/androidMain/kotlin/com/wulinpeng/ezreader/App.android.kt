package com.wulinpeng.ezreader

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

internal actual fun openUrl(url: String?) {
    // TODO
//    val uri = url?.let { Uri.parse(it) } ?: return
//    val intent = Intent().apply {
//        action = Intent.ACTION_VIEW
//        data = uri
//        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    }
//    AndroidApp.INSTANCE.startActivity(intent)
}