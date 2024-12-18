package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



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
}
