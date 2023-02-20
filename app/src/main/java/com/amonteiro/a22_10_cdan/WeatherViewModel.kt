package com.amonteiro.a22_10_cdan

import WeatherBean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)
    var data = MutableLiveData<WeatherBean?>()


    fun loadData(cityName : String){
        //Reset de donnée
        //Le post value déclanche l'observer
        errorMessage.postValue("")
        data.postValue(null)
        runInProgress.postValue(true)

        thread {
            try {
                data.postValue(RequestUtils.loadWeather(cityName))
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue("Une erreur est survenue :  ${e.message}")
            }

            runInProgress.postValue(false)
        }
    }
}