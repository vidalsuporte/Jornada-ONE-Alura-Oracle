package br.com.vidaluporte.springSemWeb.repository;

import br.com.vidaluporte.springSemWeb.model.entity.Serie;
import br.com.vidaluporte.springSemWeb.model.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {

Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);


    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadaLessThanEqualAndAvaliacaoGreaterThanEqual(int numeroTemporadas, double avaliacao);

@Query("SELECT s FROM Serie s where s.totalTemporada <= :temporadas and s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int temporadas, double avaliacao);


}
