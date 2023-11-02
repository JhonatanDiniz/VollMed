package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorConsultas {

    public void validar(AgendamentoConsultaDTO agendamento){
        var dataConsulta = agendamento.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisFechamento = dataConsulta.getHour() > 18;

        if(domingo || antesAbertura || depoisFechamento){
            throw new ValidacaoException("Agendamento fora do horário de expediênte.");
        }
    }

}
