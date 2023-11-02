package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import med.voll.med.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoMesmaDtHr implements ValidadorConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO agendamento){
        var medicoPossuiAgendamento = consultaRepository.existsByMedicoIdAndData(agendamento.idMedico(), agendamento.data());
        if(medicoPossuiAgendamento){
            throw new ValidacaoException("Medico já possui agendamento para essa data e horário.");
        }
    }
}
