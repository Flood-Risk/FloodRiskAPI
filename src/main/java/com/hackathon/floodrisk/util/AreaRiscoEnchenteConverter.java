package com.hackathon.floodrisk.util;

import com.hackathon.floodrisk.api.AreaRiscoEnchenteInputDTO;
import com.hackathon.floodrisk.domain.AreaRiscoEnchente;
import com.hackathon.floodrisk.model.AreaRiscoEnchenteBO;
import org.springframework.stereotype.Component;

@Component
public class AreaRiscoEnchenteConverter {

    public AreaRiscoEnchenteBO toBO(AreaRiscoEnchente areaRiscoEnchente, AreaRiscoEnchenteBO areaRiscoEnchenteBO) {
        areaRiscoEnchenteBO.setId(areaRiscoEnchente.getId());
        areaRiscoEnchenteBO.setNome(areaRiscoEnchente.getNome());
        areaRiscoEnchenteBO.setEstado(areaRiscoEnchente.getEstado());
        areaRiscoEnchenteBO.setCidade(areaRiscoEnchente.getCidade());
        areaRiscoEnchenteBO.setDescricao(areaRiscoEnchente.getDescricao());
        areaRiscoEnchenteBO.setLatitude(areaRiscoEnchente.getLatitude());
        areaRiscoEnchenteBO.setLongitude(areaRiscoEnchente.getLongitude());
        areaRiscoEnchenteBO.setNivelRisco(areaRiscoEnchente.getNivelRisco());
        areaRiscoEnchenteBO.setHistoricoDados(areaRiscoEnchente.getHistoricoDados());
        return areaRiscoEnchenteBO;
    }

    public void toEntity(AreaRiscoEnchenteInputDTO areaRiscoEnchenteInputDTO, AreaRiscoEnchente areaRiscoEnchente) {
        areaRiscoEnchente.setNome(areaRiscoEnchenteInputDTO.getNome());
        areaRiscoEnchente.setDescricao(areaRiscoEnchenteInputDTO.getDescricao());
        areaRiscoEnchente.setEstado(areaRiscoEnchenteInputDTO.getEstado());
        areaRiscoEnchente.setCidade(areaRiscoEnchenteInputDTO.getCidade());
        areaRiscoEnchente.setLatitude(areaRiscoEnchenteInputDTO.getLatitude());
        areaRiscoEnchente.setLongitude(areaRiscoEnchenteInputDTO.getLongitude());
        areaRiscoEnchente.setNivelRisco(areaRiscoEnchenteInputDTO.getNivelRisco());
        areaRiscoEnchente.setHistoricoDados(areaRiscoEnchenteInputDTO.getHistoricoDados());
    }

}
