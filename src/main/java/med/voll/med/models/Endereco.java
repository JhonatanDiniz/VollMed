package med.voll.med.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.med.models.dtos.EnderecoDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(EnderecoDTO obj) {
        this.logradouro = obj.logradouro();
        this.numero = obj.numero();
        this.complemento = obj.complemento();
        this.bairro = obj.bairro();
        this.cidade = obj.cidade();
        this.uf = obj.uf();
        this.cep = obj.cep();
    }

    public void atualizaEndereco(EnderecoDTO obj){
        if(obj.logradouro()!= null){
            this.logradouro = obj.logradouro();
        }
        if(obj.numero() != null){
            this.numero = obj.numero();
        }
        if(obj.complemento() != null){
            this.complemento = obj.complemento();
        }
        if(obj.bairro() != null){
            this.bairro = obj.bairro();
        }
        if(obj.cidade() != null){
            this.cidade = obj.cidade();
        }
        if(obj.uf() != null){
            this.uf = obj.uf();
        }
        if(obj.uf() != null){
            this.cep = obj.cep();
        }
    }
}
