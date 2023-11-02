package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import med.voll.med.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(AgendamentoConsultaDTO agendamento){
        var paciente = pacienteRepository.getReferenceById(agendamento.idPaciente());
        if(!paciente.isAtivo()){
            throw new ValidacaoException("Não é possível agendar consulta para pacientes inativos.");
        }
    }
}
