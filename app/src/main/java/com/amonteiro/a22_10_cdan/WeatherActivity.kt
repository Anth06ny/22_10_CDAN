package com.amonteiro.a22_10_cdan

import RequestUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a22_10_cdan.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //Créer l'IHM. Lazy création à la 1er utilisation
    //Attention à bien mettre le setContentView(binding.root) dans le onCreate
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {

            thread {
                val data = RequestUtils.loadWeather("Toulouse")
                runOnUiThread {
                    binding.tvData.text = "Il fait ${data.main.temp}° à ${data.name}"
                }
            }
            binding.tvData.text = "Chargement en cours..."
        }
    }

}