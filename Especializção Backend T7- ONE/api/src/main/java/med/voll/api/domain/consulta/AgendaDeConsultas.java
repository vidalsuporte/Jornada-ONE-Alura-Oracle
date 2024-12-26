package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository  pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

@Autowired
private List<ValidadorAgendamentoDeConsulta> validadores;

@Autowired
private List<ValidadorCancelamentoDeConsulta> validadorCancelamentoDeConsultas;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

       if(!pacienteRepository.existsById(dados.idPaciente())){
           throw new ValidacaoException("Id do paciente informado não Existe!");
       }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não Existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Nenhum médico disponível para especialidade escolhida!");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

       var consulta = new Consulta(null,medico, paciente, dados.data(),null);
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especializade é obrigatória quando um médico não for escolhido");
        }
        System.out.println(dados);
        return  medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

        }

    public void cancelar(@Valid DadosCancelamentoConsulta dadosCancelamentoConsulta) {

        if (!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }
        validadorCancelamentoDeConsultas.forEach(v-> v.validar(dadosCancelamentoConsulta));
        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        consulta.cancelar(dadosCancelamentoConsulta.motivo());

    }
}
