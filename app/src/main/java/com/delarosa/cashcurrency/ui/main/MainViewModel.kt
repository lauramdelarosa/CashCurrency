package com.delarosa.cashcurrency.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delarosa.cashcurrency.model.Currency
import com.delarosa.cashcurrency.model.Quotes
import com.delarosa.cashcurrency.model.SimpleCurrency
import com.delarosa.cashcurrency.network.WebAccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(
) : ViewModel() {

    //outputs
    private val _currencyList = MutableLiveData<List<Currency>>()
    val currencyList: LiveData<List<Currency>> get() = _currencyList

    private val _enableButton = MutableLiveData<Boolean>()
    val enableButton: LiveData<Boolean> get() = _enableButton

    private val _number = MutableLiveData<Unit>()
    val number: LiveData<Unit> get() = _number
    private var numberToConvert = 1.0

    private val selectedCurrencies = listOf("USDEUR", "USDJPY", "USDCOP", "USDGBP", "USDRUB")

    private  var completeListCurrency: List<SimpleCurrency> = listOf()

    //input

    init {
        loadPost()
    }

    //events

    private fun loadPost() {
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

    fun convertNumber() = _number.postValue(Unit)
    fun processInformation(number :Double) {
        numberToConvert=number
        showCurrencies()}


    private fun mapData(quotes: Quotes?) {
        quotes?.let {
            completeListCurrency = getCurrencyList(quotes)
            showCurrencies()
        }

    }

    private fun showCurrencies() {
        val finalList = arrayListOf<Currency>()
        for (simpleCurrency in completeListCurrency) {
            for (selected in selectedCurrencies) {
                if (selected == simpleCurrency.name) {
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

    private fun getCurrencyList(quotes: Quotes): List<SimpleCurrency> {

        return arrayListOf(
            SimpleCurrency("USDAED", quotes.USDAED.toDouble()),
            SimpleCurrency("USDAFN", quotes.USDAFN.toDouble()),
            SimpleCurrency("USDALL", quotes.USDALL.toDouble()),
            SimpleCurrency("USDAMD", quotes.USDAMD.toDouble()),
            SimpleCurrency("USDANG", quotes.USDANG.toDouble()),
            SimpleCurrency("USDAOA", quotes.USDAOA.toDouble()),
            SimpleCurrency("USDARS", quotes.USDARS.toDouble()),
            SimpleCurrency("USDAUD", quotes.USDAUD.toDouble()),
            SimpleCurrency("USDAWG", quotes.USDAWG.toDouble()),
            SimpleCurrency("USDAZN", quotes.USDAZN.toDouble()),
            SimpleCurrency("USDBAM", quotes.USDBAM.toDouble()),
            SimpleCurrency("USDBBD", quotes.USDBBD.toDouble()),
            SimpleCurrency("USDBDT", quotes.USDBDT.toDouble()),
            SimpleCurrency("USDBGN", quotes.USDBGN.toDouble()),
            SimpleCurrency("USDBHD", quotes.USDBHD.toDouble()),
            SimpleCurrency("USDBIF", quotes.USDBIF.toDouble()),
            SimpleCurrency("USDBMD", quotes.USDBMD.toDouble()),
            SimpleCurrency("USDBND", quotes.USDBND.toDouble()),
            SimpleCurrency("USDBOB", quotes.USDBOB.toDouble()),
            SimpleCurrency("USDBRL", quotes.USDBRL.toDouble()),
            SimpleCurrency("USDBSD", quotes.USDBSD.toDouble()),
            SimpleCurrency("USDBTC", quotes.USDBTC.toDouble()),
            SimpleCurrency("USDBTN", quotes.USDBTN.toDouble()),
            SimpleCurrency("USDBWP", quotes.USDBWP.toDouble()),
            SimpleCurrency("USDBYN", quotes.USDBYN.toDouble()),
            SimpleCurrency("USDBYR", quotes.USDBYR.toDouble()),
            SimpleCurrency("USDBZD", quotes.USDBZD.toDouble()),
            SimpleCurrency("USDCAD", quotes.USDCAD.toDouble()),
            SimpleCurrency("USDCDF", quotes.USDCDF.toDouble()),
            SimpleCurrency("USDCHF", quotes.USDCHF.toDouble()),
            SimpleCurrency("USDCLF", quotes.USDCLF.toDouble()),
            SimpleCurrency("USDCLP", quotes.USDCLP.toDouble()),
            SimpleCurrency("USDCNY", quotes.USDCNY.toDouble()),
            SimpleCurrency("USDCOP", quotes.USDCOP.toDouble()),
            SimpleCurrency("USDCRC", quotes.USDCRC.toDouble()),
            SimpleCurrency("USDCUC", quotes.USDCUC.toDouble()),
            SimpleCurrency("USDCUP", quotes.USDCUP.toDouble()),
            SimpleCurrency("USDCVE", quotes.USDCVE.toDouble()),
            SimpleCurrency("USDCZK", quotes.USDCZK.toDouble()),
            SimpleCurrency("USDDJF", quotes.USDDJF.toDouble()),
            SimpleCurrency("USDDKK", quotes.USDDKK.toDouble()),
            SimpleCurrency("USDDOP", quotes.USDDOP.toDouble()),
            SimpleCurrency("USDDZD", quotes.USDDZD.toDouble()),
            SimpleCurrency("USDEGP", quotes.USDEGP.toDouble()),
            SimpleCurrency("USDERN", quotes.USDERN.toDouble()),
            SimpleCurrency("USDETB", quotes.USDETB.toDouble()),
            SimpleCurrency("USDEUR", quotes.USDEUR.toDouble()),
            SimpleCurrency("USDFJD", quotes.USDFJD.toDouble()),
            SimpleCurrency("USDFKP", quotes.USDFKP.toDouble()),
            SimpleCurrency("USDGBP", quotes.USDGBP.toDouble()),
            SimpleCurrency("USDGEL", quotes.USDGEL.toDouble()),
            SimpleCurrency("USDGGP", quotes.USDGGP.toDouble()),
            SimpleCurrency("USDGHS", quotes.USDGHS.toDouble()),
            SimpleCurrency("USDGIP", quotes.USDGIP.toDouble()),
            SimpleCurrency("USDGMD", quotes.USDGMD.toDouble()),
            SimpleCurrency("USDGNF", quotes.USDGNF.toDouble()),
            SimpleCurrency("USDGTQ", quotes.USDGTQ.toDouble()),
            SimpleCurrency("USDGYD", quotes.USDGYD.toDouble()),
            SimpleCurrency("USDHKD", quotes.USDHKD.toDouble()),
            SimpleCurrency("USDHNL", quotes.USDHNL.toDouble()),
            SimpleCurrency("USDHRK", quotes.USDHRK.toDouble()),
            SimpleCurrency("USDHTG", quotes.USDHTG.toDouble()),
            SimpleCurrency("USDHUF", quotes.USDHUF.toDouble()),
            SimpleCurrency("USDIDR", quotes.USDIDR.toDouble()),
            SimpleCurrency("USDILS", quotes.USDILS.toDouble()),
            SimpleCurrency("USDIMP", quotes.USDIMP.toDouble()),
            SimpleCurrency("USDINR", quotes.USDINR.toDouble()),
            SimpleCurrency("USDIQD", quotes.USDIQD.toDouble()),
            SimpleCurrency("USDIRR", quotes.USDIRR.toDouble()),
            SimpleCurrency("USDISK", quotes.USDISK.toDouble()),
            SimpleCurrency("USDJEP", quotes.USDJEP.toDouble()),
            SimpleCurrency("USDJMD", quotes.USDJMD.toDouble()),
            SimpleCurrency("USDJOD", quotes.USDJOD.toDouble()),
            SimpleCurrency("USDJPY", quotes.USDJPY.toDouble()),
            SimpleCurrency("USDKES", quotes.USDKES.toDouble()),
            SimpleCurrency("USDKGS", quotes.USDKGS.toDouble()),
            SimpleCurrency("USDKHR", quotes.USDKHR.toDouble()),
            SimpleCurrency("USDKMF", quotes.USDKMF.toDouble()),
            SimpleCurrency("USDKPW", quotes.USDKPW.toDouble()),
            SimpleCurrency("USDKRW", quotes.USDKRW.toDouble()),
            SimpleCurrency("USDKWD", quotes.USDKWD.toDouble()),
            SimpleCurrency("USDKYD", quotes.USDKYD.toDouble()),
            SimpleCurrency("USDKZT", quotes.USDKZT.toDouble()),
            SimpleCurrency("USDLAK", quotes.USDLAK.toDouble()),
            SimpleCurrency("USDLBP", quotes.USDLBP.toDouble()),
            SimpleCurrency("USDLKR", quotes.USDLKR.toDouble()),
            SimpleCurrency("USDLRD", quotes.USDLRD.toDouble()),
            SimpleCurrency("USDLSL", quotes.USDLSL.toDouble()),
            SimpleCurrency("USDLTL", quotes.USDLTL.toDouble()),
            SimpleCurrency("USDLVL", quotes.USDLVL.toDouble()),
            SimpleCurrency("USDLYD", quotes.USDLYD.toDouble()),
            SimpleCurrency("USDMAD", quotes.USDMAD.toDouble()),
            SimpleCurrency("USDMDL", quotes.USDMDL.toDouble()),
            SimpleCurrency("USDMGA", quotes.USDMGA.toDouble()),
            SimpleCurrency("USDMKD", quotes.USDMKD.toDouble()),
            SimpleCurrency("USDMMK", quotes.USDMMK.toDouble()),
            SimpleCurrency("USDMNT", quotes.USDMNT.toDouble()),
            SimpleCurrency("USDMOP", quotes.USDMOP.toDouble()),
            SimpleCurrency("USDMRO", quotes.USDMRO.toDouble()),
            SimpleCurrency("USDMUR", quotes.USDMUR.toDouble()),
            SimpleCurrency("USDMVR", quotes.USDMVR.toDouble()),
            SimpleCurrency("USDMWK", quotes.USDMWK.toDouble()),
            SimpleCurrency("USDMXN", quotes.USDMXN.toDouble()),
            SimpleCurrency("USDMYR", quotes.USDMYR.toDouble()),
            SimpleCurrency("USDMZN", quotes.USDMZN.toDouble()),
            SimpleCurrency("USDNAD", quotes.USDNAD.toDouble()),
            SimpleCurrency("USDNGN", quotes.USDNGN.toDouble()),
            SimpleCurrency("USDNIO", quotes.USDNIO.toDouble()),
            SimpleCurrency("USDNOK", quotes.USDNOK.toDouble()),
            SimpleCurrency("USDNPR", quotes.USDNPR.toDouble()),
            SimpleCurrency("USDNZD", quotes.USDNZD.toDouble()),
            SimpleCurrency("USDOMR", quotes.USDOMR.toDouble()),
            SimpleCurrency("USDPAB", quotes.USDPAB.toDouble()),
            SimpleCurrency("USDPEN", quotes.USDPEN.toDouble()),
            SimpleCurrency("USDPGK", quotes.USDPGK.toDouble()),
            SimpleCurrency("USDPHP", quotes.USDPHP.toDouble()),
            SimpleCurrency("USDPKR", quotes.USDPKR.toDouble()),
            SimpleCurrency("USDPLN", quotes.USDPLN.toDouble()),
            SimpleCurrency("USDPYG", quotes.USDPYG.toDouble()),
            SimpleCurrency("USDQAR", quotes.USDQAR.toDouble()),
            SimpleCurrency("USDRON", quotes.USDRON.toDouble()),
            SimpleCurrency("USDRSD", quotes.USDRSD.toDouble()),
            SimpleCurrency("USDRUB", quotes.USDRUB.toDouble()),
            SimpleCurrency("USDRWF", quotes.USDRWF.toDouble()),
            SimpleCurrency("USDSAR", quotes.USDSAR.toDouble()),
            SimpleCurrency("USDSBD", quotes.USDSBD.toDouble()),
            SimpleCurrency("USDSCR", quotes.USDSCR.toDouble()),
            SimpleCurrency("USDSDG", quotes.USDSDG.toDouble()),
            SimpleCurrency("USDSEK", quotes.USDSEK.toDouble()),
            SimpleCurrency("USDSGD", quotes.USDSGD.toDouble()),
            SimpleCurrency("USDSHP", quotes.USDSHP.toDouble()),
            SimpleCurrency("USDSLL", quotes.USDSLL.toDouble()),
            SimpleCurrency("USDSOS", quotes.USDSOS.toDouble()),
            SimpleCurrency("USDSRD", quotes.USDSRD.toDouble()),
            SimpleCurrency("USDSTD", quotes.USDSTD.toDouble()),
            SimpleCurrency("USDSVC", quotes.USDSVC.toDouble()),
            SimpleCurrency("USDSYP", quotes.USDSYP.toDouble()),
            SimpleCurrency("USDSZL", quotes.USDSZL.toDouble()),
            SimpleCurrency("USDTHB", quotes.USDTHB.toDouble()),
            SimpleCurrency("USDTJS", quotes.USDTJS.toDouble()),
            SimpleCurrency("USDTMT", quotes.USDTMT.toDouble()),
            SimpleCurrency("USDTND", quotes.USDTND.toDouble()),
            SimpleCurrency("USDTOP", quotes.USDTOP.toDouble()),
            SimpleCurrency("USDTRY", quotes.USDTRY.toDouble()),
            SimpleCurrency("USDTTD", quotes.USDTTD.toDouble()),
            SimpleCurrency("USDTWD", quotes.USDTWD.toDouble()),
            SimpleCurrency("USDTZS", quotes.USDTZS.toDouble()),
            SimpleCurrency("USDUAH", quotes.USDUAH.toDouble()),
            SimpleCurrency("USDUGX", quotes.USDUGX.toDouble()),
            SimpleCurrency("USDUSD", quotes.USDUSD.toDouble()),
            SimpleCurrency("USDUYU", quotes.USDUYU.toDouble()),
            SimpleCurrency("USDUZS", quotes.USDUZS.toDouble()),
            SimpleCurrency("USDVEF", quotes.USDVEF.toDouble()),
            SimpleCurrency("USDVND", quotes.USDVND.toDouble()),
            SimpleCurrency("USDVUV", quotes.USDVUV.toDouble()),
            SimpleCurrency("USDWST", quotes.USDWST.toDouble()),
            SimpleCurrency("USDXAF", quotes.USDXAF.toDouble()),
            SimpleCurrency("USDXAG", quotes.USDXAG.toDouble()),
            SimpleCurrency("USDXAU", quotes.USDXAU.toDouble()),
            SimpleCurrency("USDXCD", quotes.USDXCD.toDouble()),
            SimpleCurrency("USDXDR", quotes.USDXDR.toDouble()),
            SimpleCurrency("USDXOF", quotes.USDXOF.toDouble()),
            SimpleCurrency("USDXPF", quotes.USDXPF.toDouble()),
            SimpleCurrency("USDYER", quotes.USDYER.toDouble()),
            SimpleCurrency("USDZAR", quotes.USDZAR.toDouble()),
            SimpleCurrency("USDZMK", quotes.USDZMK.toDouble()),
            SimpleCurrency("USDZMW", quotes.USDZMW.toDouble()),
            SimpleCurrency("USDZWL", quotes.USDZWL.toDouble())
        )

    }

    /*  */
    /**
     * get list of currency using coroutines
     *//*
    private fun loadPost() {
        _currencyList.value= Util.DATA_REDUCED
    }*/
}