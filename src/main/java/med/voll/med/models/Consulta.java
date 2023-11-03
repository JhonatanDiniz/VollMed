package med.voll.med.models;

import jakarta.persistence.*;
import lombok.*;
import med.voll.med.models.enums.MotivoCancelamento;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medicos medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Pacientes paciente;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivocancelamento")
    private MotivoCancelamento motivoCancelamento;
}
