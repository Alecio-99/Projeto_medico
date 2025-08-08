package med.vall.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.DTO.DadosDetalhamantoConsulta;
import med.vall.api.service.ServiceAgendaConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    ServiceAgendaConsultas serviceAgendaConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoConsulta agendamentoConsulta){
        var dto = serviceAgendaConsultas.agendar(agendamentoConsulta);
        return ResponseEntity.ok(dto);
    }
}
