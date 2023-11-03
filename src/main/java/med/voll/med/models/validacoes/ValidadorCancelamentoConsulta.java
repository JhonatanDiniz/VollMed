package med.voll.med.models.validacoes;

import med.voll.med.models.dtos.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoConsulta {

    void validar(CancelamentoConsultaDTO cancelamento);
}
