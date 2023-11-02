package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import med.voll.med.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMaisAgendamentoDia implements ValidadorConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO agendamento){
        var primeiroHorario = agendamento.data().withHour(7);
        var ultimoHorario = agendamento.data().withHour(18);
        var pacienteComConsulta = consultaRepository.existsByPacienteIdAndDataBetween(agendamento.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacienteComConsulta){
            throw new ValidacaoException("Paciente já possui agendamento para essa data.");
        }
    }
}
