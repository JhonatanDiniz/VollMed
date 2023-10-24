package med.voll.med.models.dtos;

import med.voll.med.models.Pacientes;

public record PacienteListagemDTO(String nome, String email, String cpf) {

    public PacienteListagemDTO(Pacientes pacientes){
        this(pacientes.getNome(), pacientes.getEmail(), pacientes.getCpf());
    }
}
