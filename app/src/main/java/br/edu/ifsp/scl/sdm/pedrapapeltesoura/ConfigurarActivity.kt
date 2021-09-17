package br.edu.ifsp.scl.sdm.pedrapapeltesoura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.ActivityConfigurarBinding

class ConfigurarActivity : AppCompatActivity() {
    private lateinit var activityConfigurarBinding: ActivityConfigurarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityConfigurarBinding = ActivityConfigurarBinding.inflate(layoutInflater)
        setContentView(activityConfigurarBinding.root)

        //ActionBar
        supportActionBar?.title = "Configurações"

        activityConfigurarBinding.salvarBt.setOnClickListener(){
            val retornoIntent: Intent = Intent()
            with (activityConfigurarBinding) {
                retornoIntent.putExtra(MainActivity.DOISJOG, doisRb.isChecked.toString())
                retornoIntent.putExtra(MainActivity.TRESJOG, tresRb.isChecked.toString())
                retornoIntent.putExtra(MainActivity.UMROD, umRoRb.isChecked.toString())
                retornoIntent.putExtra(MainActivity.TRESROD, tresRoRb.isChecked.toString())
                retornoIntent.putExtra(MainActivity.CINCOROD, cincoRoRb.isChecked.toString())
            }
            setResult(RESULT_OK, retornoIntent)
            finish()

        }
    }
}