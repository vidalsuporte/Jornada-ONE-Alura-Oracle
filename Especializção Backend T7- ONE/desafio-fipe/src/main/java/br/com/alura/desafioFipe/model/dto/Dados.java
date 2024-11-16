package br.com.alura.desafioFipe.model.dto;


public record Dados(String codigo, String nome) {

    public String toString() {
        return " Código: " + codigo + " Referência: " + nome;

    }


}
