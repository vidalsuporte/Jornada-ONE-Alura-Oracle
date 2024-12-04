package br.com.vidaluporte.springSemWeb.dto;

import java.time.LocalDate;

public record EpisodioDTO(
        int temporada,
         String titulo,
         int numeroEpisodio) {

}
