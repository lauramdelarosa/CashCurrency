package com.delarosa.cashcurrency.ui.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LauncherViewModel @Inject constructor(

) : ViewModel() {

    // Outputs
    private val _launchDestinationOutput = MediatorLiveData<Unit>()
    val launchDestinationOutput: LiveData<Unit> get() = _launchDestinationOutput

    private val _showMessage = MediatorLiveData<Unit>()
    val showMessage: LiveData<Unit> get() = _showMessage


    /**
     * validate internet conection, is if good fetch the latest data of the api if is no see if the db is empty.
     */
    fun validateConnection(deviceConnected: Boolean) {
        if (deviceConnected) {
            fetchData()
            _launchDestinationOutput.postValue(Unit)
        } else {
            if (emptyDatabase()) {
                _showMessage.postValue(Unit)
            } else {
                _launchDestinationOutput.postValue(Unit)
            }
        }

    }

    private fun fetchData() {

    }

    private fun emptyDatabase(): Boolean {
        return false

    }

}