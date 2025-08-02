package med.vall.api.repository;

import jakarta.validation.constraints.NotNull;
import med.vall.api.entity.EntityConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RepositoryConsulta extends JpaRepository<EntityConsulta, Long> {


    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
