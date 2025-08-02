package med.vall.api.service.validacoesService;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.infra.exceptions.ValidaExPaciente;
import med.vall.api.repository.RepositoryConsulta;
import med.vall.api.repository.RepositoryMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoComOutraData implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private RepositoryConsulta repositoryConsulta;

    public void validar(DadosAgendamentoConsulta agendamentoConsulta){
         var medicoPossuiOutraConsulta = repositoryConsulta.existsByMedicoIdAndData(agendamentoConsulta.idMedico(), agendamentoConsulta.data());
         if(medicoPossuiOutraConsulta){
             throw new ValidaExPaciente("O Medico já possui agendamento nesse horario");
         }
    }
}
