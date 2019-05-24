package com.delarosa.cashcurrency.ui.config

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.delarosa.cashandcurrency.util.viewModelProvider
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityConfigBinding
import javax.inject.Inject

class ConfigActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var configViewModel: ConfigViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configViewModel = viewModelProvider(viewModelFactory)

        val binding = DataBindingUtil.setContentView<ActivityConfigBinding>(this, R.layout.activity_config).apply {
            viewModel = configViewModel
            lifecycleOwner = this@ConfigActivity
        }
    }
}
