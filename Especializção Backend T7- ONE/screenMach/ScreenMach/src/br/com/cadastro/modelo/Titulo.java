package br.com.cadastro.modelo;

import br.com.cadastro.dto.DTOTitulo;

import java.util.List;
import java.util.regex.Pattern;

public class Titulo implements Comparable<Titulo> {
    private String nome;
    private int anoLancamento;
    private int duracao;
    private boolean inclusoNoPlano;
    private double somaAvaliacao= 0;
    private int totalDeAvaliacoes = 0;

    public Titulo(String nome, int anoLancamento){
        this.nome = nome;
        this.anoLancamento = anoLancamento;

    }

    public Titulo(DTOTitulo dto){

     if(dto.runtime() != null || dto.year() != null || dto.title() != null) {
         this.nome = dto.title();
         List<String> indexAno = List.of(dto.year().split(" "));
                 this.anoLancamento = Integer.valueOf(indexAno.get(0));
         String[] indexDuracao = dto.runtime().split(" ");
         this.duracao = Integer.valueOf(indexDuracao[0]);
     }else throw new NullPointerException("Título não encontrado");


    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setInclusoNoPlano(boolean inclusoNoPlano) {
        this.inclusoNoPlano = inclusoNoPlano;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public boolean isInclusoNoPlano() {
        return inclusoNoPlano;
    }

    public double getSomaAvaliacao() {
        return somaAvaliacao;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void avalia(Double nota ){
        this.somaAvaliacao += nota;
        this.totalDeAvaliacoes ++;
    }

    public double pegaMedia(){

        return  somaAvaliacao / totalDeAvaliacoes;
    }


    public void exibeDados(){
    System.out.println("Nome: " + nome );
    System.out.println("Ano Lançamento: " + anoLancamento);
    System.out.println("Duração: " + getDuracao()   );

    }

    @Override
    public String toString() {
        return  "{ Nome = " + nome +
                ", Ano de Lancamento = " + anoLancamento +
                " Duração: " + duracao +
                " }";
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }
}
