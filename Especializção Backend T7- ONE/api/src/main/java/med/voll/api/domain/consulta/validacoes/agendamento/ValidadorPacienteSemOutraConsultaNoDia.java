package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository repository;

    public void  validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        var primeiroHorario = dadosAgendamentoConsulta.data().withHour(7);
        var ultimoHorario = dadosAgendamentoConsulta.data().withHour(18);
        var pacientePossuiOutraConsulta = repository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsulta.idPaciente(),primeiroHorario,ultimoHorario);
        if(pacientePossuiOutraConsulta){
            throw new ValidacaoException("Paciente já possui outra consulta agendada nese dia");
        }

    }
}
