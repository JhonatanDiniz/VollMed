package med.voll.med.repositories;

import med.voll.med.models.Medicos;
import med.voll.med.models.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medicos, Long> {
    Page<Medicos> findByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m
            FROM Medicos m
            WHERE m.ativo = true
            AND m.especialidade = :especialidade
            AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c WHERE c.data = :data
            )
            ORDER BY random()
            LIMIT 1          
            """)
    Medicos escolherMedicoAleatorio(Especialidade especialidade, LocalDateTime data);
}
