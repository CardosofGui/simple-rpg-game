package com.example.rpgclicker.features.telaInicial

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.rpgclicker.R
import com.example.rpgclicker.base.BaseActivity
import com.example.rpgclicker.model.enums.BdSharedPreferences
import com.example.rpgclicker.features.bottom_navigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var btnIniciar : Button
    lateinit var edtUsuario : EditText
    lateinit var spnPersonagem : Spinner
    var personagemSelecionado : String? = null

    lateinit var sharedPreferences: SharedPreferences
    lateinit var adicionarPreferences: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar(toolbar, "RPG Clicker")

        // Inicializando variaveis e identificiando seus IDs
        btnIniciar = findViewById(R.id.btnIniciar)
        edtUsuario = findViewById(R.id.edtUsuario)
        spnPersonagem = findViewById(R.id.spnPersonagem)

        sharedPreferences = getSharedPreferences("Dados", Context.MODE_PRIVATE)
        adicionarPreferences = sharedPreferences.edit()

        verificaAutenticacao(sharedPreferences.getBoolean(BdSharedPreferences.USUARIO_LOGADO.key, false))

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
            val intent = Intent(this,  bottom_navigation::class.java)

            if(edtUsuario.getText().toString().isEmpty() || personagemSelecionado.isNullOrEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                adicionarPreferences.putString(BdSharedPreferences.PLAYER_NOME.key, edtUsuario.getText().toString())
                adicionarPreferences.putString(BdSharedPreferences.PLAYER_CLASSE.key, personagemSelecionado)
                adicionarPreferences.apply()
                startActivity(intent)
                finish()
            }
        }
    }

    fun verificaAutenticacao(logado : Boolean){
        if(logado){
            val intent = Intent(this,  bottom_navigation::class.java)
            Toast.makeText(this, "Usuario logado", Toast.LENGTH_LONG).show()
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Usuario não logado", Toast.LENGTH_LONG).show()
        }
    }

    // Criar menu de items na toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemCarregarJogo -> {
                Toast.makeText(baseContext, "Click carregar jogo", Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}