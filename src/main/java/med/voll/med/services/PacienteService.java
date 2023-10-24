package med.voll.med.services;

import med.voll.med.models.Pacientes;
import med.voll.med.models.dtos.PacienteAtualizaDTO;
import med.voll.med.models.dtos.PacienteCadastroDTO;
import med.voll.med.models.dtos.PacienteListagemDTO;
import med.voll.med.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Pacientes create(PacienteCadastroDTO obj){
        Pacientes paciente = new Pacientes(obj);
        return repository.save(paciente);
    }

    public Page<PacienteListagemDTO> findAll(Pageable paginacao){
       return repository.findAll(paginacao).map(PacienteListagemDTO::new);
    }

    public void update(PacienteAtualizaDTO dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaDados(dados);
        repository.save(paciente);
    }
}
