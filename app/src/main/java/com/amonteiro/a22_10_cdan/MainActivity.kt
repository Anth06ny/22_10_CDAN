package com.amonteiro.a22_10_cdan

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a22_10_cdan.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

const val MENU_ITEM_TP = 18
const val MENU_ITEM_DP = 19
const val MENU_ITEM_AD = 56

val SDF = SimpleDateFormat("dd/MM/yyyy HH:mm")

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    //Créer l'IHM. Lazy création à la 1er utilisation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //Data
    var calendar = Calendar.getInstance()

    //Surcharge de onCreate de AppCompatActivity
    //override indique que la méthode est une surcharge
    //Création de l'écran
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //On affiche l'IHM qu'on a créé
        setContentView(binding.root)

        //Action à faire sur le clic du bouton d'idbtValidate
        //Id déféinit dans le XML
        binding.btValidate.setOnClickListener {
            if (binding.rbLike.isChecked) {
                binding.et.setText(binding.rbLike.text)
            } else if (binding.rbDislike.isChecked) {
                binding.et.setText(binding.rbDislike.text)
            }
            binding.iv.setImageResource(R.drawable.ic_baseline_flag_24)

            binding.iv.setColorFilter(Color.MAGENTA)
        }

        binding.btCancel.setOnClickListener {
            binding.et.setText("")
            binding.rg.clearCheck()
            binding.iv.setImageResource(R.drawable.ic_baseline_delete_forever_24)
        }
    }

    //Callback de la création de menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_ITEM_TP, 0, "TimePicker")
        menu.add(0, MENU_ITEM_DP, 0, "DatePicker")
        menu.add(0, MENU_ITEM_AD, 0, "AlertDialog")
        return super.onCreateOptionsMenu(menu)
    }

    //Callback des clics sur les menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_ITEM_TP) {
            //Création de la dialogue du TimePicker.
            //This : envoie la référence de la classe courante.
            //C'est donc la classe courante (MainActivity) qui implémente l'interface de gestion du TimePicker (TimePickerDialog.OnTimeSetListener) qui génère la
            // méthode onTimeSet
            TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        return super.onOptionsItemSelected(item)
    }

    //CallBack du TimePicker
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.MINUTE , minute)
        calendar.set(Calendar.HOUR_OF_DAY , hourOfDay)
        Toast.makeText(this, SDF.format(calendar.time), Toast.LENGTH_LONG ).show()
    }


}