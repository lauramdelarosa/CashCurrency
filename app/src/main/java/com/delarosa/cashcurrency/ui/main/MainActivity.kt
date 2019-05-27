package com.delarosa.cashcurrency.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.databinding.ActivityMainBinding
import com.delarosa.cashcurrency.model.Currency

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

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

        /*  binding.editTextConvert.addTextChangedListener(object : TextWatcher {
              override fun afterTextChanged(p0: Editable?) {

              }

              override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                  binding.buttonConvert.isEnabled = p0?.length!! > 0
              }

              override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              }
          })*/

    }

    /**
     * when item is clicked
     */
    fun itemClicked(gridItem: Currency) {

    }
}
