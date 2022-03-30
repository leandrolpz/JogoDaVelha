package br.senai.sp.cotia.tictactoeapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentJogoBinding;

public class JogoFragment extends Fragment {
    // variavel para acessar elementos da view
    private FragmentJogoBinding binding;
    // vetor de botoes para referenciar os botoes
    private Button[] botoes;
    // matriz de string que representa o tabuleiro
    private String[][] tabuleiro;
    // variaveis para os simbolos
    private String jog1, jog2, simbolo;
    // variavel random para ver quem inicia
    private Random random;
    // variavel para controlar número de jogadas
    private int numJogadas = 0;


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

        //instanciar o tabuleiro
        tabuleiro = new String[3][3];

        // preenche a matriz com ""

        /*
        for(int i = 0; i <3; i++){
            for (int j = 0; j<3; j++){
                tabuleiro[i][j] = "";
            }
        }
        */


        for (String[] vetor : tabuleiro){
            Arrays.fill(vetor, "");
        }

        // definir os simbolos do jogador 1 e jogador 2
        jog1 = "X";
        jog2 = "O";

        //instancia o random
        random = new Random();

        //sorteia quem inicia o jogo
        sorteia();

        //atualiza a cor no placar
        atualizarVez();





        //retorno da view do binding
       return binding.getRoot();
    }

    private void sorteia(){
        // se gerar um valor VERDADEIRO jogador 1 começa, caso contrario JOGADOR 2 começa
        if(random.nextBoolean()){
            simbolo = jog1;
        }else{
            simbolo = jog2;
        }
    }

    private void atualizarVez(){
        if(simbolo.equals(jog1)){
            binding.linear.setBackgroundColor(getResources().getColor(R.color.verde_frag));
            binding.linear2.setBackgroundColor(getResources().getColor(R.color.black));
        }else{
            binding.linear2.setBackgroundColor(getResources().getColor(R.color.verde_frag));
            binding.linear.setBackgroundColor(getResources().getColor(R.color.black));

        }
    }

    private boolean vencedor(){
        // verifica s evenceu nas linhas
        for (int li = 0; li < 3; li++){
            if (tabuleiro[li][0].equals(simbolo) && tabuleiro[li][1].equals(simbolo) && tabuleiro[li][2].equals(simbolo)){
                return true;
            }
        }

        // verifaca se venceu nas colunas
        for (int col = 0; col < 2; col++){
            if (tabuleiro[0][col].equals(simbolo) && tabuleiro[1][col].equals(simbolo) && tabuleiro[2][col].equals(simbolo)){
                return true;
            }
        }

        if (tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)){
            return true;
        }
        if (tabuleiro[0][2].equals(simbolo)  && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo)){
            return true;
        }
        return false;
    }
    private void  resetGame(){
        // reset vizualmente os butões
        for(Button bt : botoes){
            bt.setClickable(true);
            bt.setText("");
            bt.setBackgroundColor(getResources().getColor(R.color.verde_frag));

        }
        // reset o tabuleiro
        for (String[] vetor : tabuleiro){
            Arrays.fill(vetor, "");
        }

        numJogadas =0;
    }

   /* Outro jeito
    private View.OnClickListener listenerBotoes = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };*/
    private View.OnClickListener listenerBotoes = btPress ->{
        // incrementa o numero de jogadas
        numJogadas++;
        //obtem o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());
        // extrai a posição atraves do nome do botão
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai linha e coluna da string posicao
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));
        // preencher a posição da matriz com o simbolo da "vez"
        tabuleiro[linha][coluna] = simbolo;
        // faz um casting de View para button
        Button botao = (Button) btPress;
        // "seta" o simbolo no botão pressionado
        botao.setText(simbolo);
        // troca o background do botao para preto
        botao.setBackgroundColor(Color.BLACK);
        // desabilitar botão que foi clicado
        botao.setClickable(false);
        if(numJogadas >= 5 && vencedor()){
            Toast.makeText(getContext(), R.string.venceu, Toast.LENGTH_LONG).show();
             resetGame();
        }else if(numJogadas == 9){
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_LONG).show();
            resetGame();

        }else {
            //inverte o simbolo
            simbolo = simbolo.equals(jog1) ? jog2 : jog1;

            // atualiza a vez
            atualizarVez();

        }

   };

}