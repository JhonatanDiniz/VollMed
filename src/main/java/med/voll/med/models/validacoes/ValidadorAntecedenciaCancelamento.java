package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.CancelamentoConsultaDTO;
import med.voll.med.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaCancelamento implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(CancelamentoConsultaDTO cancelamento){
        var consulta = consultaRepository.getReferenceById(cancelamento.idConsulta());
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, consulta.getData()).toHours();

        if(diferenca < 24){
            throw new ValidacaoException("Só é permitido cancelar consulta com 24h de antecedência.");
        }
    }
}
