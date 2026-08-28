package edu.ufg.mr100823.ejercicio13_1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.ufg.mr100823.R

class Ejercicio13_1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio13_1)

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val sharedPref = getSharedPreferences("datos_persona", Context.MODE_PRIVATE)
        val correoGuardado = sharedPref.getString("correo", "")
        etCorreo.setText(correoGuardado)

        btnGuardar.setOnClickListener {
            val correo = etCorreo.text.toString()
            
            val editor = sharedPref.edit()
            editor.putString("correo", correo)
            editor.apply()

            Toast.makeText(this, "Correo guardado correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}