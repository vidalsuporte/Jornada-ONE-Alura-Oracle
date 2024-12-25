package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long idMedico,

       @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data) {
}
