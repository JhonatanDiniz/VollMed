package med.voll.med.models.validacoes;

import med.voll.med.models.dtos.AgendamentoConsultaDTO;

public interface ValidadorConsultas {

    void validar(AgendamentoConsultaDTO agendamento);
}
