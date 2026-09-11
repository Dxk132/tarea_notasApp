package com.example.tarea_notasapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea_notasapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : NotaDatabaseHelper
    private lateinit var notasAdaptador: NotasAdaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotaDatabaseHelper(this)
        notasAdaptador= NotasAdaptador(db.getAllNotas(),this)
        binding.notasRv.layoutManager = LinearLayoutManager(this)
        binding.notasRv.adapter = notasAdaptador


        binding.FABAgregarNota.setOnClickListener {
            val intent = Intent(this, AgregarNotaActivity::class.java)
            startActivity(intent)
        }
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        notasAdaptador.refrescarlista(db.getAllNotas())
    }
}