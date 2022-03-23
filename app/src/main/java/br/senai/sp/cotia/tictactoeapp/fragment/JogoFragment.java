package br.senai.sp.cotia.tictactoeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentJogoBinding;

public class JogoFragment extends Fragment {
    // variavel para acessar elementos da view
    private FragmentJogoBinding binding;
    // vetor de botoes para referenciar os botoes
    private Button[] botoes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       //instanciar o binding
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        //INSTANCIAR O VETOR
        botoes = new Button[9];

        //associar o vetor aos bototes
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        // associa o listener aos botês
        for(Button bt : botoes){
            bt.setOnClickListener(listenerBotoes);
        }

        //retorno da view do binding
       return binding.getRoot();
    }

   /* Outro jeito
    private View.OnClickListener listenerBotoes = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };*/

    private View.OnClickListener listenerBotoes = btPress ->{
        //Log.w("BOTAO", getContext().getResources().getResourceName(btPress.getId()));
        //obtem o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());
        // extrai a posição atraves do nome do botão
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai linha e coluna da string posicao
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        Log.w("BOTAO", linha+"");
        Log.w("BOTAO", linha+"");
   };
}