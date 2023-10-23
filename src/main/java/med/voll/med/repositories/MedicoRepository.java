package med.voll.med.repositories;

import med.voll.med.models.Medicos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medicos, Long> {
    Page<Medicos> findByAtivoTrue(Pageable paginacao);
}
