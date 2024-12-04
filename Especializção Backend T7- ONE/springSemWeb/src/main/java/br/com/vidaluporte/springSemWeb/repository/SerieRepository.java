package br.com.vidaluporte.springSemWeb.repository;

import br.com.vidaluporte.springSemWeb.model.entity.Episodio;
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

@Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpsodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpsodio);

@Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s =:serie ORDER BY e.avaliacao DESC LIMIT 5 ")
    List<Episodio> topEpsodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s =:serie AND YEAR(e.dataLancamento) >= :anoLimite")
    List<Episodio> episodiosPorAno(Serie serie, int anoLimite);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> top5Lancamento();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numero")
        List<Episodio> obterEpodioPorTemporada(Long id, Long numero);


}



