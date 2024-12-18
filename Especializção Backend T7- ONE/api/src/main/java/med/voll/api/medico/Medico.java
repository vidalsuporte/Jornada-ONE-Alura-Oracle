package med.voll.api.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity(name = "Medico")
@Table(name = "medicos")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private  String email;
    private String crm;
    private String telefone;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;


    public Medico(DadosCadastroMedico json) {
       this.ativo = true;
        this.crm = json.crm();
        this.email = json.email();
        this.endereco = new Endereco(json.endereco());
        this.nome = json.nome();
        this.especialidade = json.especialidade();
        this.telefone = json.telefone();


    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoMedico json) {

        if(json.nome() != null){
            this.nome = json.nome();
        }
        if(json.telefone() != null){
            this.telefone = json.telefone();
        }
        if(json.endereco() != null){
            this.endereco.atualizarDados(json.endereco());
        }



    }

    public void excluir() {
        this.ativo=false;
    }
}
