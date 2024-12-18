package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.DadosAtualizacaoMedico;


@Getter
@AllArgsConstructor
@Embeddable
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String uf;
    private String complemento;
    private  String numero;
    private  String cidade;

    public Endereco(){

    }


    public Endereco(DadosEndereco endereco) {
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();

    }

    public void atualizarDados(@Valid DadosEndereco endereco ) {
        if(endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }
        if(endereco.cep() != null) {
            this.cep = endereco.cep();
        }
        if(endereco.uf() != null) {
            this.uf = endereco.uf();
        }
        if(endereco.logradouro() != null) {
            this.logradouro = endereco.logradouro();
        }
        if(endereco.numero() != null) {
            this.numero = endereco.numero();
        }
        if(endereco.complemento() != null) {
            this.complemento = endereco.complemento();
        }
        if(endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }



    }
}
