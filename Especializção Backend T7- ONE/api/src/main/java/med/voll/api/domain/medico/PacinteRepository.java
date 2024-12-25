package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PacinteRepository extends JpaRepository<Medico,Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);


   @Query("""
           SELECT m  FROM Medico m
           WHERE
           m.ativo = true
           and
           m.especialidade = : especialidade
           and
           m.id not in(SELECT c.medico.id from Consulta c
                   WHERE
                    c.data = : data
                   )
           order by rand()
           limit 1
           
           
           
           """)
    public Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);


   @Query("""
           SELECT m.ativo
           FROM Medico m
           WHERE
           m.id = :idMedico
           """)
    public boolean findAtivoById(Long idMedico);
}
