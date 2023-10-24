package med.voll.med.models.dtos;

import med.voll.med.models.Endereco;
import med.voll.med.models.Medicos;
import med.voll.med.models.enums.Especialidade;

public record MedicoDetalheDTO(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco, boolean ativo) {

    public MedicoDetalheDTO(Medicos medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco(), medico.isAtivo());
    }
}
