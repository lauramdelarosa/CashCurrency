package com.delarosa.cashcurrency.ui.launcher

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityLauncherBinding
import com.delarosa.cashcurrency.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_launcher.*
import java.util.*
import kotlin.concurrent.schedule


class LauncherActivity : AppCompatActivity() {

    private lateinit var launcherViewModel: LauncherViewModel
    private lateinit var binding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcherViewModel = ViewModelProviders.of(this).get(LauncherViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityLauncherBinding>(this, R.layout.activity_launcher).apply {
            viewModel = launcherViewModel
            lifecycleOwner = this@LauncherActivity
        }

        animBackground()
        launcherViewModel.launchDestinationOutput.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

        launcherViewModel.showMessage.observe(this, Observer {
            showMessage()
        })


        Timer().schedule(2000L) {
            deviceConnected()
        }

    }

    private fun animBackground() {
        background_launcher.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                view: View,
                i: Int,
                i1: Int,
                i2: Int,
                i3: Int,
                i4: Int,
                i5: Int,
                i6: Int,
                i7: Int
            ) {
                view.removeOnLayoutChangeListener(this)
                background_launcher.post(Runnable {

                    val view = background_launcher
                    val finalRadius = Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
                    val anim =
                        ViewAnimationUtils.createCircularReveal(view, view.width / 2, view.height / 2, 0f, finalRadius)
                    anim.interpolator = AccelerateDecelerateInterpolator()
                    anim.duration = 1800
                    view.setBackgroundColor(ContextCompat.getColor(this@LauncherActivity, R.color.colorPrimary))
                    anim.start()

                })

            }
        })


    }

    private fun showMessage() {
        val alertDialog = AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setCancelable(true)
            .setTitle("no tenemos conexion a internet")
            .setMessage("lo sentimos amiguito, no podemos actualizar las monedas hasta que tengamos conexion.")
            .setPositiveButton("ok") { _, _ -> finish() }
        alertDialog.show()
    }

    private fun deviceConnected() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        launcherViewModel.validateConnection(activeNetworkInfo != null && activeNetworkInfo.isConnected)
    }
}
