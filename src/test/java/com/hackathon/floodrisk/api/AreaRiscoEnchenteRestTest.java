package com.hackathon.floodrisk.api;

import com.hackathon.floodrisk.service.AreaRiscoEnchenteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static com.hackathon.floodrisk.mocks.AreaRiscoEnchentesMock.*;
import static com.hackathon.floodrisk.mocks.AreaRiscoEnchentesMock.buildAreaRiscoEnchenteInputDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaRiscoEnchenteRestTest {

    private static final Long ID_AREA_RISCO_ENCHENTE = 1L;

    @Mock
    private AreaRiscoEnchenteService service;

    @Spy
    private AreaRiscoEnchenteApiConverter converter;

    @InjectMocks
    private AreaRiscoEnchenteRest rest;

    @Test
    @DisplayName("Deve retornar output com todas areas de riscos de enchente")
    void getAllAreaRiscoEnchentes() {
        when(service.findAll()).thenReturn(Collections.singletonList(buildAreaRiscoEnchenteBO()));

        ResponseEntity<List<AreaRiscoEnchenteOutputDTO>> resultado = rest.getAllAreaRiscoEnchentes();

        verify(service, times(1)).findAll();
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoEnchenteBO());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(Collections.singletonList(buildAreaRiscoEnchenteOutputDTO()), resultado.getBody());
    }

    @Test
    @DisplayName("Deve retornar output com area de risco de enchente buscando por ID")
    void getAreaRiscoEnchente() {
        when(service.get(ID_AREA_RISCO_ENCHENTE)).thenReturn(buildAreaRiscoEnchenteBO());

        ResponseEntity<AreaRiscoEnchenteOutputDTO> resultado = rest.getAreaRiscoEnchente(ID_AREA_RISCO_ENCHENTE);

        verify(service, times(1)).get(ID_AREA_RISCO_ENCHENTE);
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoEnchenteBO());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(buildAreaRiscoEnchenteOutputDTO(), resultado.getBody());
    }

    @Test
    @DisplayName("Deve adicionar area de risco de enchente")
    void createAreaRiscoEnchente() {
        when(service.create(buildAreaRiscoEnchenteInputDTO())).thenReturn(buildAreaRiscoEnchenteBO());

        ResponseEntity<AreaRiscoEnchenteOutputDTO> resultado = rest.createAreaRiscoEnchente(buildAreaRiscoEnchenteInputDTO());

        verify(service, times(1)).create(buildAreaRiscoEnchenteInputDTO());
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoEnchenteBO());
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(buildAreaRiscoEnchenteOutputDTO(), resultado.getBody());
    }

    @Test
    @DisplayName("Deve atualizar area de risco de enchente por ID")
    void updateAreaRiscoEnchente() {
        when(service.update(ID_AREA_RISCO_ENCHENTE, buildAreaRiscoEnchenteInputDTO())).thenReturn(buildAreaRiscoEnchenteBO());

        ResponseEntity<AreaRiscoEnchenteOutputDTO> resultado = rest.updateAreaRiscoEnchente(ID_AREA_RISCO_ENCHENTE, buildAreaRiscoEnchenteInputDTO());

        verify(service, times(1)).update(ID_AREA_RISCO_ENCHENTE, buildAreaRiscoEnchenteInputDTO());
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoEnchenteBO());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(buildAreaRiscoEnchenteOutputDTO(), resultado.getBody());
    }

    @Test
    @DisplayName("Deve remover area de risco de enchente por ID")
    void deleteAreaRiscoEnchente() {
        ResponseEntity<Void> resultado = rest.deleteAreaRiscoEnchente(ID_AREA_RISCO_ENCHENTE);

        verify(service, times(1)).delete(ID_AREA_RISCO_ENCHENTE);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
    }
}