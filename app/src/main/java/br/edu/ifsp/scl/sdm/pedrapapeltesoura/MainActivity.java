package br.edu.ifsp.scl.sdm.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());

        activityMainBinding.pedraBt.setOnClickListener(this);

        activityMainBinding.tesouraBt.setOnClickListener(this);

        activityMainBinding.papelBt.setOnClickListener(this);

        activityMainBinding.tresRb.setOnCheckedChangeListener( (buttonView, mostrarOpcoes) -> {
            activityMainBinding.selecionarOpcaoLl.setVisibility(mostrarOpcoes ? View.VISIBLE:View.GONE);
        });
    }

    @Override
    public void onClick(View v) {
        int jogada = -1;
        switch (v.getId()){
            case R.id.pedraBt:
                jogada = 0;
                break;
            case R.id.tesouraBt:
                jogada = 1;
                break;
            case R.id.papelBt:
                jogada = 2;
                break;
            default:
                break;
        }
        jogarPedraPapelTesoura(jogada);
    }

    private void jogarPedraPapelTesoura(int jogada) {

        String jogadaStr = "";
        if (jogada == 0) {
            jogadaStr = "Pedra";
        } else if (jogada == 1) {
            jogadaStr = "Tesoura";
        } else {
            jogadaStr = "Papel";
        }

        Random random = new Random(System.currentTimeMillis());
        int jogadaComputador = random.nextInt(3);

        String jogadaComputadorStr = "";
        //setando imagem da jogada do comptador
        int imagemjogadaComputadorId = -1;
        switch (jogadaComputador){
            case 0:
                imagemjogadaComputadorId = R.mipmap.pedra;
                jogadaComputadorStr = "Pedra";
                break;
            case 1:
                imagemjogadaComputadorId = R.mipmap.tesoura;
                jogadaComputadorStr = "Tesoura";
                break;
            case 2:
                imagemjogadaComputadorId = R.mipmap.papel;
                jogadaComputadorStr = "Papel";
                break;
            default:
                break;
        }

        activityMainBinding.jogadaComputadorIv.setImageResource(imagemjogadaComputadorId);

        StringBuilder resultadoSb = new StringBuilder();
        resultadoSb.append("Sua jogada: ");
        resultadoSb.append(jogadaStr);
        resultadoSb.append("\nComputador: ");
        resultadoSb.append(jogadaComputadorStr);

        if(activityMainBinding.doisRb.isChecked()) {
            if(jogada == jogadaComputador) {
                resultadoSb.append("\n EMPATE!");
            }
            else if (
                    (jogada == 0 && jogadaComputador == 1) ||
                            (jogada == 1 && jogadaComputador == 2) ||
                            (jogada == 2 && jogadaComputador == 0) ) {
                resultadoSb.append("\n VITÓRIA!");
            } else {
                resultadoSb.append("\n DERROTA!");
            }
            activityMainBinding.resultadoTv.setText(resultadoSb.toString());
        } else {
            int jogadaComputador1 = random.nextInt(3);

            //setando imagem da jogada do computador
            String jogadaComputadorStr1 = "";
            int imagemjogadaComputadorId1 = -1;
            switch (jogadaComputador1){
                case 0:
                    imagemjogadaComputadorId1 = R.mipmap.pedra;
                    jogadaComputadorStr1 = "Pedra";
                    break;
                case 1:
                    imagemjogadaComputadorId1 = R.mipmap.tesoura;
                    jogadaComputadorStr1 = "Tesoura";
                    break;
                case 2:
                    imagemjogadaComputadorId1 = R.mipmap.papel;
                    jogadaComputadorStr1 = "Papel";
                    break;
                default:
                    break;
            }
            activityMainBinding.jogadaComputadorIv1.setImageResource(imagemjogadaComputadorId1);

            resultadoSb.append("\nComputador1: ");
            resultadoSb.append(jogadaComputadorStr1);

            if((jogada == jogadaComputador) && (jogadaComputador == jogadaComputador1)) {
                resultadoSb.append("\n EMPATE!");
            }
            else if (
                    (jogada == 0 && jogadaComputador == 1 && jogadaComputador1 == 1) ||
                    (jogada == 1 && jogadaComputador == 2 && jogadaComputador1 == 2) ||
                    (jogada == 2 && jogadaComputador == 0 && jogadaComputador1 == 0) ) {
                resultadoSb.append("\n VITÓRIA!");
            } else if (
                    (jogada == 0 && jogadaComputador == 0 && jogadaComputador1 == 1) ||
                    (jogada == 1 && jogadaComputador == 1 && jogadaComputador1 == 2) ||
                    (jogada == 2 && jogadaComputador == 2 && jogadaComputador1 == 0) ) {
                resultadoSb.append("\n EMPATE!");
            } else if (
                    (jogada == 0 && jogadaComputador == 1 && jogadaComputador1 == 2) ||
                    (jogada == 0 && jogadaComputador == 2 && jogadaComputador1 == 1) ||
                    (jogada == 0 && jogadaComputador == 2 && jogadaComputador1 == 2) ||
                    (jogada == 1 && jogadaComputador == 0 && jogadaComputador1 == 2) ||
                    (jogada == 1 && jogadaComputador == 2 && jogadaComputador1 == 0) ||
                    (jogada == 1 && jogadaComputador == 0 && jogadaComputador1 == 0) ||
                    (jogada == 2 && jogadaComputador == 1 && jogadaComputador1 == 0) ||
                    (jogada == 2 && jogadaComputador == 1 && jogadaComputador1 == 1) ||
                    (jogada == 2 && jogadaComputador == 0 && jogadaComputador1 == 1)) {
                resultadoSb.append("\n EMPATE!");
            } else {
                resultadoSb.append("\n DERROTA!");
            }
            activityMainBinding.resultadoTv.setText(resultadoSb.toString());
        }

    }
}