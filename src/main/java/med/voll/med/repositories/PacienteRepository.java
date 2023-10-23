package med.voll.med.repositories;

import med.voll.med.models.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Pacientes, Long> {
}
