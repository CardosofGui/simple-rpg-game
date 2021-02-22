package com.example.stickwars

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.stickwars.`class`.Boss
import com.example.stickwars.`class`.Player
import com.example.stickwars.classEnums.PlayerActions
import java.sql.Time

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [activity_bossFight.newInstance] factory method to
 * create an instance of this fragment.
 */
class activity_bossFight : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btnAtacar : Button
    lateinit var progBarTimer : ProgressBar
    lateinit var progBarLife : ProgressBar
    lateinit var txtTitle : TextView

    var lifeBoss : Int = 0
    var playerPower : Int = 0

    lateinit var Usuario: Player
    lateinit var Chefao: Boss

    lateinit var sharedPreferences: SharedPreferences
    lateinit var adicionarPreferences: SharedPreferences.Editor

    lateinit var timer : CountDownTimer

    var TimeRunning = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_activity_boss_fight, container, false)

        btnAtacar = v.findViewById(R.id.btnEnfrentarBoss)
        progBarLife = v.findViewById(R.id.progBarLife)
        progBarTimer = v.findViewById(R.id.progBarTimer)
        txtTitle = v.findViewById(R.id.txtTitle)

        sharedPreferences = requireContext().getSharedPreferences("Dados", Context.MODE_PRIVATE)
        adicionarPreferences = sharedPreferences.edit()

        progBarTimer.max = 30

        if(sharedPreferences.getBoolean("UsuarioLogado", false)) {

            Usuario = Player(
                sharedPreferences.getString("Usuario", "undefined").toString()
            )

            Usuario.setarInfoSalva(
                sharedPreferences.getInt("atkStats", 999),
                sharedPreferences.getInt("defStats", 999),
                sharedPreferences.getString("Classe", "undefined").toString(),
                sharedPreferences.getFloat("expTotal", 0.0F).toDouble()
            )

            Chefao = Boss(
                sharedPreferences.getString("nomeChefao", "Jinpachi").toString(),
                sharedPreferences.getInt("atkStatsChefao", 5),
                sharedPreferences.getInt("defStatsChefao", 5),
                sharedPreferences.getFloat("expTotalChefao", 1.0F).toDouble(),
                sharedPreferences.getInt("nivelChefao", 0),
                sharedPreferences.getBoolean("derrotadoBoss", false)
            )

            salvarDadosBoss()
            salvarDadosPlayer()
        }

        lifeBoss = (Chefao.atkStats + Chefao.defStats) * 10
        playerPower = (Usuario.atkStats + Usuario.defStats) / 2

        progBarLife.max = lifeBoss
        progBarLife.progress = lifeBoss

        atualizarDados()
        configurarTimer()

        btnAtacar.setOnClickListener{
            confrontoBoss()
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment activity_bossFight.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            activity_bossFight().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    fun salvarDadosPlayer(){
        adicionarPreferences.putInt("atkStats", Usuario.atkStats)
        adicionarPreferences.putInt("defStats", Usuario.defStats)
        adicionarPreferences.putFloat("expTotal", Usuario.expTotal.toFloat())
        adicionarPreferences.apply()
    }

    fun salvarDadosBoss(){
        adicionarPreferences.putString("nomeChefao", Chefao.nome).toString()
        adicionarPreferences.putInt("atkStatsChefao", Chefao.atkStats)
        adicionarPreferences.putInt("defStatsChefao", Chefao.defStats)
        adicionarPreferences.putFloat("expTotalChefao", Chefao.expTotal.toFloat())
        adicionarPreferences.putInt("nivelChefao", Chefao.nivelBoss)
        adicionarPreferences.putBoolean("derrotadoBoss", Chefao.derrotado)
        adicionarPreferences.apply()
    }

    fun confrontoBoss(){

        if(TimeRunning){

            lifeBoss -= playerPower

            ObjectAnimator.ofInt(progBarLife, "progress", lifeBoss).setDuration(100).start()

            if(lifeBoss <= 0){
                Chefao.derrotado = true
                Chefao.evoluirChefe(requireContext())
                lifeBoss = (Chefao.atkStats + Chefao.defStats) * 10

                TimeRunning = false
                timer.cancel()
                progBarTimer.progress = 0

                atualizarDados()
                salvarDadosBoss()
            }

            atualizarDados()

        }else{
            TimeRunning = true

            progBarLife.max = lifeBoss
            progBarLife.progress = lifeBoss

            Toast.makeText(requireContext(), "Iniciando confronto contra o Boss: ${Chefao.nome}", Toast.LENGTH_LONG).show()
            btnAtacar.setText("Atacar")

            timer.start()
        }
    }

    fun atualizarDados(){
        txtTitle.setText("Enfrentando Boss: ${Chefao.nome} Vida: ${lifeBoss}/${(Chefao.atkStats+Chefao.defStats)*10}")
    }

    fun configurarTimer(){
        timer = object : CountDownTimer(30000, 1000){
            override fun onFinish() {

                TimeRunning = false

            }

            override fun onTick(millisUntilFinished: Long) {
                var segundos: Int = millisUntilFinished.toInt() / 1000

                var progresso: Int = 30-segundos

                ObjectAnimator.ofInt(progBarTimer, "progress", progresso).setDuration(100).start()
            }
        }
    }

    override fun onStop() {
        timer.cancel()
        super.onStop()
    }
}