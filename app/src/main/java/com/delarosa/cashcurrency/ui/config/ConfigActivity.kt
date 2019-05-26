package com.delarosa.cashcurrency.ui.config

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityConfigBinding
import javax.inject.Inject

class ConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding
    private lateinit var configViewModel: ConfigViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configViewModel = ViewModelProviders.of(this).get(ConfigViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityConfigBinding>(this, R.layout.activity_config).apply {
            viewModel = configViewModel
            lifecycleOwner = this@ConfigActivity
        }
    }
}
