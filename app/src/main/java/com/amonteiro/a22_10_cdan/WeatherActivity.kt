package com.amonteiro.a22_10_cdan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_10_cdan.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    //Créer l'IHM. Lazy création à la 1er utilisation
    //Attention à bien mettre le setContentView(binding.root) dans le onCreate
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Abonnement au live data
        observe()

        binding.btLoad.setOnClickListener {
            model.loadData("Toulouse")
        }
    }

    fun observe() {
        //Chaque fois qu'on fait un postValue déclanche l'observer
        //Idem à l'abonnement de l'observation
        model.errorMessage.observe(this) {
            if (it.isNotBlank()) {
                binding.tvError.text = it
                binding.tvError.isVisible = true
            } else {
                binding.tvError.isVisible = false
            }
        }

        model.data.observe(this) {
            binding.tvData.text = "Il fait ${it?.main?.temp ?: "-"}° à ${it?.name ?: "-"}"
        }

        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }
    }
}