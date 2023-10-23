package med.voll.med.models.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.med.models.Endereco;

public record PacienteCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String cpf,
        @NotNull
        @Valid
        EnderecoDTO endereco) {
}
