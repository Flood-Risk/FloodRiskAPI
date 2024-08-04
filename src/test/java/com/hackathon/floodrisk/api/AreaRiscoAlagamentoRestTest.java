package com.hackathon.floodrisk.api;

import com.hackathon.floodrisk.service.AreaRiscoAlagamentoService;
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

import static com.hackathon.floodrisk.mocks.AreaRiscoAlagamentosMock.*;
import static com.hackathon.floodrisk.mocks.AreaRiscoAlagamentosMock.buildAreaRiscoAlagamentoInputDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaRiscoAlagamentoRestTest {

    private static final Long ID_AREA_RISCO_ALAGAMENTO = 1L;

    @Mock
    private AreaRiscoAlagamentoService service;

    @Spy
    private AreaRiscoAlagamentoApiConverter converter;

    @InjectMocks
    private AreaRiscoAlagamentoRest rest;

    @Test
    @DisplayName("Deve retornar output com todas areas de riscos de alagamento")
    void getAllAreaRiscoAlagamentos() {
        when(service.findAll()).thenReturn(Collections.singletonList(buildAreaRiscoAlagamentoBO()));

        ResponseEntity<List<AreaRiscoAlagamentoOutputDTO>> resultado = rest.getAllAreaRiscoAlagamentos();

        verify(service, times(1)).findAll();
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoAlagamentoBO());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(Collections.singletonList(buildAreaRiscoAlagamentoOutputDTO()), resultado.getBody());
    }

    @Test
    @DisplayName("Deve retornar output com area de risco de alagamento buscando por ID")
    void getAreaRiscoAlagamento() {
        when(service.get(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(buildAreaRiscoAlagamentoBO());

        ResponseEntity<AreaRiscoAlagamentoOutputDTO> resultado = rest.getAreaRiscoAlagamento(ID_AREA_RISCO_ALAGAMENTO);

        verify(service, times(1)).get(ID_AREA_RISCO_ALAGAMENTO);
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoAlagamentoBO());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(buildAreaRiscoAlagamentoOutputDTO(), resultado.getBody());
    }

    @Test
    @DisplayName("Deve adicionar area de risco de alagamento")
    void createAreaRiscoAlagamento() {
        when(service.create(buildAreaRiscoAlagamentoInputDTO())).thenReturn(buildAreaRiscoAlagamentoBO());

        ResponseEntity<AreaRiscoAlagamentoOutputDTO> resultado = rest.createAreaRiscoAlagamento(buildAreaRiscoAlagamentoInputDTO());

        verify(service, times(1)).create(buildAreaRiscoAlagamentoInputDTO());
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoAlagamentoBO());
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(buildAreaRiscoAlagamentoOutputDTO(), resultado.getBody());
    }

    @Test
    @DisplayName("Deve atualizar area de risco de alagamento por ID")
    void updateAreaRiscoAlagamento() {
        when(service.update(ID_AREA_RISCO_ALAGAMENTO, buildAreaRiscoAlagamentoInputDTO())).thenReturn(buildAreaRiscoAlagamentoBO());

        ResponseEntity<AreaRiscoAlagamentoOutputDTO> resultado = rest.updateAreaRiscoAlagamento(ID_AREA_RISCO_ALAGAMENTO, buildAreaRiscoAlagamentoInputDTO());

        verify(service, times(1)).update(ID_AREA_RISCO_ALAGAMENTO, buildAreaRiscoAlagamentoInputDTO());
        verify(converter, times(1)).toOutputDTO(buildAreaRiscoAlagamentoBO());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(buildAreaRiscoAlagamentoOutputDTO(), resultado.getBody());
    }

    @Test
    @DisplayName("Deve remover area de risco de alagamento por ID")
    void deleteAreaRiscoAlagamento() {
        ResponseEntity<Void> resultado = rest.deleteAreaRiscoAlagamento(ID_AREA_RISCO_ALAGAMENTO);

        verify(service, times(1)).delete(ID_AREA_RISCO_ALAGAMENTO);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
    }
}