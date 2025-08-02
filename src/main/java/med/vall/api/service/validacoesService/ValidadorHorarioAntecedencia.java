package med.vall.api.service.validacoesService;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.infra.exceptions.ValidaExPaciente;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas{

    public void validar(DadosAgendamentoConsulta agendamentoConsulta){
        var dataConsulta = agendamentoConsulta.data();
        var agora = LocalDateTime.now();
        var diferencaEntreMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEntreMinutos < 30){
            throw new ValidaExPaciente("A consulta deve ser marcada com 30 minutos de antecedência");
        }
    }
}
