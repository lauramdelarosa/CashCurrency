package com.delarosa.cashcurrency.ui.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.delarosa.cashcurrency.model.Quotes
import com.delarosa.cashcurrency.network.WebAccess
import com.delarosa.cashcurrency.result.Event
import com.delarosa.cashcurrency.util.PreferencesAdapter
import com.delarosa.cashcurrency.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class LauncherViewModel @Inject constructor(
    private val preferencesAdapter: PreferencesAdapter
) : ViewModel() {

    // Outputs
    private val _launchDestinationOutput = MediatorLiveData<Event<LauncherDestination>>()
    val launchDestinationOutput: LiveData<Event<LauncherDestination>> get() = _launchDestinationOutput

    private val _showMessage = MediatorLiveData<Unit>()
    val showMessage: LiveData<Unit> get() = _showMessage


    /**
     * validate internet conection, is if good fetch the latest data of the api if is no see if the db is empty.
     */
    fun validateConnection(deviceConnected: Boolean) {
        if (deviceConnected) {
            fetchData()

        } else {
            if (emptyDatabase()) {
                _showMessage.postValue(Unit)
            } else {
                _launchDestinationOutput.postValue(getDestination())
            }
        }

    }

    private fun fetchData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val webResponse = WebAccess.API.currency().await()
                if (webResponse.isSuccessful) {
                    mapData(webResponse.body()?.quotes)
                }
            } catch (e: IOException) {
            }
        }
    }

    private fun mapData(quotes: Quotes?) {
        quotes?.let {
            Util.SIMPLE_DATA = Util.getCurrencyList(quotes)
        }
        _launchDestinationOutput.postValue(getDestination())
    }

    private fun emptyDatabase(): Boolean {
        return true

    }

    private fun getDestination(): Event<LauncherDestination>? {
        return if (preferencesAdapter.getPreferenceBoolean(PreferencesAdapter.CONFIG_FIRST_TIME))
            Event(LauncherDestination.MAIN_ACTIVITY)
        else
            Event(LauncherDestination.CONFIG_ACTIVITY)

    }

    fun updatePreferenceConfigFirstTime() {
        preferencesAdapter.setPreferenceBoolean(PreferencesAdapter.CONFIG_FIRST_TIME, true)
    }

    enum class LauncherDestination {
        CONFIG_ACTIVITY,
        MAIN_ACTIVITY
    }
}