package edu.ufg.mr100823

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ufg.mr100823.adapter.EjercicioAdapter
import edu.ufg.mr100823.ejercicio13_1.Ejercicio13_1Activity
import edu.ufg.mr100823.ejercicio13_2.Ejercicio13_2Activity
import edu.ufg.mr100823.model.Ejercicio

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvEjercicios = findViewById<RecyclerView>(R.id.rvEjercicios)
        val svBuscador = findViewById<SearchView>(R.id.svBuscador)
        
        val listaEjercicios = mutableListOf<Ejercicio>()

        // Registro de ejercicios según el Pipeline
        listaEjercicios.add(Ejercicio("Ejercicio 13.1", "Guardar correo con SharedPreferences", Ejercicio13_1Activity::class.java))
        listaEjercicios.add(Ejercicio("Ejercicio 13.2", "Agenda personal con SharedPreferences", Ejercicio13_2Activity::class.java))
        
        val adapter = EjercicioAdapter(listaEjercicios)
        rvEjercicios.layoutManager = LinearLayoutManager(this)
        rvEjercicios.adapter = adapter

        // Configuración del buscador
        svBuscador.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }
}