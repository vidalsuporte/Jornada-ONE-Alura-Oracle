package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{
    @Autowired
   private ConsultaRepository consultaRepository;

   public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
    var medicoPosuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dadosAgendamentoConsulta.idMedico(),dadosAgendamentoConsulta.data());
   if(medicoPosuiOutraConsultaNoMesmoHorario){
       throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
   }


   }
}
