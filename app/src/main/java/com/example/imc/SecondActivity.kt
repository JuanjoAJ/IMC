package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.databinding.ActivitySecondBinding
import kotlin.math.pow

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var bundleRecuperado: Bundle? = null
    private lateinit var selectRadio: RadioButton
    private var height = 0.0
    private var weight = 0.0
    private var genre = ""
    private val statesImage = arrayListOf(
        R.drawable.estado1,
        R.drawable.estado2,
        R.drawable.estado3,
        R.drawable.estado4,
        R.drawable.estado5,
        R.drawable.estado6
    )
    private val statesText = arrayListOf(
        R.string.low_weight,
        R.string.normal_weight,
        R.string.pre_obesity,
        R.string.obesity_I,
        R.string.obesity_II,
        R.string.obesity_III
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bundleRecuperado = intent.extras?.getBundle("data")
        height = bundleRecuperado?.getDouble("height")!!
        weight = bundleRecuperado?.getDouble("weight")!!
        selectRadio = binding.genre.findViewById(bundleRecuperado?.getInt("genre")!!)
        genre= selectRadio.text.toString()
        val imcFormula: (Double, Double) -> Double = { height, weight -> weight / height.pow(2) }
        printScreen(imcFormula(height, weight))

        binding.btnReboot.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun imc(imcFormula: Double):Int {

        if (genre == "Masculino") {
            return if (imcFormula < 18.5) 0
            else if (imcFormula <= 24.9) 1
            else if (imcFormula <= 29.9) 2
            else if (imcFormula <= 34.9) 3
            else if (imcFormula <= 39.9) 4
            else 5
        } else {
            when (imcFormula) {
                in 0.0..16.4 -> return 0

                in 16.5..22.9 -> return 1

                in 23.0 .. 25.9 -> return 2

                in 26.0..30.9 -> return 3

                in 31.0..33.9 -> return 4

                in 34.0..Double.MAX_VALUE -> return 5
            }
        }
        return -1
    }

    private fun printScreen(imcFormula:Double){
        binding.txtImc.text = String.format("%.2f", imcFormula)
        binding.txtState.text=getString(statesText[imc(imcFormula)])
        binding.imageState.background=getDrawable(statesImage[imc(imcFormula)])
        binding.height.hint=height.toString()
        binding.weight.hint=weight.toString()
        selectRadio.isChecked=true
    }
}