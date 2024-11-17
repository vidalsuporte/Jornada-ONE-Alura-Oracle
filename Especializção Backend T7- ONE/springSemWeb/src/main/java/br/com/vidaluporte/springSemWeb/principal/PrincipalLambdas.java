package br.com.vidaluporte.springSemWeb.principal;

import br.com.vidaluporte.springSemWeb.model.DadosEpisodio;
import br.com.vidaluporte.springSemWeb.model.DadosSerie;
import br.com.vidaluporte.springSemWeb.model.DadosTemporada;
import br.com.vidaluporte.springSemWeb.model.entity.Episodio;
import br.com.vidaluporte.springSemWeb.service.ConsumoApi;
import br.com.vidaluporte.springSemWeb.service.ConverteDados;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PrincipalLambdas {
    private Scanner entrada = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=4dfbd808";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        System.out.println("Digite o nome da série pra busca:");
        var nomeSerie = entrada.nextLine();
        String dadosSerie = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") + APIKEY);
        System.out.println(ENDERECO + nomeSerie.replace(" ","+") + APIKEY);
        DadosSerie dtoDadosSerie = conversor.converteDados(dadosSerie, DadosSerie.class);
        System.out.println(dtoDadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dtoDadosSerie.totalTemporada() ; i++) {
            String dadosTemporada = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") + "&season="+i+ APIKEY);
            DadosTemporada temporada = conversor.converteDados(dadosTemporada, DadosTemporada.class);
            temporadas.add(temporada);

        }

      List<DadosEpisodio> dadosEpisodios = temporadas.stream()
              .flatMap(t->t.episodios().stream())
              .collect(Collectors.toList());

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        . map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("top 10 Epódios");
            episodios.stream()
                .sorted(Comparator.comparingDouble(Episodio::getAvaliacao).reversed())
                    .limit(10)
                    .map(e -> e.getTitulo().toUpperCase())
                .forEach(System.out::println);

        System.out.println("digite um trecho do titulo");
            var trechoTitulo = entrada.nextLine();

           Optional<Episodio> episodioBuscado = episodios.stream()
                    .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                    .findFirst();

        if(episodioBuscado.isPresent()){
            System.out.println("Episódio encontado!");
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        }else {
            System.out.println("Epsódio não encontrado!");
        }

//        System.out.println("A partir de que ano você quer listar os episodios");
//        var ano = entrada.nextInt();
//        entrada.nextLine();


//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataLancamento()!= null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episódio: " + e.getTitulo() +
//                                " Data lançamento: " + e.getDataLancamento().format(dateTimeFormatter)
//                ));



Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
        .collect(Collectors.groupingBy(Episodio::getTemporada,Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacaoPorTemporada);
        System.out.println("________XXXXX________");
DoubleSummaryStatistics est = episodios.stream()
        .filter(e-> e.getAvaliacao() > 0.0)
        .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        System.out.println("\n Média: " + decimalFormat.format(est.getAverage()) +
                            "\n Melhor: " + decimalFormat.format(est.getMax()) +
                            "\n Pior: " + decimalFormat.format(est.getMin()));




    }





}
