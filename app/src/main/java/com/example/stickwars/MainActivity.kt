package com.example.stickwars

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var btnIniciar : Button
    lateinit var edtUsuario : EditText
    lateinit var spnPersonagem : Spinner
    lateinit var personagemSelecionado : String

    lateinit var sharedPreferences: SharedPreferences
    lateinit var adicionarPreferences: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando variaveis e identificiando seus IDs
        btnIniciar = findViewById(R.id.btnIniciar)
        edtUsuario = findViewById(R.id.edtUsuario)
        spnPersonagem = findViewById(R.id.spnPersonagem)

        sharedPreferences = getSharedPreferences("Dados", Context.MODE_PRIVATE)
        adicionarPreferences = sharedPreferences.edit()


        if(sharedPreferences.getBoolean("UsuarioLogado", false)){
            val intent = Intent(this,  activity_game::class.java)
            Toast.makeText(this, "Usuario logado", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }else{
            Toast.makeText(this, "Usuario não logado", Toast.LENGTH_LONG).show()
        }

        // Criando o spinner
        val personagens = arrayOf("Escolha seu personagem...", "Guerreiro", "Arqueiro", "Mago")

        val adapter:ArrayAdapter<String> = object: ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            personagens
        ){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view:TextView = super.getDropDownView(
                    position,
                    convertView,
                    parent
                ) as TextView

                view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)

                if (position == 0){
                    view.setTextColor(Color.LTGRAY)
                }

                if (position == spnPersonagem.selectedItemPosition){
                    view.background = ColorDrawable(Color.parseColor("#F5F5F5"))
                }

                spnPersonagem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                       personagemSelecionado = personagens.get(position)
                    }

                }

                return view
            }

            override fun isEnabled(position: Int): Boolean {
                // disable the third item of spinner
                return position != 0
            }
        }

        spnPersonagem.adapter = adapter

        btnIniciar.setOnClickListener(){
            val intent = Intent(this,  activity_game::class.java)

            adicionarPreferences.putString("Usuario", edtUsuario.getText().toString())
            adicionarPreferences.putString("Classe", personagemSelecionado)
            adicionarPreferences.apply()

            startActivity(intent)
        }

    }
}