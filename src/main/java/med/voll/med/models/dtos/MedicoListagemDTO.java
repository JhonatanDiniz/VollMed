package med.voll.med.models.dtos;

import med.voll.med.models.Medicos;
import med.voll.med.models.enums.Especialidade;

public record MedicoListagemDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public MedicoListagemDTO(Medicos medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
