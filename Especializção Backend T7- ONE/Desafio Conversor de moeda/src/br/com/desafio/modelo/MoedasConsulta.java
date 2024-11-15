package br.com.desafio.modelo;

import br.com.desafio.dto.DtoMoedaConsulta;

public class MoedasConsulta {

    String moedaBase;
    Moedas moedas;

    public MoedasConsulta() {
    }

    public MoedasConsulta(DtoMoedaConsulta dtoMoedaConsulta){

        this.moedaBase = dtoMoedaConsulta.base_code();
        this.moedas = dtoMoedaConsulta.conversion_rates();

    }


    @Override
    public String toString() {
        return "MoedaBase = " + moedaBase +
                ", \n" + moedas ;
    }
}
