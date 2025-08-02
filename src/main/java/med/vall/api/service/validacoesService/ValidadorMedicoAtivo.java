package med.vall.api.service.validacoesService;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.infra.exceptions.ValidaExPaciente;
import med.vall.api.repository.RepositoryMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private RepositoryMedico repositoryMedico;

    public void validar (DadosAgendamentoConsulta agendamentoConsulta){

        if(agendamentoConsulta.idMedico() == null){
            return;
        }
        var medicoAtivo = repositoryMedico.findAtivoById(agendamentoConsulta.idMedico());
        if(!medicoAtivo){
            throw new ValidaExPaciente("A consulta não pode ser agendada com o ");
        }

    }
}
