package med.vall.api.service.validacoesService;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.infra.exceptions.ValidaExPaciente;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidaHorarioFuncionalidadeClinica implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta agendamentoConsulta){
        var dadosConsulta = agendamentoConsulta.data();

        var domingo = dadosConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dadosConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dadosConsulta.getHour() > 18;
        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidaExPaciente("Consulta fora do horario fe funcionamento da clinica");
        }
    }

}
