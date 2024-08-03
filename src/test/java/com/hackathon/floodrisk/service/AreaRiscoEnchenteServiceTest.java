package com.hackathon.floodrisk.service;

import com.hackathon.floodrisk.api.AreaRiscoEnchenteInputDTO;
import com.hackathon.floodrisk.domain.AreaRiscoEnchente;
import com.hackathon.floodrisk.model.AreaRiscoEnchenteBO;
import com.hackathon.floodrisk.repos.AreaRiscoEnchenteRepository;
import com.hackathon.floodrisk.util.AreaRiscoEnchenteConverter;
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

import static com.hackathon.floodrisk.mocks.AreaRiscoEnchentesMock.*;
import static com.hackathon.floodrisk.mocks.AreaRiscoEnchentesMock.buildAreaRiscoEnchente;
import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaRiscoEnchenteServiceTest {

    private static final Long ID_AREA_RISCO_ENCHENTE = 1L;
    private static final String LATITUDE = "-29.98754116965509";
    private static final String LONGITUDE = "-51.258601061711346";

    @Mock
    private AreaRiscoEnchenteRepository repository;

    @Spy
    private AreaRiscoEnchenteConverter converter;

    @InjectMocks
    private AreaRiscoEnchenteService service;

    @Test
    @DisplayName("Deve retornar BO com todas areas de riscos de enchente")
    void findAll() {
        when(repository.findAll(Sort.by("id"))).thenReturn(Collections.singletonList(buildAreaRiscoEnchente()));

        List<AreaRiscoEnchenteBO> resultado = service.findAll();

        verify(repository, times(1)).findAll(Sort.by("id"));
        verify(converter, times(1)).toBO(buildAreaRiscoEnchente(), buildAreaRiscoEnchenteBO());
        assertEquals(Collections.singletonList(buildAreaRiscoEnchenteBO()), resultado);
    }

    @Test
    @DisplayName("Deve retornar BO com area de risco de enchente buscando por ID")
    void get() {
        when(repository.findById(ID_AREA_RISCO_ENCHENTE)).thenReturn(Optional.of(buildAreaRiscoEnchente()));

        AreaRiscoEnchenteBO resultado = service.get(ID_AREA_RISCO_ENCHENTE);

        verify(repository, times(1)).findById(ID_AREA_RISCO_ENCHENTE);
        verify(converter, times(1)).toBO(buildAreaRiscoEnchente(), buildAreaRiscoEnchenteBO());
        assertEquals(buildAreaRiscoEnchenteBO(), resultado);
    }

    @Test
    @DisplayName("Deve adicionar area de risco de enchente")
    void create() {
        when(repository.save(any(AreaRiscoEnchente.class))).thenReturn(buildAreaRiscoEnchente());

        AreaRiscoEnchenteBO resultado = service.create(buildAreaRiscoEnchenteInputDTO());

        verify(converter, times(1)).toEntity(eq(buildAreaRiscoEnchenteInputDTO()), any(AreaRiscoEnchente.class));
        verify(repository, times(1)).save(any(AreaRiscoEnchente.class));
        verify(converter, times(1)).toBO(buildAreaRiscoEnchente(), buildAreaRiscoEnchenteBO());
        assertEquals(buildAreaRiscoEnchenteBO(), resultado);
    }

    @Test
    @DisplayName("Deve atualizar area de risco de enchente por ID")
    void update() {
        when(repository.findById(ID_AREA_RISCO_ENCHENTE)).thenReturn(Optional.of(buildAreaRiscoEnchente()));
        when(repository.save(any(AreaRiscoEnchente.class))).thenReturn(buildAreaRiscoEnchente());

        AreaRiscoEnchenteBO resultado = service.update(ID_AREA_RISCO_ENCHENTE, buildAreaRiscoEnchenteInputDTO());

        verify(converter, times(1)).toEntity(buildAreaRiscoEnchenteInputDTO(), buildAreaRiscoEnchente());
        verify(repository, times(1)).findById(ID_AREA_RISCO_ENCHENTE);
        verify(converter, times(1)).toBO(buildAreaRiscoEnchente(), buildAreaRiscoEnchenteBO());
        verify(repository, times(1)).save(buildAreaRiscoEnchente());
        assertEquals(buildAreaRiscoEnchenteBO(), resultado);
    }

    @Test
    @DisplayName("Deve remover area de risco de enchente por ID")
    void delete() {
        when(repository.findById(ID_AREA_RISCO_ENCHENTE)).thenReturn(Optional.of(buildAreaRiscoEnchente()));

        service.delete(ID_AREA_RISCO_ENCHENTE);

        verify(repository, times(1)).findById(ID_AREA_RISCO_ENCHENTE);
        verify(repository, times(1)).deleteById(ID_AREA_RISCO_ENCHENTE);
    }

    @Test
    @DisplayName("Deve retornar true quando a latitude existir")
    void latitudeExists() {
        when(repository.existsByLatitudeIgnoreCase(LATITUDE)).thenReturn(true);

        boolean result = service.latitudeExists(LATITUDE);

        assertTrue(result);
        verify(repository, times(1)).existsByLatitudeIgnoreCase(LATITUDE);
    }

    @Test
    @DisplayName("Deve retornar false quando a latitude nao existir")
    void latitudeDoesntExists() {
        when(repository.existsByLatitudeIgnoreCase(LATITUDE)).thenReturn(false);

        boolean result = service.latitudeExists(LATITUDE);

        assertFalse(result);
        verify(repository, times(1)).existsByLatitudeIgnoreCase(LATITUDE);
    }

    @Test
    @DisplayName("Deve retornar true quando a longitude existir")
    void longitudeExists() {
        when(repository.existsByLongitudeIgnoreCase(LONGITUDE)).thenReturn(true);

        boolean result = service.longitudeExists(LONGITUDE);

        assertTrue(result);
        verify(repository, times(1)).existsByLongitudeIgnoreCase(LONGITUDE);
    }

    @Test
    @DisplayName("Deve retornar false quando a longitude nao existir")
    void longitudeDoesntExists() {
        when(repository.existsByLongitudeIgnoreCase(LONGITUDE)).thenReturn(false);

        boolean result = service.longitudeExists(LONGITUDE);

        assertFalse(result);
        verify(repository, times(1)).existsByLongitudeIgnoreCase(LONGITUDE);
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao buscar area de risco de enchente com ID inexistente")
    void getNotFoundException() {
        when(repository.findById(ID_AREA_RISCO_ENCHENTE)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.get(ID_AREA_RISCO_ENCHENTE));
        verify(repository, times(1)).findById(ID_AREA_RISCO_ENCHENTE);
        verify(converter, never()).toBO(any(AreaRiscoEnchente.class), any(AreaRiscoEnchenteBO.class));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao atualizar area de risco de enchente com ID inexistente")
    void updateNotFoundException() {
        when(repository.findById(ID_AREA_RISCO_ENCHENTE)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(ID_AREA_RISCO_ENCHENTE, buildAreaRiscoEnchenteInputDTO()));
        verify(repository, times(1)).findById(ID_AREA_RISCO_ENCHENTE);
        verify(converter, never()).toEntity(any(AreaRiscoEnchenteInputDTO.class), any(AreaRiscoEnchente.class));
        verify(repository, never()).save(any(AreaRiscoEnchente.class));
        verify(converter, never()).toBO(any(AreaRiscoEnchente.class), any(AreaRiscoEnchenteBO.class));
    }

    @Test
    @DisplayName("Deve enviar NotFoundException ao tentar excluir area de risco de enchente inexistente")
    void deleteNotFoundException() {
        when(repository.findById(ID_AREA_RISCO_ENCHENTE)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(ID_AREA_RISCO_ENCHENTE));
        verify(repository, times(1)).findById(ID_AREA_RISCO_ENCHENTE);
        verify(repository, never()).deleteById(ID_AREA_RISCO_ENCHENTE);
    }
}