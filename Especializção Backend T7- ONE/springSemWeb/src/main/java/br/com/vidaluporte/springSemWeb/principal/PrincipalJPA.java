package br.com.vidaluporte.springSemWeb.principal;

import br.com.vidaluporte.springSemWeb.model.DadosSerie;
import br.com.vidaluporte.springSemWeb.model.DadosTemporada;
import br.com.vidaluporte.springSemWeb.model.entity.Episodio;
import br.com.vidaluporte.springSemWeb.model.entity.Serie;
import br.com.vidaluporte.springSemWeb.model.enums.Categoria;
import br.com.vidaluporte.springSemWeb.repository.SerieRepository;
import br.com.vidaluporte.springSemWeb.service.ConsumoApi;
import br.com.vidaluporte.springSemWeb.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class PrincipalJPA {
    private Scanner entrada = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=4dfbd808";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
     private SerieRepository repositorio;
    private  List<Serie> series = new ArrayList<>();
    private   Optional<Serie> serieBuscada;
    public PrincipalJPA(SerieRepository repositorio) {
           this.repositorio = repositorio;


    }


    public void exibeMenu() {
        var opcao = -1;
        var menu = """
                -------------------------------
                        Menu Séries JPA
                -------------------------------
                1 - Buscar séries
                2 - Buscar episóios
                3 - Listar Séries buscadas
                4 - Buscar Série por Título
                5 - Bucar Série por ator
                6 - Top Séries
                7 - Busca por categoria
                8 - Busca Séries por temporadas e avaliação
                9 - Busca Epsódio por trecho
                10 - Top 5 episódios por série
                11 - Episódios a partir de uma data
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
                case 4:{
                    buscarSeriePorTitulo();
                    break;
                }
                case 5:{
                    buscarSeriePorAtor();
                    break;
                }
                case 6:{
                    top5Series();
                    break;
                }
                case 7:{

                    buscaPorCategoria();
                    break;
                }
                case 8:{

                    buscaSeriePorTemporadaEAvaliacao();
                    break;
                }
                case 9:{

                    buscarEpisodioPorTrecho();

                    break;
                }
                case 10:{
                    topEpisodiosoPorserie();

                    break;
                }

                case 11:{
                    buscarEpisodioDepoisData();

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
        Serie serie = new Serie(dadosSerie);
        repositorio.save(serie);
    System.out.println(new Serie(dadosSerie));
}

private DadosSerie getDadosSerie(){
    System.out.println("Digite o nome da série para busca");
     var nomeSerie = entrada.nextLine();
    var json = consumoApi.obterDados(ENDERECO +nomeSerie.replace(" ","+")+APIKEY);
    return conversor.converteDados(json, DadosSerie.class);
}


private void buscarEpisodioPorSerie(){
    listarSeriesBuscadas();
    System.out.println("Escolha um série pelo Nome: ");
    var nomeSerie = entrada.nextLine();

    Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

    if(serie.isPresent()) {
        var serieEncontrada = serie.get();
        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= serieEncontrada.getTotalTemporada(); i++) {
            var json = consumoApi.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = conversor.converteDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(d -> d.episodios().stream().map(e -> new Episodio(d.numero(), e)))
                .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
    }else {
        System.out.println("Série encontrada");
    }
}
 private void listarSeriesBuscadas(){

         series =  repositorio.findAll();

        series.stream().sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);


 }

    private void buscarSeriePorTitulo() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = entrada.nextLine();
 serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
    if (serieBuscada.isPresent()){
        System.out.println("Dados da Série: " + serieBuscada.get());
    }else {
        System.out.println("Série não encontrada!");
    }
    }

    private void buscarSeriePorAtor() {
        System.out.println("Digite o nome do ator: ");
        var nomeAtor = entrada.nextLine();
        System.out.println("A partir de que avliação: ");
        var avaliacao = entrada.nextDouble();

        List<Serie> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor,avaliacao);
        if(!seriesEncontradas.isEmpty()) {
           seriesEncontradas
                   .forEach(System.out::println);
        }else {
            System.out.println("Ator " + nomeAtor + " não possui filme cadastrado no banco de dado.");
        }
    }

    private void top5Series() {

        List<Serie> top5Series = repositorio.findTop5ByOrderByAvaliacaoDesc();
        top5Series.stream()
                .forEach(s -> System.out.println("Título: " + s.getTitulo() + ", Avaliação: " +s.getAvaliacao()));



    }
    private void buscaPorCategoria() {
        System.out.println("Digite a categoria desejada: ");
        var genero = entrada.nextLine();
        Categoria categoria = Categoria.formPortugues(genero);
        List<Serie> seriesCategoria = repositorio.findByGenero(categoria);
        System.out.println("Série da categoria: " + genero);
        seriesCategoria.stream()
                .forEach(System.out::println);


    }

    private void buscaSeriePorTemporadaEAvaliacao() {
        System.out.println("Digite o numero máximo de Temporadas: ");
        var numeroTemporadas = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o minimo de avaliação: ");
        var avaliacao = entrada.nextDouble();

        List<Serie> series = repositorio.seriesPorTemporadaEAvaliacao(numeroTemporadas,avaliacao);

        series.stream()
                .forEach(System.out::println);

    }
    private void buscarEpisodioPorTrecho() {
        System.out.println("Qual episódio desja buscar?");
        var trechoEpsodio = entrada.nextLine();
        List<Episodio> episodiosEncontrados = repositorio.episodiosPorTrecho(trechoEpsodio);
            episodiosEncontrados.forEach(e -> System.out.printf("Série: %s Temporada %s - Episódio %s - %s\n",
                    e.getSerie().getTitulo(), e.getTemporada(),
                    e.getNumero(), e.getTitulo()));



    }

    private void topEpisodiosoPorserie()
    {
        buscarSeriePorTitulo();
        if(serieBuscada.isPresent()){

            Serie serie = serieBuscada.get();
            List<Episodio> topEpisodios = repositorio.topEpsodiosPorSerie(serie);
            topEpisodios.forEach(e -> System.out.printf("Série: %s Temporada %s - Avaliação: %f- Episódio %s - %s\n",
                    e.getSerie().getTitulo(), e.getTemporada(), e.getAvaliacao(),
                    e.getNumero(), e.getTitulo()));

        }

    }

    private void buscarEpisodioDepoisData() {
    buscarSeriePorTitulo();
    if(serieBuscada.isPresent()){
        Serie serie = serieBuscada.get();
        System.out.println("Digite o ano limite de Lançamento de episódios");
        var anoLimite = entrada.nextInt();
        entrada.nextLine();
        List<Episodio> epiodiosAno = repositorio.episodiosPorAno(serie, anoLimite);

        epiodiosAno.forEach(e -> System.out.printf("Série: %s Temporada %s - Avaliação: %f- Episódio %s - %s\n",
                e.getSerie().getTitulo(), e.getTemporada(), e.getAvaliacao(),
                e.getNumero(), e.getTitulo()));



    }




    }


}
