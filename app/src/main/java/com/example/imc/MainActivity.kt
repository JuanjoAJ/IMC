package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var height: Double? = null
    private var weight: Double? = null
    private var genre: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener {
            if (binding.height.text.isNotEmpty()) height = binding.height.text.toString().toDouble()
            if (binding.weight.text.isNotEmpty()) weight = binding.weight.text.toString().toDouble()
            genre = findViewById(binding.genre.checkedRadioButtonId)
            if (height == null || weight == null || genre == null) {
                Snackbar.make(binding.root, "Faltan datos por rellenar", Snackbar.LENGTH_SHORT).show()
            }else{
                val intent= Intent(applicationContext, SecondActivity::class.java)
                val bundle=Bundle()
                bundle.putDouble("height", height!!)
                bundle.putDouble("weight", weight!!)
                bundle.putInt("genre",binding.genre.checkedRadioButtonId )
                intent.putExtra("data", bundle)
                startActivity(intent)
            }
        }

    }


}