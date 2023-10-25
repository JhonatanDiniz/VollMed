package med.voll.med.services;

import med.voll.med.models.Pacientes;
import med.voll.med.models.dtos.PacienteAtualizaDTO;
import med.voll.med.models.dtos.PacienteCadastroDTO;
import med.voll.med.models.dtos.PacienteDetalheDTO;
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

    public PacienteDetalheDTO create(PacienteCadastroDTO obj){
        Pacientes paciente = new Pacientes(obj);
        repository.save(paciente);
        return new PacienteDetalheDTO(paciente);
    }

    public Page<PacienteListagemDTO> findAll(Pageable paginacao){
       return repository.findAllByAtivoTrue(paginacao).map(PacienteListagemDTO::new);
    }

    public PacienteDetalheDTO findById(Long id){
        Pacientes paciente = repository.getReferenceById(id);
        return new PacienteDetalheDTO(paciente);
    }

    public void update(PacienteAtualizaDTO dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaDados(dados);
        repository.save(paciente);
    }

    public void delete(Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        repository.save(paciente);
    }
}
