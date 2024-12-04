package br.com.vidaluporte.springSemWeb.dto;

import br.com.vidaluporte.springSemWeb.model.entity.Episodio;
import br.com.vidaluporte.springSemWeb.model.enums.Categoria;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public record SerieDTO( Long id,


 String titulo,
 int totalTemporada,
 Double avaliacao,

 Categoria genero,
 String atores,
 String poster,
 String sinopse
) {
}
