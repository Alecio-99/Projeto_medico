package med.vall.api.service;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.DTO.DadosDetalhamantoConsulta;
import med.vall.api.entity.EntityConsulta;
import med.vall.api.entity.EntityMedico;
import med.vall.api.infra.exceptions.ValidaExPaciente;
import med.vall.api.repository.RepositoryConsulta;
import med.vall.api.repository.RepositoryMedico;
import med.vall.api.repository.RepositoryPaciente;
import med.vall.api.service.validacoesService.ValidadorAgendamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAgendaConsultas {

    @Autowired
    private  RepositoryConsulta repositoryConsulta;

    @Autowired
    private RepositoryMedico repositoryMedico;

    @Autowired
    private RepositoryPaciente repositoryPaciente;

    //aqui eu posso usar um truque do spring de colocar tudo em uma lista e minha interface vai se encarregar de chamar todos os validadores
    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    public DadosDetalhamantoConsulta agendar(DadosAgendamentoConsulta agendamentoConsulta){
        if(!repositoryPaciente.existsById(agendamentoConsulta.idPaciente())){
            throw new ValidaExPaciente("Paciente não existe");
        }
        if (agendamentoConsulta.idMedico() != null && !repositoryMedico.existsById(agendamentoConsulta.idMedico())){
            throw new ValidaExPaciente("Medico não existe");
        }

        //Aqui estou percorrendo meus validadores
        validadores.forEach(v -> v.validar(agendamentoConsulta));

        // com isso podemos pegar os dados dos nossas entidades DTOs e utilizalar em variaveis
        var paciente = repositoryPaciente.findById(agendamentoConsulta.idPaciente()).get();
        var medico = escolherMedico(agendamentoConsulta);
        if(medico == null){
            throw new ValidaExPaciente("Não existe medico disponivel nessa data!");
        }
        var consulta = new EntityConsulta(null, medico, paciente, agendamentoConsulta.data());

        repositoryConsulta.save(consulta);

        return new DadosDetalhamantoConsulta(consulta);
    }

    private EntityMedico escolherMedico(DadosAgendamentoConsulta agendamentoConsulta) {
      if(agendamentoConsulta.idMedico() != null){
          return repositoryMedico.getReferenceById(agendamentoConsulta.idMedico());
      }
      if(agendamentoConsulta.especialidade() ==  null){
          throw new ValidaExPaciente("Se o medico não for especificado, é necessário escolher uma especialidade");
      }
      return repositoryMedico.escolherMedicoAleatorio(agendamentoConsulta.especialidade(), agendamentoConsulta.data());
    }
}
