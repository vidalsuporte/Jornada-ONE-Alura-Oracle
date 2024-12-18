package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico json){

        medicoRepository.save(new Medico(json));
    }


    @GetMapping
    public List<DadosListagemMedicos> listar(){
        return  medicoRepository.findAll().stream().map(DadosListagemMedicos::new).collect(Collectors.toList());
    }


}

