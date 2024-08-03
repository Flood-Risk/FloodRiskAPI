package com.hackathon.floodrisk.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.floodrisk.service.AreaRiscoEnchenteService;
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

import static com.hackathon.floodrisk.mocks.AreaRiscoEnchentesMock.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AreaRiscoEnchenteRest.class)
@ExtendWith(MockitoExtension.class)
class AreaRiscoEnchenteRestIntegrationTest {

    private static final Long ID_AREA_RISCO_ENCHENTE = 1L;
    private static final Long ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE = 999L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AreaRiscoEnchenteService areaRiscoEnchenteService;

    @MockBean
    private AreaRiscoEnchenteApiConverter converter;

    @Test
    @DisplayName("Deve retornar no body da resposta uma lista com todas areas de riscos de enchente")
    public void testGetAllAreaRiscoEnchentes() throws Exception {
        when(areaRiscoEnchenteService.findAll()).thenReturn(List.of(buildAreaRiscoEnchenteBO()));
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(get("/api/areas-riscos-enchente")
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
    @DisplayName("Deve retornar no body da resposta area de risco de enchente buscada por ID")
    public void testGetAreaRiscoEnchente() throws Exception {
        when(areaRiscoEnchenteService.get(ID_AREA_RISCO_ENCHENTE)).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(get("/api/areas-riscos-enchente/{id}", ID_AREA_RISCO_ENCHENTE)
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
    @DisplayName("Deve adicionar area de risco de enchente")
    public void testCreateAreaRiscoEnchente() throws Exception {
        String jsonInputDTO = objectMapper.writeValueAsString(buildAreaRiscoEnchenteInputDTO());

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
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
    @DisplayName("Deve atualizar area de risco de enchente por ID")
    public void testUpdateAreaRiscoEnchente() throws Exception {
        String jsonInputDTO = objectMapper.writeValueAsString(buildAreaRiscoEnchenteInputDTO());

        when(areaRiscoEnchenteService.update(eq(ID_AREA_RISCO_ENCHENTE), any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(put("/api/areas-riscos-enchente/{id}", ID_AREA_RISCO_ENCHENTE)
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
    @DisplayName("Deve remover area de risco de enchente por ID")
    public void testDeleteAreaRiscoEnchente() throws Exception {
        doNothing().when(areaRiscoEnchenteService).delete(ID_AREA_RISCO_ENCHENTE);

        mockMvc.perform(delete("/api/areas-riscos-enchente/{id}", ID_AREA_RISCO_ENCHENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar buscar area de risco de enchente inexistente")
    public void testGetAreaRiscoEnchenteNotFound() throws Exception {
        when(areaRiscoEnchenteService.get(ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE)).thenThrow(new NotFoundException(ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE));

        mockMvc.perform(get("/api/areas-riscos-enchente/{id}", ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Não encontrado área de risco de enchente com o ID: " + ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar atualizar area de risco de enchente inexistente")
    public void testUpdateRiscoEnchenteNotFound() throws Exception {
        when(areaRiscoEnchenteService.update(eq(ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE), any(AreaRiscoEnchenteInputDTO.class)))
                .thenThrow(new NotFoundException(ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE));

        mockMvc.perform(put("/api/areas-riscos-enchente/{id}", ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildAreaRiscoEnchenteInputDTO())))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Não encontrado área de risco de enchente com o ID: " + ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar deletar area de risco de enchente inexistente")
    public void testDeleteRiscoEnchenteNotFound() throws Exception {
        doThrow(new NotFoundException(ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE)).when(areaRiscoEnchenteService).delete(ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE);

        mockMvc.perform(delete("/api/areas-riscos-enchente/{id}", ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Não encontrado área de risco de enchente com o ID: " + ID_NAO_EXISTENTE_AREA_RISCO_ENCHENTE));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com nome nulo")
    public void testCreateSemNomeArea() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setNome(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nome"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com nome com mais de 255 caracteres")
    public void testCreateComNomeAreaAcimaDe255Caracteres() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setNome("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nome"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com descricao nulo")
    public void testCreateSemDescricaoArea() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setDescricao(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("descricao"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com descricao com mais de 255 caracteres")
    public void testCreateComDescricaoAreaAcimaDe255Caracteres() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setDescricao("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("descricao"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com estado nulo")
    public void testCreateSemEstadoArea() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setEstado(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("estado"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com estado com mais de 255 caracteres")
    public void testCreateComEstadoAcimaDe255Caracteres() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setEstado("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("estado"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com cidade nulo")
    public void testCreateSemCidadeArea() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setCidade(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("cidade"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com cidade com mais de 255 caracteres")
    public void testCreateComCidadeAcimaDe255Caracteres() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setCidade("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("cidade"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com latitude nulo")
    public void testCreateSemLatitudeArea() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setLatitude(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("latitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com latitude com mais de 255 caracteres")
    public void testCreateComLatitudeAcimaDe255Caracteres() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setLatitude("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("latitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com longitude nulo")
    public void testCreateSemLongitudeArea() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setLongitude(null);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("REQUIRED_NOT_NULL"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("longitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must not be null"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com longitude com mais de 255 caracteres")
    public void testCreateComLongitudeAcimaDe255Caracteres() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setLongitude("A".repeat(256));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("longitude"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com nivel de risco menor que 1")
    public void testCreateNivelRiscoAreaMenorQueUm() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setNivelRisco(0);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("VALUE_LESS_THAN_MIN"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nivelRisco"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must be greater than or equal to 1"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com nivel de risco maior que 5")
    public void testCreateNivelRiscoAreaMaiorQueCinco() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setNivelRisco(6);
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("VALUE_GREATER_THAN_MAX"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("nivelRisco"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("must be less than or equal to 5"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar area de enchente com historico de dados com mais de 255 caracteres")
    public void testCreateComHistoricoDadosAcimaDe255Caracteres() throws Exception {
        AreaRiscoEnchenteInputDTO input = buildAreaRiscoEnchenteInputDTO();
        input.setHistoricoDados(List.of("A".repeat(256)));
        String jsonInputDTO = objectMapper.writeValueAsString(input);

        when(areaRiscoEnchenteService.create(any(AreaRiscoEnchenteInputDTO.class))).thenReturn(buildAreaRiscoEnchenteBO());
        when(converter.toOutputDTO(buildAreaRiscoEnchenteBO())).thenReturn(buildAreaRiscoEnchenteOutputDTO());

        mockMvc.perform(post("/api/areas-riscos-enchente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInputDTO))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.message").value("Validation failed for object='areaRiscoEnchenteInputDTO'. Error count: 1"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("INVALID_SIZE"))
                .andExpect(jsonPath("$.fieldErrors[0].property").value("historicoDados[0]"))
                .andExpect(jsonPath("$.fieldErrors[0].message").value("size must be between 0 and 255"));
    }

}