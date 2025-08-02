package med.vall.api.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vall.api.enuns.EnumEspecialidades;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        EnumEspecialidades especialidade

) {
}
