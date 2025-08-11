package med.vall.api.controller;

import med.vall.api.DTO.DadosAgendamentoConsulta;
import med.vall.api.DTO.DadosDetalhamantoConsulta;
import med.vall.api.enuns.EnumEspecialidades;
import med.vall.api.service.ServiceAgendaConsultas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> agendamentoConsultaJacksonJson;

    @Autowired
    private JacksonTester<DadosDetalhamantoConsulta> dadosDetalhamantoConsultaJacksonJson;

    @Autowired
    @MockitoBean
    private ServiceAgendaConsultas agendaConsultas;


    @Test
    @DisplayName("Devera retornar erro 400 quando as informação forem incorretas")
    @WithMockUser // Fala pro spring mokar um user generico
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
    @Test
    @DisplayName("Devera retornar erro 200 quando as informação forem corretas")
    @WithMockUser // Fala pro spring mokar um user generico
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = EnumEspecialidades.CARDIOLOGIA;
        var dadosDetalhamento =  new DadosDetalhamantoConsulta(null, 2l, 5l, data);
        when(agendaConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendamentoConsultaJacksonJson.write(
                                new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
                        ).getJson())

                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamantoConsultaJacksonJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}