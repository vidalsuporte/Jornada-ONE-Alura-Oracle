package br.com.alura.desafioFipe.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosVeiculo(@JsonAlias("TipoVeiculo") String tipoVeiculo,
                           @JsonAlias("Valor") String valor,
                           @JsonAlias("Marca") String marca,
                           @JsonAlias("Modelo") String modelo,
                           @JsonAlias("AnoModelo") String anoModelo,
                           @JsonAlias("Combustivel") String combustivel,
                           @JsonAlias("CodigoFipe") String codigoFipe,
                           @JsonAlias("MesReferencia") String mesReferencia,
                           @JsonAlias("SiglaCombustivel") String SiglaCombustivel) {


    @Override
    public String toString() {
        return "Marca: " + marca + "\n" +
                "Modelo: " + modelo + "\n" +
                "Ano: " + anoModelo + "\n" +
                "Mês Referência: " + mesReferencia + "\n" +
                "Combustível: " + combustivel + "\n" +
                "Valor: " + valor;
    }


}
