package br.com.cadastro.modelo;

public class Serie extends Titulo{
    private int temporada;
    private int episodioPorTemporada;
    private boolean ativa;
    private int minutosPorEpisodios;

    public Serie(String nome, int anoLancamento) {
        super(nome, anoLancamento);
    }


    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public void setEpisodioPorTemporada(int episodioPorTemporada) {
        this.episodioPorTemporada = episodioPorTemporada;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void setMinutosPorEpisodios(int minutosPorEpisodios) {
        this.minutosPorEpisodios = minutosPorEpisodios;
    }

    public int getTemporada() {
        return temporada;
    }

    public int getEpisodioPorTemporada() {
        return episodioPorTemporada;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public int getMinutosPorEpisodios() {
        return minutosPorEpisodios;
    }

    @Override
    public int getDuracao() {
        return temporada * episodioPorTemporada * minutosPorEpisodios;
    }
}
