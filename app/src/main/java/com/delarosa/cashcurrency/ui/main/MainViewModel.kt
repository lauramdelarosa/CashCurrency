package com.delarosa.cashcurrency.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delarosa.cashcurrency.model.Currency
import com.delarosa.cashcurrency.model.SimpleCurrency
import com.delarosa.cashcurrency.util.PreferencesAdapter
import com.delarosa.cashcurrency.util.Util
import com.google.gson.Gson
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val preferencesAdapter: PreferencesAdapter
) : ViewModel() {

    //outputs
    private val _currencyList = MutableLiveData<List<Currency>>()
    val currencyList: LiveData<List<Currency>> get() = _currencyList

    private val _availableText =
        MutableLiveData<String>().apply { value = preferencesAdapter.getPreferenceString(PreferencesAdapter.AVAILABLE) }
    val availableText: LiveData<String> get() = _availableText

    private val _number = MutableLiveData<Unit>()
    val number: LiveData<Unit> get() = _number
    private var numberToConvert = 1.0

    private var selectedCurrencies = listOf("USDEUR", "USDJPY", "USDCOP", "USDGBP", "USDRUB")

    private var completeListCurrency: List<SimpleCurrency> = listOf()

    //input

    init {
        try {
            preferencesAdapter.getPreferenceString(PreferencesAdapter.LIST)?.let {
                val gson = Gson()
                if (it != "") {
                    val text = gson.fromJson(it, Array<String>::class.java).toList()

                    selectedCurrencies = text.toList()
                }


            }
            preferencesAdapter.getPreferenceString(PreferencesAdapter.AVAILABLE)?.let {
                _availableText.postValue(if (it == "") "--" else it)

            }


            completeListCurrency = Util.SIMPLE_DATA
            showCurrencies()
        } catch (e: Exception) {

        }

    }

    //events

    fun convertNumber() = _number.postValue(Unit)
    fun processInformation(number: Double) {
        numberToConvert = number
        showCurrencies()
    }

    private fun showCurrencies() {
        val finalList = arrayListOf<Currency>()
        for (simpleCurrency in completeListCurrency) {
            for (selected in selectedCurrencies) {
                if (selected == simpleCurrency.name.removePrefix("USD")) {
                    finalList.add(
                        Currency(
                            simpleCurrency.name.removePrefix("USD"),
                            simpleCurrency.value,
                            simpleCurrency.value * numberToConvert
                        )
                    )
                }
            }
        }
        _currencyList.value = finalList
    }

}