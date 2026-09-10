package com.example.tarea_notasapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tarea_notasapp.databinding.ActivityAgregarNotaBinding

class AgregarNotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarNotaBinding
    private lateinit var db: NotaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        db = NotaDatabaseHelper(this)

        binding.ivGuardarNota.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            
            if (titulo.isNotEmpty() && descripcion.isNotEmpty()) {
                val nota = Nota(0, titulo, descripcion)
                db.insertNota(nota)
                finish()
                Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor llene los campos", Toast.LENGTH_SHORT).show()
            }
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
