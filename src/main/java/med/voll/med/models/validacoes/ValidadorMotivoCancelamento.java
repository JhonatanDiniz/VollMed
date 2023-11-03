package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.CancelamentoConsultaDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoCancelamento implements ValidadorCancelamentoConsulta {

    public void validar(CancelamentoConsultaDTO cancelamento){
        if(cancelamento.motivoCancelamento() == null){
            throw new ValidacaoException("É necessário informar o motivo do cancelamento.");
        }
    }
}
