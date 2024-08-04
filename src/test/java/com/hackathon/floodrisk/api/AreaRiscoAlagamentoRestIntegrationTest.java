package com.hackathon.floodrisk.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.floodrisk.service.AreaRiscoAlagamentoService;
import com.hackathon.floodrisk.util.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.hackathon.floodrisk.mocks.AreaRiscoAlagamentosMock.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AreaRiscoAlagamentoRest.class)
@ExtendWith(MockitoExtension.class)
class AreaRiscoAlagamentoRestIntegrationTest {

    private static final Long ID_AREA_RISCO_ALAGAMENTO = 1L;
    private static final Long ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO = 999L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AreaRiscoAlagamentoService areaRiscoAlagamentoService;

    @MockBean
    private AreaRiscoAlagamentoApiConverter converter;

    @Test
    @DisplayName("Deve retornar no body da resposta uma lista com todas areas de riscos de alagamento")
    public void testGetAllAreaRiscoAlagamentos() throws Exception {
        when(areaRiscoAlagamentoService.findAll()).thenReturn(List.of(buildAreaRiscoAlagamentoBO()));
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(get("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Ilha das Flores"))
                .andExpect(jsonPath("$[0].descricao").value("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                        "especialmente durante chuvas intensas e períodos de cheia dos rios."))
                .andExpect(jsonPath("$[0].estado").value("Rio Grande do Sul"))
                .andExpect(jsonPath("$[0].cidade").value("Porto Alegre"))
                .andExpect(jsonPath("$[0].latitude").value("-29.98754116965509"))
                .andExpect(jsonPath("$[0].longitude").value("-51.258601061711346"))
                .andExpect(jsonPath("$[0].nivelRisco").value(5))
                .andExpect(jsonPath("$[0].historicoDados[0]").value("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));
    }

    @Test
    @DisplayName("Deve retornar no body da resposta area de risco de alagamento buscada por ID")
    public void testGetAreaRiscoAlagamento() throws Exception {
        when(areaRiscoAlagamentoService.get(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(get("/api/areas-riscos-alagamento/{id}", ID_AREA_RISCO_ALAGAMENTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Ilha das Flores"))
                .andExpect(jsonPath("$.descricao").value("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                        "especialmente durante chuvas intensas e períodos de cheia dos rios."))
                .andExpect(jsonPath("$.estado").value("Rio Grande do Sul"))
                .andExpect(jsonPath("$.cidade").value("Porto Alegre"))
                .andExpect(jsonPath("$.latitude").value("-29.98754116965509"))
                .andExpect(jsonPath("$.longitude").value("-51.258601061711346"))
                .andExpect(jsonPath("$.nivelRisco").value(5))
                .andExpect(jsonPath("$.historicoDados[0]").value("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));
    }

    @Test
    @DisplayName("Deve adicionar area de risco de alagamento")
    public void testCreateAreaRiscoAlagamento() throws Exception {
        String jsonInputDTO = objectMapper.writeValueAsString(buildAreaRiscoAlagamentoInputDTO());

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Ilha das Flores"))
                .andExpect(jsonPath("$.descricao").value("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                        "especialmente durante chuvas intensas e períodos de cheia dos rios."))
                .andExpect(jsonPath("$.estado").value("Rio Grande do Sul"))
                .andExpect(jsonPath("$.cidade").value("Porto Alegre"))
                .andExpect(jsonPath("$.latitude").value("-29.98754116965509"))
                .andExpect(jsonPath("$.longitude").value("-51.258601061711346"))
                .andExpect(jsonPath("$.nivelRisco").value(5))
                .andExpect(jsonPath("$.historicoDados[0]").value("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));
    }

    @Test
    @DisplayName("Deve atualizar area de risco de alagamento por ID")
    public void testUpdateAreaRiscoAlagamento() throws Exception {
        String jsonInputDTO = objectMapper.writeValueAsString(buildAreaRiscoAlagamentoInputDTO());

        when(areaRiscoAlagamentoService.update(eq(ID_AREA_RISCO_ALAGAMENTO), any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(put("/api/areas-riscos-alagamento/{id}", ID_AREA_RISCO_ALAGAMENTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Ilha das Flores"))
                .andExpect(jsonPath("$.descricao").value("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                        "especialmente durante chuvas intensas e períodos de cheia dos rios."))
                .andExpect(jsonPath("$.estado").value("Rio Grande do Sul"))
                .andExpect(jsonPath("$.cidade").value("Porto Alegre"))
                .andExpect(jsonPath("$.latitude").value("-29.98754116965509"))
                .andExpect(jsonPath("$.longitude").value("-51.258601061711346"))
                .andExpect(jsonPath("$.nivelRisco").value(5))
                .andExpect(jsonPath("$.historicoDados[0]").value("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));

    }

    @Test
    @DisplayName("Deve remover area de risco de alagamento por ID")
    public void testDeleteAreaRiscoAlagamento() throws Exception {
        doNothing().when(areaRiscoAlagamentoService).delete(ID_AREA_RISCO_ALAGAMENTO);

        mockMvc.perform(delete("/api/areas-riscos-alagamento/{id}", ID_AREA_RISCO_ALAGAMENTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar buscar area de risco de alagamento inexistente")
    public void testGetAreaRiscoAlagamentoNotFound() throws Exception {
        when(areaRiscoAlagamentoService.get(ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO)).thenThrow(new NotFoundException(ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO));

        mockMvc.perform(get("/api/areas-riscos-alagamento/{id}", ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Não encontrado área de risco de alagamento com o ID: " + ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar atualizar area de risco de alagamento inexistente")
    public void testUpdateRiscoAlagamentoNotFound() throws Exception {
        when(areaRiscoAlagamentoService.update(eq(ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO), any(AreaRiscoAlagamentoInputDTO.class)))
                .thenThrow(new NotFoundException(ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO));

        mockMvc.perform(put("/api/areas-riscos-alagamento/{id}", ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildAreaRiscoAlagamentoInputDTO())))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Não encontrado área de risco de alagamento com o ID: " + ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar deletar area de risco de alagamento inexistente")
    public void testDeleteRiscoAlagamentoNotFound() throws Exception {
        doThrow(new NotFoundException(ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO)).when(areaRiscoAlagamentoService).delete(ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO);

        mockMvc.perform(delete("/api/areas-riscos-alagamento/{id}", ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Não encontrado área de risco de alagamento com o ID: " + ID_NAO_EXISTENTE_AREA_RISCO_ALAGAMENTO));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com nome nulo")
    public void testCreateSemNomeArea() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setNome(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nome"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com nome com mais de 255 caracteres")
    public void testCreateComNomeAreaAcimaDe255Caracteres() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setNome("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nome"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com descricao nulo")
    public void testCreateSemDescricaoArea() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setDescricao(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("descricao"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com descricao com mais de 255 caracteres")
    public void testCreateComDescricaoAreaAcimaDe255Caracteres() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setDescricao("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("descricao"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com estado nulo")
    public void testCreateSemEstadoArea() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setEstado(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("estado"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com estado com mais de 255 caracteres")
    public void testCreateComEstadoAcimaDe255Caracteres() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setEstado("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("estado"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com cidade nulo")
    public void testCreateSemCidadeArea() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setCidade(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("cidade"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com cidade com mais de 255 caracteres")
    public void testCreateComCidadeAcimaDe255Caracteres() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setCidade("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("cidade"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com latitude nulo")
    public void testCreateSemLatitudeArea() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setLatitude(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("latitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com latitude com mais de 255 caracteres")
    public void testCreateComLatitudeAcimaDe255Caracteres() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setLatitude("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("latitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com longitude nulo")
    public void testCreateSemLongitudeArea() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setLongitude(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("longitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com longitude com mais de 255 caracteres")
    public void testCreateComLongitudeAcimaDe255Caracteres() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setLongitude("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("longitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com nivel de risco menor que 1")
    public void testCreateNivelRiscoAreaMenorQueUm() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setNivelRisco(0);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("VALUE_LESS_THAN_MIN"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nivelRisco"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must be greater than or equal to 1"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com nivel de risco maior que 5")
    public void testCreateNivelRiscoAreaMaiorQueCinco() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setNivelRisco(6);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("VALUE_GREATER_THAN_MAX"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nivelRisco"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must be less than or equal to 5"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de alagamento com historico de dados com mais de 255 caracteres")
    public void testCreateComHistoricoDadosAcimaDe255Caracteres() throws Exception {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        input.setHistoricoDados(List.of("A".repeat(256)));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoAlagamentoService.create(any(AreaRiscoAlagamentoInputDTO.class))).thenReturn(buildAreaRiscoAlagamentoBO());
        when(converter.toOutputDTO(buildAreaRiscoAlagamentoBO())).thenReturn(buildAreaRiscoAlagamentoOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-alagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoAlagamentoInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("historicoDados[0]"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

}