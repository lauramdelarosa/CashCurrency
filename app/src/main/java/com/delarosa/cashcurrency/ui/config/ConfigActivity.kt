package com.delarosa.cashcurrency.ui.config

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityConfigBinding
import com.delarosa.cashcurrency.ui.main.MainActivity
import com.delarosa.cashcurrency.util.viewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class ConfigActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding

    private lateinit var configViewModel: ConfigViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter: SpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configViewModel = viewModelProvider(viewModelFactory)

        binding = DataBindingUtil.setContentView<ActivityConfigBinding>(
            this,
            R.layout.activity_config
        ).apply {
            viewModel = configViewModel
            lifecycleOwner = this@ConfigActivity
        }
        configViewModel.intentDestinationOutput.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })



        configViewModel.listOfCurrencies.observe(this, Observer {
            adapter = SpinnerAdapter(this, it.first, it.second) { string: List<String> -> itemClicked(string) }
            binding.spinnerCurrency.adapter = adapter
        })




    }

    /**
     * when item is clicked
     */
    private fun itemClicked(list: List<String>) {
        configViewModel.getListOfCurrencies(list)
    }
}
