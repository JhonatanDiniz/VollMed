package med.voll.med.services;

import med.voll.med.models.Pacientes;
import med.voll.med.models.dtos.PacienteCadastroDTO;
import med.voll.med.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Pacientes create(PacienteCadastroDTO obj){
        Pacientes paciente = new Pacientes(obj);
        return repository.save(paciente);
    }
}
