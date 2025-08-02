package med.vall.api.service.validacoesService;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.infra.exceptions.ValidaExPaciente;
import med.vall.api.repository.RepositoryConsulta;
import med.vall.api.repository.RepositoryPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private RepositoryConsulta repositoryConsulta;

    public void validar (DadosAgendamentoConsulta agendamentoConsulta){
        var primeiroHorario = agendamentoConsulta.data().withHour(7);
        var ultimoHorario = agendamentoConsulta.data().withHour(18);
        var pacientePossuiOutroAgendamento = repositoryConsulta.existsByPacienteIdAndDataBetween(agendamentoConsulta.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutroAgendamento){
            throw  new ValidaExPaciente("O paciente já possui consulta agendada nesse dia");
        }
    }
}
