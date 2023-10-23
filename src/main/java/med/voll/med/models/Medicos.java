package med.voll.med.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.med.models.dtos.MedicoAtualizaDTO;
import med.voll.med.models.dtos.MedicoCadastroDTO;
import med.voll.med.models.enums.Especialidade;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Medicos(MedicoCadastroDTO obj) {
        this.nome = obj.nome();
        this.email = obj.email();
        this.telefone = obj.telefone();
        this.crm = obj.crm();
        this.especialidade = obj.especialidade();
        this.endereco = new Endereco(obj.endereco());
        this.ativo = true;
    }

    public void atualizaDados(MedicoAtualizaDTO dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizaEndereco(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
