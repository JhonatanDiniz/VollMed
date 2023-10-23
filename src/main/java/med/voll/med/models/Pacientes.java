package med.voll.med.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import med.voll.med.models.dtos.PacienteCadastroDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Endereco endereco;

    public Pacientes(PacienteCadastroDTO obj) {
        this.nome = obj.nome();
        this.email = obj.email();
        this.telefone = obj.telefone();
        this.cpf = obj.cpf();
        this.endereco = new Endereco(obj.endereco());
    }
}
