package br.com.alura.desafioFipe.principal;

import br.com.alura.desafioFipe.consumoApi.ConsumoApi;
import br.com.alura.desafioFipe.consumoApi.ConverteDados;
import br.com.alura.desafioFipe.model.dto.Dados;
import br.com.alura.desafioFipe.model.dto.DadosVeiculo;
import br.com.alura.desafioFipe.model.dto.Modelos;


import java.util.Comparator;
import java.util.Scanner;

public class Principal {

    public Principal() {
    }

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private int opcao = -1;
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    private Scanner entrada = new Scanner(System.in);

    private String menu = """
            \n-----------------------------
                  Desafio Tabela Fipe
            -----------------------------
            Escolha a opção de veículo:
            1 - Carro
            2 - Moto
            3 - Caminhão / Ônibus
            0 - Para Finalizar o Programa
            
            Digite a opção:
            """;

    public void exibirMenu() {

        while (opcao != 0) {
            String consulta = ENDERECO;
            System.out.println(menu);

            opcao = entrada.nextInt();
            entrada.nextLine();


            switch (opcao) {
                case 1: {

                    consulta += "carros/marcas/";

                    break;
                }
                case 2: {
                    consulta += "motos/marcas/";

                    break;
                }
                case 3: {
                    consulta += "caminhoes/marcas/";
                    break;
                }
                case 0: {
                    opcao = 0;
                    System.out.println("Obrigado por usar nossa API.");
                    break;
                }

                default: {
                    System.out.println("Opção inválida");
                }
            }

            if (opcao != 0) {
                var json = consumoApi.obterDados(consulta);

                var lista = converteDados.converteLista(json, Dados.class);
                lista.stream()
                        .sorted(Comparator.comparingInt(value -> value.codigo().hashCode()))
                        .forEach(System.out::println);

                System.out.println("---------------------------------------\n");
                System.out.println("Digite o código do fabricante:");

                String cod = entrada.nextLine();
                consulta += cod + "/modelos/";

                json = consumoApi.obterDados(consulta);


                var modelosList = converteDados.converteDados(json, Modelos.class);
                modelosList.modelos().stream()
                        .sorted(Comparator.comparing(Dados::nome))
                        .forEach(System.out::println);

                System.out.println("---------------------------------------\n");

                System.out.println("Digite S para filtrar por Nome ou digite o código");
                String filtro = entrada.nextLine();

                if (filtro.equalsIgnoreCase("S")) {

                    System.out.println("Digite Parte do nome para filtrar por Nome:");
                    filtro = entrada.nextLine();
                    String finalFiltro = filtro;
                    modelosList.modelos().stream()
                            .filter(m -> m.nome().toUpperCase().contains(finalFiltro.toUpperCase()))
                            .sorted(Comparator.comparing(Dados::nome))
                            .forEach(System.out::println);
                    System.out.println("Digite o código do modelo:");
                } else {
                    System.out.println("Digite o código do modelo:");
                }

                cod = entrada.nextLine();
                consulta += cod + "/anos/";

                json = consumoApi.obterDados(consulta);

                var listaAno = converteDados.converteLista(json, Dados.class);
                listaAno.stream()
                        .sorted(Comparator.comparingInt(value -> value.codigo().hashCode()))
                        .forEach(System.out::println);


                System.out.println("Digite o código o ano ou \n S para listar o valor de todo os anos disponíveis ");
                var ano = entrada.nextLine();
                if (!ano.equalsIgnoreCase("S")) {
                    json = consumoApi.obterDados(consulta + ano);
                    var dadosVeiculo = converteDados.converteDados(json, DadosVeiculo.class);
                    System.out.println("-----------------------------------------------");
                    System.out.println(dadosVeiculo);
                    System.out.println("-----------------------------------------------");
                } else {

                    for (int i = listaAno.size(); i > 0; i--) {

                        json = consumoApi.obterDados(consulta + listaAno.get(i - 1).codigo());
                        var dadosVeiculo = converteDados.converteDados(json, DadosVeiculo.class);
                        System.out.println("-----------------------------------------------");
                        System.out.println(dadosVeiculo);
                        System.out.println("-----------------------------------------------");
                    }


                }

            }


            System.out.println("\n __________________________________");
            System.out.println("Fim da consulta - \nDigite 1 pra nova consulta ou 0 para sair: ");

            opcao = entrada.nextInt();
            entrada.nextLine();


        }

    }

}


