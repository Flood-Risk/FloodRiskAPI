package com.hackathon.floodrisk.api;

import com.hackathon.floodrisk.model.AreaRiscoEnchenteBO;
import org.springframework.stereotype.Component;

@Component
public class AreaRiscoEnchenteApiConverter {

    public AreaRiscoEnchenteOutputDTO toOutputDTO(AreaRiscoEnchenteBO dto) {
        AreaRiscoEnchenteOutputDTO output = new AreaRiscoEnchenteOutputDTO();
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
