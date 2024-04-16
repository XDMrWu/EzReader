import androidx.compose.ui.window.ComposeUIViewController
import com.wulinpeng.ezreader.App
import com.wulinpeng.ezreader.com.wulinpeng.ezreader.AppDelegate
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
fun onAppCreate() = AppDelegate.onCreate()
