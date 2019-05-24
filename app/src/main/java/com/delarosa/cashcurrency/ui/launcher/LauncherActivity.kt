package com.delarosa.cashcurrency.ui.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.delarosa.cashandcurrency.util.viewModelProvider
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityLauncherBinding
import javax.inject.Inject

class LauncherActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var launcherViewModel: LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcherViewModel = viewModelProvider(viewModelFactory)

        val binding = DataBindingUtil.setContentView<ActivityLauncherBinding>(this, R.layout.activity_launcher).apply {
            viewModel = launcherViewModel
            lifecycleOwner = this@LauncherActivity
        }
    }
}
