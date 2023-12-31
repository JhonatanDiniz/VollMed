package med.voll.med.services;

import med.voll.med.models.Medicos;
import med.voll.med.models.dtos.MedicoAtualizaDTO;
import med.voll.med.models.dtos.MedicoCadastroDTO;
import med.voll.med.models.dtos.MedicoDetalheDTO;
import med.voll.med.models.dtos.MedicoListagemDTO;
import med.voll.med.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public MedicoDetalheDTO create(MedicoCadastroDTO obj){
        Medicos medico = new Medicos(obj);
        repository.save(medico);
        return new MedicoDetalheDTO(medico);
    }

    public Page<MedicoListagemDTO> findAll(Pageable paginacao){
        return repository.findByAtivoTrue(paginacao).map(MedicoListagemDTO::new);
    }

    public MedicoDetalheDTO findById(Long id){
        Medicos medico = repository.getReferenceById(id);
        return new MedicoDetalheDTO(medico);
    }

    public void update(MedicoAtualizaDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizaDados(dados);
        repository.save(medico);
    }

    public void delete(Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        repository.save(medico);
    }
}
