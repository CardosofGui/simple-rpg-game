package com.example.stickwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.stickwars.`class`.Classes
import com.example.stickwars.`class`.Personagem
import com.example.stickwars.`class`.Player

class activity_game : AppCompatActivity(), View.OnClickListener {

    lateinit var btnUparAtk: Button
    lateinit var btnUparDef: Button
    lateinit var btnBoss: Button
    lateinit var txtInfo: TextView
    lateinit var txtStas: TextView

    lateinit var Usuario: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnUparAtk = findViewById(R.id.btnUparAtk)
        btnUparDef = findViewById(R.id.btnUparDef)
        btnBoss = findViewById(R.id.btnEnfrentarBoss)
        txtInfo = findViewById(R.id.txtTitulo)
        txtStas = findViewById(R.id.txtStats)

        val classe: String = intent.getStringExtra("personagemSelecionado").toString()
        val nome: String = intent.getStringExtra("nomeUser").toString()

        Usuario = Player("$nome")
        Usuario.setarClasse(classe)

        txtStas.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
        txtInfo.setText("Bem vindo ${Usuario.nome} - Classe ${Usuario.classe}")

        btnUparAtk.setOnClickListener(this)
        btnUparDef.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnUparAtk -> {
                Usuario.evoluirForca()
                txtStas.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
            }
            R.id.btnUparDef -> {
                Usuario.evoluirDefesa()
                txtStas.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
            }
        }
    }
}