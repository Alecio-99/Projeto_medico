package med.vall.api.repository;

import med.vall.api.DTO.DadosCadastroPaciente;
import med.vall.api.DTO.DadosEndereco;
import med.vall.api.DTO.MedicosDTO;
import med.vall.api.entity.EntityConsulta;
import med.vall.api.entity.EntityMedico;
import med.vall.api.entity.EntityPaciente;
import med.vall.api.enuns.EnumEspecialidades;
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
class RepositoryMedicoTest {

    @Autowired
    private RepositoryMedico repositoryMedico;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado não estiver disponivel na data")
    void escolherMedicoAleatorioCenario1() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
       var medico = cadastrarMedico("Luan", "luan@gmail.com","123456", EnumEspecialidades.CARDIOLOGIA);
       var paciente = cadastrarPaciente("Alecio","alecio@gmail.com","61529009308");
       cadastrarConsulta(medico, paciente, proximaSegundaAs10);

      var medicoLivre = repositoryMedico.escolherMedicoAleatorio(EnumEspecialidades.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }
    @Test
    @DisplayName("Deveria devolver medico quando unico medico cadastrado disponivel na data")
    void escolherMedicoAleatorioCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Luan", "luan@gmail.com","123456", EnumEspecialidades.CARDIOLOGIA);

        var medicoLivre = repositoryMedico.escolherMedicoAleatorio(EnumEspecialidades.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }
    private void  cadastrarConsulta(EntityMedico medico, EntityPaciente paciente, LocalDateTime data){
        em.persist(new EntityConsulta(null, medico, paciente, data));
    }
    private EntityMedico  cadastrarMedico(String nome, String email, String rm, EnumEspecialidades especialidade){
        var medico = new EntityMedico(dadosMedico(nome, email, rm, especialidade));
        em.persist(medico);
        return medico;
    }
    private EntityPaciente  cadastrarPaciente(String nome, String email, String cpf){
        var paciente = new EntityPaciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }
    private MedicosDTO dadosMedico(String nome, String email, String rm, EnumEspecialidades especialidade){
        return new MedicosDTO(
                nome,
                email,
                "619999999999",
                rm,
                especialidade,
                dadosEndereco()
        );
    }
    private DadosCadastroPaciente dadosPaciente (String nome, String email, String cpf){
        return new DadosCadastroPaciente(
                nome,
                email,
                "69877777777",
                cpf,
                dadosEndereco()
        );
    }
    private DadosEndereco dadosEndereco(){
        return new DadosEndereco(
                "Rua x",
                "bairro",
                "03813000",
                "Sao Paulo",
                "SP",
                null,
                null
        );
    }
}