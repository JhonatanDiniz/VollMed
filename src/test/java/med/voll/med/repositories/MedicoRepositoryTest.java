package med.voll.med.repositories;

import med.voll.med.models.Consulta;
import med.voll.med.models.Medicos;
import med.voll.med.models.Pacientes;
import med.voll.med.models.dtos.EnderecoDTO;
import med.voll.med.models.dtos.MedicoCadastroDTO;
import med.voll.med.models.dtos.PacienteCadastroDTO;
import med.voll.med.models.enums.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando um único médico cadastrado não está disponível.")
    void escolherMedicoAleatorioCenario1() {
        var proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastraMedico("Jhonatan Gomes Diniz", "jhonatangomesdiniz@gmail.com", "61981867130", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Scheila Nunes", "scheila@gmail.com", "61983031545", "80938051091");
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez);

        var medicoDisponivel = medicoRepository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        assertThat(medicoDisponivel).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando estiver disponível na data.")
    void escolherMedicoAleatorioCenario2() {
        var proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastraMedico("Jhonatan Gomes Diniz", "jhonatangomesdiniz@gmail.com", "61981867130", "123456", Especialidade.CARDIOLOGIA);

        var medicoDisponivel = medicoRepository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        assertThat(medicoDisponivel).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medicos medico, Pacientes paciente, LocalDateTime data){
        em.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medicos cadastraMedico(String nome, String email, String telefone, String crm, Especialidade especialidade){
        var medico = new Medicos(dadosMedico(nome, email, telefone, crm, Especialidade.CARDIOLOGIA));
        em.persist(medico);
        return medico;
    }

    private Pacientes cadastrarPaciente(String nome, String email, String telefone, String cpf){
        var paciente = new Pacientes(dadosPaciente(nome, email, telefone, cpf));
        em.persist(paciente);
        return paciente;
    }

    private MedicoCadastroDTO dadosMedico(String nome, String email, String telefone, String crm, Especialidade especialidade){
        return new MedicoCadastroDTO(
                nome,
                email,
                telefone,
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PacienteCadastroDTO dadosPaciente(String nome, String email, String telefone, String cpf){
        return new PacienteCadastroDTO(
                nome,
                email,
                telefone,
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "QI 09",
                "307",
                "BLOCO A",
                "GUARÁ I",
                "BRASÍLIA",
                "DF",
                "71020018"
        );
    }
}