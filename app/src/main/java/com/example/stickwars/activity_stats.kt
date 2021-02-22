package com.example.stickwars

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.stickwars.`class`.Boss
import com.example.stickwars.`class`.Player
import com.example.stickwars.classEnums.BdSharedPreferences
import com.example.stickwars.classEnums.Chefoes

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [activity_stats.newInstance] factory method to
 * create an instance of this fragment.
 */
class activity_stats : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var txtInfo: TextView
    lateinit var txtStats: TextView
    lateinit var imgClass: ImageView

    lateinit var Usuario: Player
    lateinit var Chefao: Boss

    lateinit var sharedPreferences: SharedPreferences
    lateinit var adicionarPreferences: SharedPreferences.Editor


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

        val v = inflater.inflate(R.layout.fragment_activity_stats, container, false)

        txtInfo = v.findViewById(R.id.txtTitulo)
        txtStats = v.findViewById(R.id.txtStats)
        imgClass = v.findViewById(R.id.imgClass)


        sharedPreferences = requireContext().getSharedPreferences("Dados", Context.MODE_PRIVATE)
        adicionarPreferences = sharedPreferences.edit()

        criandoObjetos(sharedPreferences.getBoolean(BdSharedPreferences.usuarioLogado.key, false))


        setarTextos()
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment activity_stats.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            activity_stats().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun setarTextos(){
        txtStats.setText(String.format("Atk: ${Usuario.atkStats} Def: ${Usuario.defStats} Exp. Total: %.1f", Usuario.expTotal))
        txtInfo.setText("Bem vindo ${Usuario.nome} - Classe ${Usuario.classe}")
    }

    fun criandoObjetos(logado : Boolean){
        if(logado){

            Usuario = Player(
                sharedPreferences.getString(BdSharedPreferences.playerNome.key, "undefined").toString()
            )
            Usuario.setarInfoSalva(
                sharedPreferences.getInt(BdSharedPreferences.playerAtkStats.key, 999),
                sharedPreferences.getInt(BdSharedPreferences.playerDefStats.key, 999),
                sharedPreferences.getString(BdSharedPreferences.playerClasse.key, "undefined").toString(),
                sharedPreferences.getFloat(BdSharedPreferences.playerExpTotal.key, 0.0F).toDouble()
            )
            Usuario.atualizarImagemClasse(imgClass)

        }else{
            val classe: String = sharedPreferences.getString(BdSharedPreferences.playerClasse.key, "undefined").toString()
            val nome: String = sharedPreferences.getString(BdSharedPreferences.playerNome.key, "undefined").toString()

            Usuario = Player(nome)
            Usuario.setarClasse(classe, imgClass)

            Chefao = Boss(
                Chefoes.Chef1.nomeChefao,
                Chefoes.Chef1.atkStats,
                Chefoes.Chef1.defStats,
                Chefoes.Chef1.nivelBoss,
                Chefoes.Chef1.derrotado)

            adicionarPreferences.putBoolean("UsuarioLogado", true)
            adicionarPreferences.apply()

            Usuario.salvarDadosPlayer(adicionarPreferences)
            Chefao.salvarDadosBoss(adicionarPreferences)
        }
    }

}