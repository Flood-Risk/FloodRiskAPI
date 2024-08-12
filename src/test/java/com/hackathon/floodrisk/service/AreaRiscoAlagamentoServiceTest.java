package com.hackathon.floodrisk.service;

import com.hackathon.floodrisk.api.AreaRiscoAlagamentoInputDTO;
import com.hackathon.floodrisk.domain.AreaRiscoAlagamento;
import com.hackathon.floodrisk.repos.AreaRiscoAlagamentoRepository;
import com.hackathon.floodrisk.util.AreaRiscoAlagamentoConverter;
import com.hackathon.floodrisk.util.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.hackathon.floodrisk.mocks.AreaRiscoAlagamentosMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaRiscoAlagamentoServiceTest {

    private static final Long ID_AREA_RISCO_ALAGAMENTO = 1L;
    private static final String LATITUDE = "-29.98754116965509";
    private static final String LONGITUDE = "-51.258601061711346";

    @Mock
    private AreaRiscoAlagamentoRepository repository;

    @Spy
    private AreaRiscoAlagamentoConverter converter;

    @InjectMocks
    private AreaRiscoAlagamentoService service;

    @Test
    @DisplayName("Deve retornar BO com todas areas de riscos de alagamento")
    void findAll() {
        when(repository.findAll(Sort.by("id"))).thenReturn(Collections.singletonList(buildAreaRiscoAlagamento()));

        List<AreaRiscoAlagamentoBO> resultado = service.findAll();

        verify(repository, times(1)).findAll(Sort.by("id"));
        verify(converter, times(1)).toBO(buildAreaRiscoAlagamento(), buildAreaRiscoAlagamentoBO());
        assertEquals(Collections.singletonList(buildAreaRiscoAlagamentoBO()), resultado);
    }

    @Test
    @DisplayName("Deve retornar BO com area de risco de alagamento buscando por ID")
    void get() {
        when(repository.findById(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(Optional.of(buildAreaRiscoAlagamento()));

        AreaRiscoAlagamentoBO resultado = service.get(ID_AREA_RISCO_ALAGAMENTO);

        verify(repository, times(1)).findById(ID_AREA_RISCO_ALAGAMENTO);
        verify(converter, times(1)).toBO(buildAreaRiscoAlagamento(), buildAreaRiscoAlagamentoBO());
        assertEquals(buildAreaRiscoAlagamentoBO(), resultado);
    }

    @Test
    @DisplayName("Deve adicionar area de risco de alagamento")
    void create() {
        when(repository.save(any(AreaRiscoAlagamento.class))).thenReturn(buildAreaRiscoAlagamento());

        AreaRiscoAlagamentoBO resultado = service.create(buildAreaRiscoAlagamentoInputDTO());

        verify(converter, times(1)).toEntity(eq(buildAreaRiscoAlagamentoInputDTO()), any(AreaRiscoAlagamento.class));
        verify(repository, times(1)).save(any(AreaRiscoAlagamento.class));
        verify(converter, times(1)).toBO(buildAreaRiscoAlagamento(), buildAreaRiscoAlagamentoBO());
        assertEquals(buildAreaRiscoAlagamentoBO(), resultado);
    }

    @Test
    @DisplayName("Deve atualizar area de risco de alagamento por ID")
    void update() {
        when(repository.findById(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(Optional.of(buildAreaRiscoAlagamento()));
        when(repository.save(any(AreaRiscoAlagamento.class))).thenReturn(buildAreaRiscoAlagamento());

        AreaRiscoAlagamentoBO resultado = service.update(ID_AREA_RISCO_ALAGAMENTO, buildAreaRiscoAlagamentoInputDTO());

        verify(converter, times(1)).toEntity(buildAreaRiscoAlagamentoInputDTO(), buildAreaRiscoAlagamento());
        verify(repository, times(1)).findById(ID_AREA_RISCO_ALAGAMENTO);
        verify(converter, times(1)).toBO(buildAreaRiscoAlagamento(), buildAreaRiscoAlagamentoBO());
        verify(repository, times(1)).save(buildAreaRiscoAlagamento());
        assertEquals(buildAreaRiscoAlagamentoBO(), resultado);
    }

    @Test
    @DisplayName("Deve remover area de risco de alagamento por ID")
    void delete() {
        when(repository.findById(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(Optional.of(buildAreaRiscoAlagamento()));

        service.delete(ID_AREA_RISCO_ALAGAMENTO);

        verify(repository, times(1)).findById(ID_AREA_RISCO_ALAGAMENTO);
        verify(repository, times(1)).delete(buildAreaRiscoAlagamento());
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao buscar area de risco de alagamento com ID inexistente")
    void getNotFoundException() {
        when(repository.findById(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.get(ID_AREA_RISCO_ALAGAMENTO));
        verify(repository, times(1)).findById(ID_AREA_RISCO_ALAGAMENTO);
        verify(converter, never()).toBO(any(AreaRiscoAlagamento.class), any(AreaRiscoAlagamentoBO.class));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao atualizar area de risco de alagamento com ID inexistente")
    void updateNotFoundException() {
        AreaRiscoAlagamentoInputDTO input = buildAreaRiscoAlagamentoInputDTO();
        when(repository.findById(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(ID_AREA_RISCO_ALAGAMENTO, input));
        verify(repository, times(1)).findById(ID_AREA_RISCO_ALAGAMENTO);
        verify(converter, never()).toEntity(any(AreaRiscoAlagamentoInputDTO.class), any(AreaRiscoAlagamento.class));
        verify(repository, never()).save(any(AreaRiscoAlagamento.class));
        verify(converter, never()).toBO(any(AreaRiscoAlagamento.class), any(AreaRiscoAlagamentoBO.class));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar excluir area de risco de alagamento inexistente")
    void deleteNotFoundException() {
        when(repository.findById(ID_AREA_RISCO_ALAGAMENTO)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(ID_AREA_RISCO_ALAGAMENTO));
        verify(repository, times(1)).findById(ID_AREA_RISCO_ALAGAMENTO);
        verify(repository, never()).deleteById(ID_AREA_RISCO_ALAGAMENTO);
    }
}