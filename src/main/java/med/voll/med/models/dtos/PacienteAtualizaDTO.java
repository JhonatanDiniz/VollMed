package med.voll.med.models.dtos;

import jakarta.validation.constraints.NotNull;

public record PacienteAtualizaDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
        ) {
}
