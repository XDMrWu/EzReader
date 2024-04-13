import androidx.compose.ui.window.ComposeUIViewController
import com.wulinpeng.ezreader.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
