package med.voll.med.models.dtos;

import jakarta.validation.constraints.NotNull;
import med.voll.med.models.Endereco;

public record MedicoAtualizaDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco) {
}
