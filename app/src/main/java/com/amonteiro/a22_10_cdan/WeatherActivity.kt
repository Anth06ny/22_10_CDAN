package com.amonteiro.a22_10_cdan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_10_cdan.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //Créer l'IHM. Lazy création à la 1er utilisation
    //Attention à bien mettre le setContentView(binding.root) dans le onCreate
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        refreshScreen()


        binding.btLoad.setOnClickListener {

            //Affiche la progressBar
            binding.progressBar.isVisible = true

            thread {
                model.loadData("Toulouse")
                runOnUiThread {
                    refreshScreen()
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    fun refreshScreen() {
        binding.tvData.text = "Il fait ${model.data?.main?.temp ?: "-"}° à ${model.data?.name ?: "-"}"

        if (model.errorMessage.isNotBlank()) {
            binding.tvError.text = model.errorMessage
            binding.tvError.isVisible = true
        } else {
            binding.tvError.isVisible = false
        }
    }

}