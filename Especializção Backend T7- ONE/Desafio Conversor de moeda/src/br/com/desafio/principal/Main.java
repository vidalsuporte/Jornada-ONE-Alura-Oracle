package br.com.desafio.principal;

import br.com.desafio.dto.DtoMoedaConsulta;
import br.com.desafio.json.BuscaMoeda;
import br.com.desafio.modelo.Moedas;
import br.com.desafio.modelo.MoedasConsulta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String moedas = """
                      Opções de Moedas:
                      Digite o código com 3 Letras:
                      USD - Dolar americano  /  BRL - Real Brasileiro  / ARS Peso Argentino
                      BOB - Bolivar Boliviano  / CAD - Dolar Canadense  /  CLP - Peso Cheileno
                      EUR Euro  /  JPY - Iene Japonês  /  RUB Rublo Russo
                      """;
    String menu= """
                  Desavio Conversor de Moedas
            _______________________________________
            Digite a operação desejada:
            1 - Consulta de Cotação atual da moeda escolhida em 8 moedas diferentes.
            2 - Converter uma quantia expecífica em 8 moedas
            3 - Escolher conversão direta entre duas moedas \n
            """;
        System.out.printf(menu);
        System.out.printf("Opção: ");
        switch (entrada.nextInt()) {
            case 1: {

                System.out.println(moedas);
                BuscaMoeda buscaMoeda = new BuscaMoeda();
                var moedasConsulta = new MoedasConsulta(buscaMoeda.buscaMoeda());
                System.out.println(moedasConsulta);
                break;
            }
            case 2:{
                System.out.println(moedas);
                System.out.println("Digite o código da a ser convertida:");
                String moedaConvertida = entrada.next().toUpperCase();
                System.out.println("Digite o valor a ser convertido:");
                Double quantia = entrada.nextDouble();
                Moedas moeda = new BuscaMoeda().getMoeda(moedaConvertida).conversion_rates();
                System.out.println("A quantia de " + quantia + " em " + moedaConvertida + " é igual: ");
                moeda.converteMoedaEmLote(quantia,new BuscaMoeda().getMoeda(moedaConvertida).conversion_rates());
                break;
            }
            case 3:{
                System.out.println(moedas);
                System.out.println("Digite o código da moeda a ser convertida:");
                String moedaBase = entrada.next().toUpperCase();
                System.out.println("Digite o código da moeda convertida:");
                String moedaConvertida = entrada.next().toUpperCase();
                System.out.println("Digite o valor a ser convertido:");
                Double quantia = entrada.nextDouble();
                Moedas moeda = new BuscaMoeda().getMoeda(moedaBase).conversion_rates();
                System.out.println("O valor " + String.format("%.2f",quantia) + " em " + moedaBase +
                        " equivale à " + String.format("%.2f", moeda.converteMoeda(quantia,moedaConvertida)) +
                        " em " + moedaConvertida + '.');
                break;
            }

            default:{
                System.out.printf("Opção inválida.");
                break;
            }

        }











    }

}