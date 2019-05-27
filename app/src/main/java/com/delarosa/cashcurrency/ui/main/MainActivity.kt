package com.delarosa.cashcurrency.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityMainBinding
import com.delarosa.cashcurrency.model.Currency
import com.delarosa.cashcurrency.ui.config.ConfigActivity
import com.delarosa.cashcurrency.util.viewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = viewModelProvider(viewModelFactory)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

        adapter = CurrencyAdapter(this@MainActivity, listOf()) { currency: Currency -> itemClicked(currency) }
        binding.listCurrenciesGrid.adapter = adapter

        mainViewModel.currencyList.observe(this, Observer {
            adapter.currencyList = it ?: listOf()
            adapter.notifyDataSetChanged()
        })

        mainViewModel.number.observe(this, Observer {
            val number = binding.editTextConvert.text.toString()
            mainViewModel.processInformation(number.toDouble())
        })
        binding.settings.setOnClickListener {
            startActivity(Intent(this, ConfigActivity::class.java))
        }
    }

    /**
     * when item is clicked
     */
    fun itemClicked(gridItem: Currency) {
//todo mensaje de elminar moneda y eliminarla
    }
}
