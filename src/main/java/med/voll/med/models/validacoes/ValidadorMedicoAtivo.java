package med.voll.med.models.validacoes;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import med.voll.med.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(AgendamentoConsultaDTO agendamento){
        if(agendamento.idMedico() == null){
            return;
        }

        var medico = medicoRepository.getReferenceById(agendamento.idMedico());
        if(!medico.isAtivo()){
            throw new ValidacaoException("Não é possível fazer agendamento para médicos inativos.");
        }
    }
}
