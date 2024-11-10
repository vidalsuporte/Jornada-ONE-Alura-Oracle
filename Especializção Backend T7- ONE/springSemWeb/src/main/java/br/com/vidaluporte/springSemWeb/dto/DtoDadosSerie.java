package br.com.vidaluporte.springSemWeb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DtoDadosSerie(@JsonAlias("Title") String titulo,
                            @JsonAlias("totalSeasons") int totalTemporada,
                            @JsonAlias("imdbRating") String avaliacao) {
}
