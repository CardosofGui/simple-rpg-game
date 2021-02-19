package com.example.stickwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.stickwars.`class`.Arqueiro
import com.example.stickwars.`class`.Guerreiro
import com.example.stickwars.`class`.Mago
import com.example.stickwars.`class`.Personagem
import org.w3c.dom.Text

class activity_game : AppCompatActivity() {

    lateinit var btnUparAtk: Button
    lateinit var btnUparDef: Button
    lateinit var btnBoss: Button
    lateinit var txtInfo: TextView
    lateinit var txtStas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnUparAtk = findViewById(R.id.btnUparAtk)
        btnUparDef = findViewById(R.id.btnUparDef)
        btnBoss = findViewById(R.id.btnEnfrentarBoss)
        txtInfo = findViewById(R.id.txtTitulo)
        txtStas = findViewById(R.id.txtStats)

        val classe: String? = intent.getStringExtra("personagemSelecionado")
        val nome: String? = intent.getStringExtra("nomeUser")


    }

}