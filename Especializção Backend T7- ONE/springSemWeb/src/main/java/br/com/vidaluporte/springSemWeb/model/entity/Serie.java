package br.com.vidaluporte.springSemWeb.model.entity;

import br.com.vidaluporte.springSemWeb.model.DadosSerie;
import br.com.vidaluporte.springSemWeb.model.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Optional;
import java.util.OptionalDouble;

public class Serie {

   private String titulo;
    private int totalTemporada;
    private Double avaliacao;
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;



  public Serie(DadosSerie dadosSerie){
      this.titulo = dadosSerie.titulo();
      this.totalTemporada = dadosSerie.totalTemporada();
      this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
      this.genero = Categoria.formString(dadosSerie.genero().split(",")[0].trim());
      this.atores = dadosSerie.atores();
      this.sinopse = dadosSerie.sinopse();
      this.poster = dadosSerie.poster();
  }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotalTemporada() {
        return totalTemporada;
    }

    public void setTotalTemporada(int totalTemporada) {
        this.totalTemporada = totalTemporada;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return  "genero = " + genero +
                ", titulo = '" + titulo + '\'' +
                ", totalTemporada = " + totalTemporada +
                ", avaliacao = " + avaliacao +
                ", atores = '" + atores + '\'' +
                ", poster ='" + poster + '\'' +
                ", sinopse = '" + sinopse + '\'' +
                '}';
    }
}
