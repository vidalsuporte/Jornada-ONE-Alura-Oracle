package br.com.cadastro.principal;
import br.com.cadastro.calculos.CalculdoraDeTempo;
import br.com.cadastro.calculos.FiltroDeRecomendacao;
import br.com.cadastro.modelo.Episodio;
import br.com.cadastro.modelo.Filme;
import br.com.cadastro.modelo.Serie;
import br.com.cadastro.modelo.Titulo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("hitiman", 2010);

        meuFilme.setDuracao(160);
        meuFilme.avalia(9.00);
        meuFilme.avalia(9.00);
        meuFilme.avalia(9.00);

        System.out.println(meuFilme.getClassificacao());
        meuFilme.exibeDados();
        System.out.println("__________________");
        Filme outroFilme = new Filme("avatar",2013 );
        outroFilme.setDuracao(215);
        outroFilme.avalia(5.00);
        outroFilme.avalia(2.00);
        outroFilme.avalia(1.00);

        System.out.println(outroFilme.getClassificacao());
        outroFilme.exibeDados();
        System.out.println("__________________");
        Serie serie = new Serie("lost", 2000);

        serie.setTemporada(10);
        serie.setEpisodioPorTemporada(10);
        serie.setMinutosPorEpisodios(50);

        serie.exibeDados();
        System.out.println("__________________");
        CalculdoraDeTempo calculdoraDeTempo = new CalculdoraDeTempo();

        calculdoraDeTempo.incluir(meuFilme);
        calculdoraDeTempo.incluir(outroFilme);
        calculdoraDeTempo.incluir(serie);

        System.out.println("Tempo para maratonar é de: " + calculdoraDeTempo.getTempoTotal()+"min.");

        Episodio episodio = new Episodio();
        episodio.setTotalDeVisualizacao(120);


        FiltroDeRecomendacao filtroDeRecomendacao = new FiltroDeRecomendacao();

            filtroDeRecomendacao.filtra(episodio);
        System.out.println("--------------------------");
        filtroDeRecomendacao.filtra(meuFilme);
        System.out.println("----------------------------");
            filtroDeRecomendacao.filtra(outroFilme);


        ArrayList<Titulo> titulos = new ArrayList<>();
        titulos.add(meuFilme);
        titulos.add(outroFilme);
        titulos.add(serie);

        System.out.println("tamanho da Lista: " + titulos.size());
        System.out.println("Nome do Primeiro Título da Lista: " + titulos.get(0).getNome() );




}}





