package med.vall.api.service.validacoesService;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.infra.exceptions.ValidaExPaciente;
import med.vall.api.repository.RepositoryPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private RepositoryPaciente repositoryPaciente;

    public void validar (DadosAgendamentoConsulta agendamentoConsulta){
        var validarPacienteAtivo = repositoryPaciente.findAtivoById(agendamentoConsulta.idPaciente());
        if(!validarPacienteAtivo){
            throw new ValidaExPaciente("Não é possivel agendar consulta com paciente exluido");
        }
    }
}
