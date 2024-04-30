import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.wulinpeng.ezreader.App
import com.wulinpeng.ezreader.com.wulinpeng.ezreader.AppDelegate
import com.wulinpeng.ezreader.launcher.EzPlatformContext
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController(configure = {
    onFocusBehavior = OnFocusBehavior.DoNothing
}) {
    App()
}
fun onAppCreate() = AppDelegate.onCreate(EzPlatformContext())
