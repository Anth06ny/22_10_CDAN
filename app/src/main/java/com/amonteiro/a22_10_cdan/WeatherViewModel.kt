package com.amonteiro.a22_10_cdan

import WeatherBean
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    var errorMessage = ""
    var data :WeatherBean? = null

    fun loadData(cityName : String){
        //Reset de donnée
        errorMessage = ""
        data = null

        try {
            data = RequestUtils.loadWeather(cityName)
        }
        catch(e:Exception) {
            e.printStackTrace()
            errorMessage = "Une erreur est survenue :  ${e.message}"
        }
    }
}