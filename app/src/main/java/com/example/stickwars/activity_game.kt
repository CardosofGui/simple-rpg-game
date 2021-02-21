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
import com.example.stickwars.classEnums.Chefoes
import com.example.stickwars.classEnums.PlayerActions
import pl.droidsonroids.gif.GifImageView

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

        // Inicializando variaveis e localizando IDs
        btnUparAtk = findViewById(R.id.btnUparAtk)
        btnUparDef = findViewById(R.id.btnUparDef)
        btnBoss = findViewById(R.id.btnEnfrentarBoss)
        txtInfo = findViewById(R.id.txtTitulo)
        txtStats = findViewById(R.id.txtStats)
        imgClass = findViewById(R.id.imgClass)

        // Resgatando dados
        val classe: String = intent.getStringExtra("personagemSelecionado").toString()
        val nome: String = intent.getStringExtra("nomeUser").toString()

        // Criando objetos (Personagens)
        Usuario = Player("$nome")
        Usuario.setarClasse(classe, imgClass)
        Chefao = Boss(Chefoes.Chef1.nomeChefao, Chefoes.Chef1.atkStats, Chefoes.Chef1.defStats, Chefoes.Chef1.expTotal, Chefoes.Chef1.nivelBoss, Chefoes.Chef1.derrotado)


        // Setando textos iniciais
        txtStats.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
        txtInfo.setText("Bem vindo ${Usuario.nome} - Classe ${Usuario.classe}")
        btnBoss.setText("Boss Lvl. ${Chefao.nivelBoss} \n ${Chefao.nome}")

        // Ações Cliques
        btnUparAtk.setOnClickListener(this)
        btnUparDef.setOnClickListener(this)
        btnBoss.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnUparAtk -> {
                criarPopupEvoluirStats(PlayerActions.ACTION_UP_ATK.action,15)
            }
            R.id.btnUparDef -> {
                criarPopupEvoluirStats(PlayerActions.ACTION_UP_DEF.action,15)
            }
            R.id.btnEnfrentarBoss -> {
                criarPopupCombate(15)
            }
        }
    }

    fun criarPopupEvoluirStats(evoluir: String, delayUpgradeSeg: Long){
        // Gerando popup
        val dialogView = LayoutInflater.from(this).inflate(R.layout.popup_upar_stats, null)
        val mBuilder = AlertDialog.Builder(this).setView(dialogView)
        val dialog = mBuilder.create()

        // Inicializando variaveis e localizando IDs
        val txtTimer : TextView = dialogView.findViewById(R.id.timer)
        val txtStatsAction : TextView = dialogView.findViewById(R.id.txtStatsAction)
        val progBarLvlUp : ProgressBar = dialogView.findViewById(R.id.progLvlUp)
        val gifAction : GifImageView = dialogView.findViewById(R.id.gifAction)

        val timer: CountDownTimer

        // Setando config inicial
        gifAction.setBackgroundResource(R.drawable.training_fight)
        progBarLvlUp.max = 3000

        if(evoluir.equals(PlayerActions.ACTION_UP_ATK.action)){
            txtStatsAction.setText("Evoluindo seu Ataque")
        }else if(evoluir.equals(PlayerActions.ACTION_UP_DEF.action)){
            txtStatsAction.setText("Evoluindo sua Defesa")
        }


        timer = object : CountDownTimer(delayUpgradeSeg*1000, 1000){
            override fun onFinish() {

                if(evoluir.equals(PlayerActions.ACTION_UP_ATK.action)){
                    // Fazendo upgrade na força se tiver sido a escolhida
                    Usuario.evoluirForca()
                    Toast.makeText(baseContext, "+1 LVL de ATK. LVL atual: ${Usuario.atkStats}", Toast.LENGTH_LONG).show()
                }else if(evoluir.equals(PlayerActions.ACTION_UP_DEF.action)){
                    // Fazendo upgrade na defesa se tiver sido a escolhida
                    Usuario.evoluirDefesa()
                    Toast.makeText(baseContext, "+1 LVL de DEF. LVL atual: ${Usuario.defStats}", Toast.LENGTH_LONG).show()
                }

                // Atualizando informações
                txtStats.setText("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: ${Usuario.expTotal}")
                dialog.dismiss()
            }

            override fun onTick(millisUntilFinished: Long) {
                // Executando o timer e a barra de progresso
                var minutes: Int = ((millisUntilFinished/1000) / 60).toInt()
                var segundos: Int = ((millisUntilFinished/1000) % 60).toInt()

                var timeText = "$minutes:"
                if(segundos < 10) timeText += "0"
                timeText += segundos

                txtTimer.setText(timeText)

                var progresso: Int = (delayUpgradeSeg.toInt() - segundos) * 200
                ObjectAnimator.ofInt(progBarLvlUp, "progress", progresso).setDuration(1000).start()
            }
        }.start()

        dialog.setOnDismissListener(DialogInterface.OnDismissListener {
            timer.cancel()
        })

        dialog.show()
    }

    fun criarPopupCombate(delayCombateSeg: Long){
        // Gerando popup
        val dialogView = LayoutInflater.from(this).inflate(R.layout.popup_upar_stats, null)
        val mBuilder = AlertDialog.Builder(this).setView(dialogView)
        val dialog = mBuilder.create()

        // Inicializando variaveis e localizando IDs
        val txtTimer : TextView = dialogView.findViewById(R.id.timer)
        val txtStatsAction : TextView = dialogView.findViewById(R.id.txtStatsAction)
        val progBarLvlUp : ProgressBar = dialogView.findViewById(R.id.progLvlUp)
        val gifAction : GifImageView = dialogView.findViewById(R.id.gifAction)

        val timer: CountDownTimer

        // Setando config inicial
        txtStatsAction.setText("Enfrentando Boss - ${Chefao.nome}")
        gifAction.setBackgroundResource(R.drawable.boss_fight)
        progBarLvlUp.max = 3000

        timer = object : CountDownTimer(delayCombateSeg*1000, 1000){
            override fun onFinish() {
                // Criando o combate
                Usuario.combateBoss(Chefao, baseContext, btnBoss)
                dialog.dismiss()
            }

            override fun onTick(millisUntilFinished: Long) {
                // Executando o timer e a barra de progresso
                var minutes: Int = ((millisUntilFinished/1000) / 60).toInt()
                var segundos: Int = ((millisUntilFinished/1000) % 60).toInt()

                var timeText = "$minutes:"
                if(segundos < 10) timeText += "0"
                timeText += segundos

                txtTimer.setText(timeText)

                var progresso: Int = (delayCombateSeg.toInt() - segundos) * 200
                ObjectAnimator.ofInt(progBarLvlUp, "progress", progresso).setDuration(1000).start()
            }
        }.start()

        dialog.setOnDismissListener(DialogInterface.OnDismissListener {
            timer.cancel()
        })

        dialog.show()
    }
}

