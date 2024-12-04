package br.com.vidaluporte.springSemWeb.service;

import br.com.vidaluporte.springSemWeb.dto.EpisodioDTO;
import br.com.vidaluporte.springSemWeb.dto.SerieDTO;
import br.com.vidaluporte.springSemWeb.model.entity.Serie;
import br.com.vidaluporte.springSemWeb.model.enums.Categoria;
import br.com.vidaluporte.springSemWeb.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
   @Autowired
    private SerieRepository repositorio;

   public List<SerieDTO> obterTodasAsSeries(){

       return converteDados(repositorio.findAll());


   }


    public List<SerieDTO> obterTop5Series() {
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());



    }

    private List<SerieDTO> converteDados (List<Serie> series){

       return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporada(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }


    public List<SerieDTO> obterLancamentos() {

       return converteDados(repositorio.top5Lancamento());

    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporada(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {

        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
return s.getEpisodios().stream()
        .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumero()))
        .collect(Collectors.toList());
        }
        return null;


   }

    public List<EpisodioDTO> obterPorTemporadas(Long id, Long numeroTemporada) {
    return repositorio.obterEpodioPorTemporada(id,numeroTemporada)
            .stream()
            .map(e-> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumero()))
            .collect(Collectors.toList());


   }

    public List<SerieDTO> obterPorCategoria(String categoria) {

       return converteDados(repositorio.findByGenero(Categoria.formPortugues(categoria)));


    }
}
