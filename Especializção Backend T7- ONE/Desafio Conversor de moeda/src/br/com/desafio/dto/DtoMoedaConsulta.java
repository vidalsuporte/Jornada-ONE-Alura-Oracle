package br.com.desafio.dto;

import br.com.desafio.modelo.Moedas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record DtoMoedaConsulta(String base_code, Moedas conversion_rates) {

}
