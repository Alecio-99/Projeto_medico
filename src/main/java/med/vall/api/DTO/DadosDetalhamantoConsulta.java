package med.vall.api.DTO;

import med.vall.api.entity.EntityConsulta;

import java.time.LocalDateTime;

public record DadosDetalhamantoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamantoConsulta(EntityConsulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
