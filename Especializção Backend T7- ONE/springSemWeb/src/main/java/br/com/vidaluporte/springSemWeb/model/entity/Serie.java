package br.com.vidaluporte.springSemWeb.model.entity;

import br.com.vidaluporte.springSemWeb.model.DadosSerie;
import br.com.vidaluporte.springSemWeb.model.enums.Categoria;
import br.com.vidaluporte.springSemWeb.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private int totalTemporada;
    private Double avaliacao;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    public Serie() {
    }

    public Serie(DadosSerie dadosSerie){
      this.titulo = dadosSerie.titulo();
      this.totalTemporada = dadosSerie.totalTemporada();
      this.avaliacao = OptionalDouble.of(Double.parseDouble(dadosSerie.avaliacao())).orElse(0.0);
      this.genero = Categoria.formString(dadosSerie.genero().split(",")[0].trim());
      this.atores = dadosSerie.atores();
      this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
      this.poster = dadosSerie.poster();
  }


    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", eposódios = " + episodios +
                '}';
    }
}
