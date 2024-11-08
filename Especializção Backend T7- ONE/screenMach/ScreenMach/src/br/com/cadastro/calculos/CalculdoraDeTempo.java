package br.com.cadastro.calculos;

import br.com.cadastro.modelo.Filme;
import br.com.cadastro.modelo.Titulo;

public class CalculdoraDeTempo {

private int tempoTotal;


    public int getTempoTotal() {
        return tempoTotal;
    }

public void incluir(Titulo titulo){
         tempoTotal += titulo.getDuracao();
}



}
