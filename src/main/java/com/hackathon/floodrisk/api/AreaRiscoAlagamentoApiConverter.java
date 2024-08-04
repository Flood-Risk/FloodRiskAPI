package com.hackathon.floodrisk.api;

import com.hackathon.floodrisk.service.AreaRiscoAlagamentoBO;
import org.springframework.stereotype.Component;

@Component
public class AreaRiscoAlagamentoApiConverter {

    public AreaRiscoAlagamentoOutputDTO toOutputDTO(AreaRiscoAlagamentoBO dto) {
        AreaRiscoAlagamentoOutputDTO output = new AreaRiscoAlagamentoOutputDTO();
        output.setId(dto.getId());
        output.setNome(dto.getNome());
        output.setCidade(dto.getCidade());
        output.setEstado(dto.getEstado());
        output.setLatitude(dto.getLatitude());
        output.setLongitude(dto.getLongitude());
        output.setNivelRisco(dto.getNivelRisco());
        output.setDescricao(dto.getDescricao());
        output.setHistoricoDados(dto.getHistoricoDados());
        return output;
    }

}
