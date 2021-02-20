package com.example.stickwars

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.stickwars.`class`.*
import kotlin.random.Random

class activity_game : AppCompatActivity(), View.OnClickListener {

    lateinit var btnUparAtk: Button
    lateinit var btnUparDef: Button
    lateinit var btnBoss: Button
    lateinit var txtInfo: TextView
    lateinit var txtStats: TextView
    lateinit var imgClass: ImageView

    lateinit var Usuario: Player
    lateinit var Chefao: Boss

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnUparAtk = findViewById(R.id.btnUparAtk)
        btnUparDef = findViewById(R.id.btnUparDef)
        btnBoss = findViewById(R.id.btnEnfrentarBoss)
        txtInfo = findViewById(R.id.txtTitulo)
        txtStats = findViewById(R.id.txtStats)
        imgClass = findViewById(R.id.imgClass)

        val classe: String = intent.getStringExtra("personagemSelecionado").toString()
        val nome: String = intent.getStringExtra("nomeUser").toString()

        Usuario = Player("$nome")
        Chefao = Boss(Chefoes.Chef1.nomeChefao, Chefoes.Chef1.atkStats, Chefoes.Chef1.defStats, Chefoes.Chef1.expTotal, Chefoes.Chef1.nivelBoss, Chefoes.Chef1.derrotado)

        Usuario.setarClasse(classe, imgClass)

        txtStats.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
        txtInfo.setText("Bem vindo ${Usuario.nome} - Classe ${Usuario.classe}")

        btnUparAtk.setOnClickListener(this)
        btnUparDef.setOnClickListener(this)
        btnBoss.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnUparAtk -> {
                Usuario.evoluirForca()
                txtStats.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
            }
            R.id.btnUparDef -> {
                Usuario.evoluirDefesa()
                txtStats.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
            }
            R.id.btnEnfrentarBoss -> {
                Usuario.combateBoss(Chefao, baseContext)
            }
        }
    }
}