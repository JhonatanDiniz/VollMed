package med.voll.med.models.dtos;

import med.voll.med.models.Endereco;
import med.voll.med.models.Pacientes;

public record PacienteDetalheDTO(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, boolean ativo) {

    public PacienteDetalheDTO(Pacientes paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco(), paciente.isAtivo());
    }
}
