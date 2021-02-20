package com.example.stickwars

import android.animation.ObjectAnimator
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.stickwars.`class`.*

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

                criarPopupEvoluirStats("Atk")

            }
            R.id.btnUparDef -> {

                criarPopupEvoluirStats("Def")

            }
            R.id.btnEnfrentarBoss -> {
                Usuario.combateBoss(Chefao, baseContext)
            }
        }
    }


    fun criarPopupEvoluirStats(evoluir: String){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.popup_upar_stats, null)
        val mBuilder = AlertDialog.Builder(this).setView(dialogView)
        val dialog = mBuilder.create()

        val txtTimer : TextView = dialogView.findViewById(R.id.timer)
        val timer: CountDownTimer
        val progBarLvlUp : ProgressBar = dialogView.findViewById(R.id.progLvlUp)

        progBarLvlUp.max = 30

        timer = object : CountDownTimer(30000, 1000){
            override fun onFinish() {

                if(evoluir.equals("Atk")){
                    Usuario.evoluirForca()
                }else{
                    Usuario.evoluirDefesa()
                }

                txtStats.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
                dialog.dismiss()
            }

            override fun onTick(millisUntilFinished: Long) {
                var minutes: Int = ((millisUntilFinished/1000) / 60).toInt()
                var segundos: Int = ((millisUntilFinished/1000) % 60).toInt()

                var timeText = "$minutes:"
                if(segundos < 10) timeText += "0"
                timeText += segundos

                txtTimer.setText(timeText)

                var progresso: Int = 30 - segundos
                ObjectAnimator.ofInt(progBarLvlUp, "progress", progresso).setDuration(1000).start()
            }
        }.start()

        dialog.setOnDismissListener(DialogInterface.OnDismissListener {
            Toast.makeText(baseContext, "fechado", Toast.LENGTH_LONG).show()
            timer.cancel()
        })

        dialog.show()
    }
}

