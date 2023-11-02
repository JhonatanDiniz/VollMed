package med.voll.med.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.med.models.Medicos;
import med.voll.med.models.Pacientes;
import med.voll.med.models.enums.Especialidade;

import java.time.LocalDateTime;

public record AgendamentoConsultaDTO(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade

) {
}
