package br.com.vidaluporte.springSemWeb.model.entity;

import br.com.vidaluporte.springSemWeb.model.DadosEpisodio;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
@Entity
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  int temporada;
    private String titulo;
    private int numero;
    private Double avaliacao;
    private LocalDate dataLancamento;

    @ManyToOne
    private Serie serie;


    public Episodio() {
    }

    public Episodio(int numero, DadosEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numero = d.numero();
        try {
            this.avaliacao = Double.valueOf(d.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(d.dataLancamento());
        }catch(DateTimeParseException ex){
            this.dataLancamento = null;
        }
        }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return  "temporada= " + temporada +
                ", titulo= '" + titulo + '\'' +
                ", numero= " + numero +
                ", avaliacao= " + avaliacao +
                ", dataLancamento= " + dataLancamento
                ;
    }
}
