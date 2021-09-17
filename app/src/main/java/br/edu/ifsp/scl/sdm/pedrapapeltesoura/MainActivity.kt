package br.edu.ifsp.scl.sdm.pedrapapeltesoura

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.scl.sdm.pedrapapeltesoura.R
import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var doisjogTv: TextView
    private lateinit var tresjogTv: TextView
    private lateinit var umrodTv: TextView
    private lateinit var tresrodTv: TextView
    private lateinit var cincorodTv: TextView
    private lateinit var configurarActivityResultLauncher: ActivityResultLauncher<Intent>

    companion object {
        val DOISJOG = "DOISJOG"
        val TRESJOG = "TRESJOG"
        val UMROD = "UMROD"
        val TRESROD = "TRESROD"
        val CINCOROD = "CINCOROD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(activityMainBinding.root)


        //ActionBar
        supportActionBar?.title = "Jogar"

        activityMainBinding.pedraBt.setOnClickListener(this)
        activityMainBinding.tesouraBt.setOnClickListener(this)
        activityMainBinding.papelBt.setOnClickListener(this)


       /* activityMainBinding.tresRb.setOnCheckedChangeListener { _: CompoundButton?, mostrarOpcoes: Boolean ->
            activityMainBinding.selecionarOpcaoLl.visibility =
                if (mostrarOpcoes) View.VISIBLE else View.GONE
        }*/

        doisjogTv= TextView(this)
        with (doisjogTv) {
            hint = "Dois"
            //visibility = View.GONE
            activityMainBinding.root.addView(this)
        }

        tresjogTv = TextView(this)
        with (tresjogTv) {
            hint = "tres"
            //visibility = View.GONE
            activityMainBinding.root.addView(this)
        }

        umrodTv= TextView(this)
        with (umrodTv) {
            hint = "Dois"
            //visibility = View.GONE
            activityMainBinding.root.addView(this)
        }

        tresrodTv = TextView(this)
        with (tresrodTv) {
            hint = "tres"
            //visibility = View.GONE
            activityMainBinding.root.addView(this)
        }

        cincorodTv = TextView(this)
        with (cincorodTv) {
            hint = "tres"
            //visibility = View.GONE
            activityMainBinding.root.addView(this)
        }

        savedInstanceState?.getString(DOISJOG).takeIf { it != null }.apply{ doisjogTv.setText(this) }
        savedInstanceState?.getString(TRESJOG).takeIf { it != null }.apply{ tresjogTv.setText(this) }
        savedInstanceState?.getString(UMROD).takeIf { it != null }.apply{ umrodTv.setText(this) }
        savedInstanceState?.getString(TRESROD).takeIf { it != null }.apply{ tresrodTv.setText(this) }
        savedInstanceState?.getString(CINCOROD).takeIf { it != null }.apply{ cincorodTv.setText(this) }

        configurarActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado?.resultCode == RESULT_OK){
                with (resultado) {
                    data?.getStringExtra(DOISJOG).takeIf { it != null }.run { doisjogTv.setText(this) }
                    data?.getStringExtra(TRESJOG).takeIf { it != null }.run { tresjogTv.setText(this) }
                    data?.getStringExtra(UMROD).takeIf { it != null }.let { umrodTv.setText(it) }
                    data?.getStringExtra(TRESROD).takeIf { it != null }.run { tresrodTv.setText(this) }
                    data?.getStringExtra(CINCOROD).takeIf { it != null }.run { cincorodTv.setText(this) }
                }
            }
        }
    }

    override fun onClick(v: View) {
        var jogada = -1
        when (v.id) {
            R.id.pedraBt -> jogada = 0
            R.id.tesouraBt -> jogada = 1
            R.id.papelBt -> jogada = 2
            else -> {
            }
        }
        jogarPedraPapelTesoura(jogada)
    }

    private fun jogarPedraPapelTesoura(jogada: Int) {

        var jogadaStr = if (jogada == 0) {
            "Pedra"
        } else if (jogada == 1) {
            "Tesoura"
        } else {
            "Papel"
        }
        val random = Random(System.currentTimeMillis())
        val jogadaComputador = random.nextInt(3)
        var jogadaComputadorStr = ""
        //setando imagem da jogada do comptador
        var imagemjogadaComputadorId = -1
        when (jogadaComputador) {
            0 -> {
                imagemjogadaComputadorId = R.mipmap.pedra
                jogadaComputadorStr = "Pedra"
            }
            1 -> {
                imagemjogadaComputadorId = R.mipmap.tesoura
                jogadaComputadorStr = "Tesoura"
            }
            2 -> {
                imagemjogadaComputadorId = R.mipmap.papel
                jogadaComputadorStr = "Papel"
            }
            else -> {
            }
        }
        activityMainBinding.jogadaComputadorIv.setImageResource(imagemjogadaComputadorId)
        val resultadoSb = StringBuilder()
        resultadoSb.append("Sua jogada: ")
        resultadoSb.append(jogadaStr)
        resultadoSb.append("\nComputador: ")
        resultadoSb.append(jogadaComputadorStr)
        if (doisjogTv.equals(true)) {
            if (jogada == jogadaComputador) {
                resultadoSb.append("\n EMPATE!")
            } else if (jogada == 0 && jogadaComputador == 1 ||
                jogada == 1 && jogadaComputador == 2 ||
                jogada == 2 && jogadaComputador == 0
            ) {
                resultadoSb.append("\n VITÓRIA!")
            } else {
                resultadoSb.append("\n DERROTA!")
            }
            activityMainBinding.resultadoTv.text = resultadoSb.toString()
        } else {
            val jogadaComputador1 = random.nextInt(3)

            //setando imagem da jogada do computador
            var jogadaComputadorStr1 = ""
            var imagemjogadaComputadorId1 = -1
            when (jogadaComputador1) {
                0 -> {
                    imagemjogadaComputadorId1 = R.mipmap.pedra
                    jogadaComputadorStr1 = "Pedra"
                }
                1 -> {
                    imagemjogadaComputadorId1 = R.mipmap.tesoura
                    jogadaComputadorStr1 = "Tesoura"
                }
                2 -> {
                    imagemjogadaComputadorId1 = R.mipmap.papel
                    jogadaComputadorStr1 = "Papel"
                }
                else -> {
                }
            }
            activityMainBinding.jogadaComputadorIv1.setImageResource(imagemjogadaComputadorId1)
            resultadoSb.append("\nComputador1: ")
            resultadoSb.append(jogadaComputadorStr1)
            if (jogada == jogadaComputador && jogadaComputador == jogadaComputador1) {
                resultadoSb.append("\n EMPATE!")
            } else if (jogada == 0 && jogadaComputador == 1 && jogadaComputador1 == 1 ||
                jogada == 1 && jogadaComputador == 2 && jogadaComputador1 == 2 ||
                jogada == 2 && jogadaComputador == 0 && jogadaComputador1 == 0
            ) {
                resultadoSb.append("\n VITÓRIA!")
            } else if (jogada == 0 && jogadaComputador == 0 && jogadaComputador1 == 1 ||
                jogada == 1 && jogadaComputador == 1 && jogadaComputador1 == 2 ||
                jogada == 2 && jogadaComputador == 2 && jogadaComputador1 == 0
            ) {
                resultadoSb.append("\n EMPATE!")
            } else if (jogada == 0 && jogadaComputador == 1 && jogadaComputador1 == 2 ||
                jogada == 0 && jogadaComputador == 2 && jogadaComputador1 == 1 ||
                jogada == 0 && jogadaComputador == 2 && jogadaComputador1 == 2 ||
                jogada == 1 && jogadaComputador == 0 && jogadaComputador1 == 2 ||
                jogada == 1 && jogadaComputador == 2 && jogadaComputador1 == 0 ||
                jogada == 1 && jogadaComputador == 0 && jogadaComputador1 == 0 ||
                jogada == 2 && jogadaComputador == 1 && jogadaComputador1 == 0 ||
                jogada == 2 && jogadaComputador == 1 && jogadaComputador1 == 1 ||
                jogada == 2 && jogadaComputador == 0 && jogadaComputador1 == 1
            ) {
                resultadoSb.append("\n EMPATE!")
            } else {
                resultadoSb.append("\n DERROTA!")
            }
            activityMainBinding.resultadoTv.text = resultadoSb.toString()
        }
    }

    // Salvando dados de instância
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(DOISJOG, doisjogTv.text.toString())
        outState.putString(TRESJOG, tresjogTv.text.toString())
        outState.putString(UMROD, doisjogTv.text.toString())
        outState.putString(TRESROD, tresrodTv.text.toString())
        outState.putString(CINCOROD, cincorodTv.text.toString())
    }

    // Restaurando dados de instância
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //savedInstanceState.getString(NOME).takeIf { it != null }.apply{ nomeEt.setText(this) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.configurarMi -> {
                val editarIntent: Intent = Intent(this, ConfigurarActivity::class.java)
                configurarActivityResultLauncher.launch(editarIntent)
                true
            }
            else -> {
                false
            }
        }
    }
}