package com.delarosa.cashcurrency.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
/*    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory*/

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  mainViewModel = viewModelProvider(viewModelFactory)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }
    }
}
