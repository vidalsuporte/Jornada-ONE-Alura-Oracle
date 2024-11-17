package br.com.vidaluporte.springSemWeb.principal;

import br.com.vidaluporte.springSemWeb.model.DadosSerie;
import br.com.vidaluporte.springSemWeb.model.DadosTemporada;
import br.com.vidaluporte.springSemWeb.model.entity.Serie;
import br.com.vidaluporte.springSemWeb.service.ConsumoApi;
import br.com.vidaluporte.springSemWeb.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrincipalJPA {
    private Scanner entrada = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=4dfbd808";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    public void exibeMenu() {
        var opcao = -1;
        var menu = """
                -------------------------------
                        Menu Séries JPA
                -------------------------------
                1 - Buscar séries
                2 - Buscar episóios
                3 - Listar Séries buscadas
                
                0 - Sair
            
                """;
        do {
            System.out.println(menu);
            opcao = entrada.nextInt();
            entrada.nextLine();


            switch (opcao) {
                case 1: {
                    buscarSerieWeb();

                    break;
                }
                case 2: {
                    buscarEpisodioPorSerie();
                    break;
                }
                case 3:{
                    listarSeriesBuscadas();
                    break;
                }
                case 0: {
                    System.out.println("Saindo do Sistema");
                    break;
                }
                default:
                    System.out.println("opção inválida!");
            }

        }while(opcao != 0);
    }

private void buscarSerieWeb(){
        DadosSerie dadosSerie = getDadosSerie();
        dadosSeries.add(dadosSerie);
    System.out.println(dadosSerie);
}

private DadosSerie getDadosSerie(){
    System.out.println("Digite o nome da série para busca");
     var nomeSerie = entrada.nextLine();
    var json = consumoApi.obterDados(ENDERECO +nomeSerie.replace(" ","+")+APIKEY);
    return conversor.converteDados(json, DadosSerie.class);
}


private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
    List<DadosTemporada> temporadas = new ArrayList<>();
    for (int i = 1; i < dadosSerie.totalTemporada(); i++) {
        var json = consumoApi.obterDados(ENDERECO + dadosSerie.titulo().replace(" ","+") + "&season="+i+ APIKEY);
        DadosTemporada dadosTemporada = conversor.converteDados(json, DadosTemporada.class);
        temporadas.add(dadosTemporada);
    }
        temporadas.forEach(System.out::println);

}
 private void listarSeriesBuscadas(){
        List<Serie> series = new ArrayList<>();

        dadosSeries.forEach(System.out::println);
        series = dadosSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());

        series.stream().sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);


 }


}
