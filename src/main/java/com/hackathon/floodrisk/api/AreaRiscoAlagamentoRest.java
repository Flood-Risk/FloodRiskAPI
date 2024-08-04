package com.hackathon.floodrisk.api;

import com.hackathon.floodrisk.service.AreaRiscoAlagamentoService;
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
@RequestMapping(value = "/api/areas-riscos-alagamento", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Áreas de risco de alagamentos")
@RequiredArgsConstructor
public class AreaRiscoAlagamentoRest {

    private final AreaRiscoAlagamentoService areaRiscoAlagamentoService;
    private final AreaRiscoAlagamentoApiConverter converter;

    @GetMapping
    @Operation(summary = "Retorna todas as áreas de risco de alagamento")
    public ResponseEntity<List<AreaRiscoAlagamentoOutputDTO>> getAllAreaRiscoAlagamentos() {
        log.info("Iniciando chamada para obter todas as áreas de risco de alagamento");
        return status(HttpStatus.OK).body(areaRiscoAlagamentoService.findAll().stream()
                .map(converter::toOutputDTO)
                .toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna área de risco de alagamento por ID")
    public ResponseEntity<AreaRiscoAlagamentoOutputDTO> getAreaRiscoAlagamento(
            @PathVariable(name = "id") final Long id) {
        log.info("Iniciando chamada para obter área de risco de alagamento com ID: {}", id);
        return status(HttpStatus.OK).body(converter.toOutputDTO(areaRiscoAlagamentoService.get(id)));
    }

    @PostMapping
    @Operation(summary = "Adiciona uma nova área de risco de alagamento")
    public ResponseEntity<AreaRiscoAlagamentoOutputDTO> createAreaRiscoAlagamento(
            @RequestBody @Valid final AreaRiscoAlagamentoInputDTO areaRiscoAlagamentoInputDTO) {
        log.info("Iniciando chamada para adicionar uma nova área de risco de alagamento");
        return status(HttpStatus.CREATED).body(converter.toOutputDTO(areaRiscoAlagamentoService.create(areaRiscoAlagamentoInputDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma área de risco de alagamento por ID")
    public ResponseEntity<AreaRiscoAlagamentoOutputDTO> updateAreaRiscoAlagamento(@PathVariable(name = "id") final Long id,
                                                                                  @RequestBody @Valid final AreaRiscoAlagamentoInputDTO areaRiscoAlagamentoInputDTO) {
        log.info("Iniciando chamada para atualizar área de risco de alagamento com ID: {}", id);
        return status(HttpStatus.OK).body(converter.toOutputDTO(areaRiscoAlagamentoService.update(id, areaRiscoAlagamentoInputDTO)));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @Operation(summary = "Remove uma área de risco de alagamento por ID")
    public ResponseEntity<Void> deleteAreaRiscoAlagamento(@PathVariable(name = "id") final Long id) {
        log.info("Iniciando chamada para remover área de risco de alagamento com ID: {}", id);
        areaRiscoAlagamentoService.delete(id);
        return status(HttpStatus.NO_CONTENT).build();
    }

}
