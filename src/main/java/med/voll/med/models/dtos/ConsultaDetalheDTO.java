package med.voll.med.models.dtos;

import med.voll.med.models.Consulta;

import java.time.LocalDateTime;

public record ConsultaDetalheDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public ConsultaDetalheDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
