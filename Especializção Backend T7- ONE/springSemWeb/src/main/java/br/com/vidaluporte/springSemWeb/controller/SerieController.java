package br.com.vidaluporte.springSemWeb.controller;


import br.com.vidaluporte.springSemWeb.dto.EpisodioDTO;
import br.com.vidaluporte.springSemWeb.dto.SerieDTO;
import br.com.vidaluporte.springSemWeb.model.enums.Categoria;
import br.com.vidaluporte.springSemWeb.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    SerieService serieService;


        @GetMapping
    public List<SerieDTO> obterSeries(){

            return serieService.obterTodasAsSeries();
        }


    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series(){

        return serieService.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos(){

            return serieService.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorID(@PathVariable Long id){
            return serieService.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){

return serieService.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obterPorTemporadas(@PathVariable Long id, @PathVariable Long numeroTemporada){

        return serieService.obterPorTemporadas(id, numeroTemporada);
    }

    @GetMapping("/categoria/{categoria}")
    public List<SerieDTO>  obterPorCategoria(@PathVariable String categoria){

    return serieService.obterPorCategoria( categoria);


    }



}
