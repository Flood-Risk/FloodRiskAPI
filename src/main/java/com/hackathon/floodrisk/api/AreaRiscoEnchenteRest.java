package com.hackathon.floodrisk.api;

import com.hackathon.floodrisk.service.AreaRiscoEnchenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestController
@RequestMapping(value = "/api/areas-riscos-enchente", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Áreas de risco de enchentes")
@RequiredArgsConstructor
public class AreaRiscoEnchenteRest {

    private final AreaRiscoEnchenteService areaRiscoEnchenteService;
    private final AreaRiscoEnchenteApiConverter converter;

    @GetMapping
    @Operation(summary = "Retorna todas as áreas de risco de enchente")
    public ResponseEntity<List<AreaRiscoEnchenteOutputDTO>> getAllAreaRiscoEnchentes() {
        log.info("Iniciando chamada para obter todas as áreas de risco de enchente");
        return status(HttpStatus.OK).body(areaRiscoEnchenteService.findAll().stream()
                .map(converter::toOutputDTO)
                .toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna área de risco de enchente por ID")
    public ResponseEntity<AreaRiscoEnchenteOutputDTO> getAreaRiscoEnchente(
            @PathVariable(name = "id") final Long id) {
        log.info("Iniciando chamada para obter área de risco de enchente com ID: {}", id);
        return status(HttpStatus.OK).body(converter.toOutputDTO(areaRiscoEnchenteService.get(id)));
    }

    @PostMapping
    @Operation(summary = "Adiciona uma nova área de risco de enchente")
    public ResponseEntity<AreaRiscoEnchenteOutputDTO> createAreaRiscoEnchente(
            @RequestBody @Valid final AreaRiscoEnchenteInputDTO areaRiscoEnchenteInputDTO) {
        log.info("Iniciando chamada para adicionar uma nova área de risco de enchente");
        return status(HttpStatus.CREATED).body(converter.toOutputDTO(areaRiscoEnchenteService.create(areaRiscoEnchenteInputDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma área de risco de enchente por ID")
    public ResponseEntity<AreaRiscoEnchenteOutputDTO> updateAreaRiscoEnchente(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final AreaRiscoEnchenteInputDTO areaRiscoEnchenteInputDTO) {
        log.info("Iniciando chamada para atualizar área de risco de enchente com ID: {}", id);
        return status(HttpStatus.OK).body(converter.toOutputDTO(areaRiscoEnchenteService.update(id, areaRiscoEnchenteInputDTO)));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @Operation(summary = "Remove uma área de risco de enchente por ID")
    public ResponseEntity<Void> deleteAreaRiscoEnchente(@PathVariable(name = "id") final Long id) {
        log.info("Iniciando chamada para remover área de risco de enchente com ID: {}", id);
        areaRiscoEnchenteService.delete(id);
        return status(HttpStatus.NO_CONTENT).build();
    }

}
