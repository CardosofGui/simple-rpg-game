package com.example.stickwars

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.stickwars.`class`.Player

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [activity_upDef.newInstance] factory method to
 * create an instance of this fragment.
 */
class activity_upDef : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btnUpgradeDef : Button
    lateinit var progBarLvlUp : ProgressBar
    lateinit var txtTitle : TextView


    lateinit var Usuario: Player

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
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_activity_up_def, container, false)

        btnUpgradeDef = v.findViewById(R.id.btnUpgradeDef)
        progBarLvlUp = v.findViewById(R.id.progBarLvlUp)
        txtTitle = v.findViewById(R.id.txtTitle)

        progBarLvlUp.max = 15
        var clickCount = 0

        sharedPreferences = requireContext().getSharedPreferences("Dados", Context.MODE_PRIVATE)
        adicionarPreferences = sharedPreferences.edit()


        if(sharedPreferences.getBoolean("UsuarioLogado", false)){

            Usuario = Player(
                sharedPreferences.getString("Usuario", "undefined").toString())

            Usuario.setarInfoSalva(
                sharedPreferences.getInt("atkStats", 999),
                sharedPreferences.getInt("defStats", 999),
                sharedPreferences.getString("Classe", "undefined").toString(),
                sharedPreferences.getFloat("expTotal", 0.0F).toDouble())
        }

        atualizarTexto()

        btnUpgradeDef.setOnClickListener {
            clickCount++

            if(clickCount == 15){
                ObjectAnimator.ofInt(progBarLvlUp, "progress", 0).setDuration(0).start()
                Usuario.evoluirDefesa()
                salvarDadosPlayer()
                clickCount = 0
                atualizarTexto()
            }else{
                ObjectAnimator.ofInt(progBarLvlUp, "progress", clickCount).setDuration(100).start()
            }
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
         * @return A new instance of fragment activity_upDef.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            activity_upDef().apply {
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

    fun atualizarTexto(){
        txtTitle.setText("Evoluindo Defesa: ${Usuario.defStats}")
    }
}