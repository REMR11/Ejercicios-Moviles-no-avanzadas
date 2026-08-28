package edu.ufg.mr100823.ejercicio13_2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.ufg.mr100823.R

class Ejercicio13_2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio13_2)

        val etNombre = findViewById<EditText>(R.id.etNombreAgenda)
        val etDatos = findViewById<EditText>(R.id.etDatosAgenda)
        val btnGrabar = findViewById<Button>(R.id.btnGrabarAgenda)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperarAgenda)

        val sharedPref = getSharedPreferences("agenda_personal", Context.MODE_PRIVATE)

        btnGrabar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val datos = etDatos.text.toString()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Debe ingresar un nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editor = sharedPref.edit()
            editor.putString(nombre, datos)
            editor.apply()

            etNombre.setText("")
            etDatos.setText("")
            Toast.makeText(this, "Datos grabados correctamente", Toast.LENGTH_SHORT).show()
        }

        btnRecuperar.setOnClickListener {
            val nombreBuscado = etNombre.text.toString().trim()

            if (nombreBuscado.isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre a buscar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Búsqueda insensible a mayúsculas/minúsculas
            val todasLasEntradas = sharedPref.all
            var encontrado = false

            for ((key, value) in todasLasEntradas) {
                if (key.equals(nombreBuscado, ignoreCase = true)) {
                    etDatos.setText(value.toString())
                    Toast.makeText(this, "Datos recuperados", Toast.LENGTH_SHORT).show()
                    encontrado = true
                    break
                }
            }

            if (!encontrado) {
                etDatos.setText("")
                Toast.makeText(this, "No se encontró información para este nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
