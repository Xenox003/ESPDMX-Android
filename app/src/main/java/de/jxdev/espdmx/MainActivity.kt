package de.jxdev.espdmx

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import de.jxdev.espdmx.ui.theme.ESPDMXTheme
import de.jxdev.espdmx.utils.ServiceDiscoveryManager
import de.jxdev.espdmx.utils.WebsocketManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module


val appModule = module {
    single { ServiceDiscoveryManager(this.androidContext() ) }
    single { WebsocketManager(this.androidContext()) }
}

class MainActivity : ComponentActivity() {

    @SuppressLint("BatteryLife")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        Log.d("TEST","onCreate")

        setContent {
            ESPDMXTheme {
                // A surface container using the 'background' color from the them+e

                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(this)
                }
            }
        }

        // Prevent the device from entering idle standby \\
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Fullscreen Lock (Use R API if possible, use FLAG_FULLSCREEN if R API is not available \\
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
            window.insetsController?.hide(WindowInsets.Type.navigationBars())
            window.insetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            window.setDecorFitsSystemWindows(false)
        } else {

            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // Disable Battery Optimisation
        val intent = Intent()
        val packageName = packageName
        val pm = getSystemService(POWER_SERVICE) as PowerManager
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
            intent.data = Uri.parse("package:$packageName")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("TEST","onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("TEST","onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("TEST","onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("TEST","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("TEST","onDestroy")
    }
}