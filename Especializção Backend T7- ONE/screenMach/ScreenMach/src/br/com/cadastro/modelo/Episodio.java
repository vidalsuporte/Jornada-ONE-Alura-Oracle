package br.com.cadastro.modelo;

import br.com.cadastro.calculos.Classificavel;

public class Episodio implements Classificavel {
    private int numeroEpisoio;
    private int Temporada;
    private String nome;
    private Serie serie;
    private int totalDeVisualizacao;

    public int getTotalDeVisualizacao() {
        return totalDeVisualizacao;
    }

    public void setTotalDeVisualizacao(int totalDeVisualizacao) {
        this.totalDeVisualizacao = totalDeVisualizacao;
    }

    public int getNumeroEpisoio() {
        return numeroEpisoio;
    }

    public void setNumeroEpisoio(int numeroEpisoio) {
        this.numeroEpisoio = numeroEpisoio;
    }

    public int getTemporada() {
        return Temporada;
    }

    public void setTemporada(int temporada) {
        Temporada = temporada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public int getClassificacao() {
       if(totalDeVisualizacao > 100){
           return 4;
       }else {
           return 2;
       }

    }
}
