package med.voll.med.services;

import med.voll.med.exceptions.ValidacaoException;
import med.voll.med.models.Consulta;
import med.voll.med.models.Medicos;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import med.voll.med.models.dtos.CancelamentoConsultaDTO;
import med.voll.med.models.dtos.ConsultaDetalheDTO;
import med.voll.med.models.validacoes.ValidadorCancelamentoConsulta;
import med.voll.med.models.validacoes.ValidadorConsultas;
import med.voll.med.repositories.ConsultaRepository;
import med.voll.med.repositories.MedicoRepository;
import med.voll.med.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorConsultas> validadoresAgendamento;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public ConsultaDetalheDTO agendaConsulta(AgendamentoConsultaDTO agendamento){
        if(!pacienteRepository.existsById(agendamento.idPaciente())){
            throw new ValidacaoException("Id do paciente não existe.");
        }

        if(agendamento.idMedico() != null && !medicoRepository.existsById(agendamento.idMedico())){
            throw new ValidacaoException("Id do médico não existe.");
        }

        validadoresAgendamento.forEach(v -> v.validar(agendamento));

        var paciente = pacienteRepository.getReferenceById(agendamento.idPaciente());
        var medico = escolherMedico(agendamento);
        if(medico == null){
            throw new ValidacaoException("No momento não temos médicos disponíveis.");
        }


        var consulta = new Consulta(null, medico, paciente, agendamento.data(), null);
        consultaRepository.save(consulta);
        return new ConsultaDetalheDTO(consulta);
    }

    public void cancelarConsulta(CancelamentoConsultaDTO cancelamento){
        if(!consultaRepository.existsById(cancelamento.idConsulta())){
            throw new ValidacaoException("Id da consulta não existe.");
        }

        validadoresCancelamento.forEach(v -> v.validar(cancelamento));

        consultaRepository.deleteById(cancelamento.idConsulta());
    }

    private Medicos escolherMedico(AgendamentoConsultaDTO agendamento) {
        if(agendamento.idMedico() != null){
            return medicoRepository.getReferenceById(agendamento.idMedico());
        }
        if(agendamento.especialidade() == null){
            throw new ValidacaoException("Especialidade deve ser informada em caso de não escolha de médico.");
        }
        return medicoRepository.escolherMedicoAleatorio(agendamento.especialidade(), agendamento.data());
    }
}
