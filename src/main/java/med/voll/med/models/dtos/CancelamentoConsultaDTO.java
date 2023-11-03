package med.voll.med.models.dtos;

import jakarta.validation.constraints.NotNull;
import med.voll.med.models.enums.MotivoCancelamento;

public record CancelamentoConsultaDTO(@NotNull Long idConsulta, @NotNull MotivoCancelamento motivoCancelamento) {
}
