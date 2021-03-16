package com.example.rpgclicker.features

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rpgclicker.R
import com.example.rpgclicker.base.BaseActivity
import com.example.rpgclicker.features.telaInicial.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class bottom_navigation : BaseActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var adicionarPreferences: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        setupToolbar(toolbar, "Teste")

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.activity_stats,
            R.id.activity_upAtk,
            R.id.activity_upDef,
            R.id.activity_bossFight
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)


        bottomNavigation.setupWithNavController(navController)
    }

    // Criar menu de items na toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemSair -> {
                sharedPreferences = getSharedPreferences("Dados", Context.MODE_PRIVATE)
                adicionarPreferences = sharedPreferences.edit()

                adicionarPreferences.putBoolean("UsuarioLogado", false)
                adicionarPreferences.apply()

                val intent = Intent(this,  MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}