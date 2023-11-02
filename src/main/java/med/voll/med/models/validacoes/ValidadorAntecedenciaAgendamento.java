package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaAgendamento implements ValidadorConsultas {

    public void validar(AgendamentoConsultaDTO agendamento){
        var dataAgendamento = agendamento.data();
        var dataAtual = LocalDateTime.now();
        var diferenca = Duration.between(dataAtual, dataAgendamento).toMinutes();

        if(diferenca < 30){
            throw new ValidacaoException("Agendamento deve ser feito com no mínimo 30 minutos de antecedência.");
        }
    }
}
