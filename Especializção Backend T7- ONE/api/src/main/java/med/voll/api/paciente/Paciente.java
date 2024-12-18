package med.voll.api.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private  Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dadosCadastroPaciente){
       this.ativo = true;
        this.cpf = dadosCadastroPaciente.cpf();
        this.email = dadosCadastroPaciente.email();
        this.nome = dadosCadastroPaciente.nome();
        this.telefone = dadosCadastroPaciente.telefone();
        this.endereco = new Endereco(dadosCadastroPaciente.endereco());

    }


    public void atualizaDados(@Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {

        if(dadosAtualizacaoPaciente.nome() != null) {
            this.nome = dadosAtualizacaoPaciente.nome();
        }
        if(dadosAtualizacaoPaciente.telefone() != null) {
            this.telefone = dadosAtualizacaoPaciente.telefone();
        }
        if(dadosAtualizacaoPaciente.endereco() != null) {
            this.endereco.atualizarDados(dadosAtualizacaoPaciente.endereco());
        }


    }

    public void excluir(){
        this.ativo = false;
    }



}
