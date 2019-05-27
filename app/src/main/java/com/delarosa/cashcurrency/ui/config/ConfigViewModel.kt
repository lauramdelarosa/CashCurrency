package com.delarosa.cashcurrency.ui.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.delarosa.cashcurrency.model.CurrencySelected
import com.delarosa.cashcurrency.util.PreferencesAdapter
import com.delarosa.cashcurrency.util.Util
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject


class ConfigViewModel @Inject constructor(
    private val preferencesAdapter: PreferencesAdapter

) : ViewModel() {

    var availableTextInput = MediatorLiveData<String>()
    private val _availableTextInput: LiveData<String> get() = availableTextInput

    private val _intentDestinationOutput = MediatorLiveData<Unit>()
    val intentDestinationOutput: LiveData<Unit> get() = _intentDestinationOutput

    private val _stringListOfCurrencies = MediatorLiveData<String>()


    private var _listOfCurrencies = MediatorLiveData<Pair<List<CurrencySelected>, ArrayList<String>>>()
    val listOfCurrencies: LiveData<Pair<List<CurrencySelected>, ArrayList<String>>> get() = _listOfCurrencies

    private var listOfSelectedCurrencies: ArrayList<String> = arrayListOf()

    init {
        try {
            fetchInitialData()
            val listToDisplay = arrayListOf<CurrencySelected>()
            for (list in Util.SIMPLE_DATA) {
                listToDisplay.add(CurrencySelected(list.name.removePrefix("USD"), false))
            }
            _listOfCurrencies.postValue(Pair(listToDisplay, listOfSelectedCurrencies))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun fetchInitialData() {
        preferencesAdapter.getPreferenceString(PreferencesAdapter.LIST)?.let {
            val gson = Gson()

            if (it != "") {
                val text = gson.fromJson(it, Array<String>::class.java).toList()
                listOfSelectedCurrencies = if (text.size == 1)
                    arrayListOf(text[0])
                else
                    text as ArrayList<String>
            }


        }
        preferencesAdapter.getPreferenceString(PreferencesAdapter.AVAILABLE)?.let {
            availableTextInput.value = it
        }
    }

    fun getListOfCurrencies(list: List<String>) {
        val gson = Gson()
        val jsonText = gson.toJson(list)
        _stringListOfCurrencies.value = jsonText
    }

    fun validateData() {
        preferencesAdapter.setPreferenceString(
            PreferencesAdapter.AVAILABLE,
            _availableTextInput.value.toString()
        )

        preferencesAdapter.setPreferenceString(
            PreferencesAdapter.LIST, _stringListOfCurrencies.value.toString()
        )
        _intentDestinationOutput.postValue(Unit)
    }


}

