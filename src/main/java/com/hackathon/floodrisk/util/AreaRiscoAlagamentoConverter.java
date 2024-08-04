package com.hackathon.floodrisk.util;

import com.hackathon.floodrisk.api.AreaRiscoAlagamentoInputDTO;
import com.hackathon.floodrisk.domain.AreaRiscoAlagamento;
import com.hackathon.floodrisk.service.AreaRiscoAlagamentoBO;
import org.springframework.stereotype.Component;

@Component
public class AreaRiscoAlagamentoConverter {

    public AreaRiscoAlagamentoBO toBO(AreaRiscoAlagamento areaRiscoAlagamento, AreaRiscoAlagamentoBO areaRiscoAlagamentoBO) {
        areaRiscoAlagamentoBO.setId(areaRiscoAlagamento.getId());
        areaRiscoAlagamentoBO.setNome(areaRiscoAlagamento.getNome());
        areaRiscoAlagamentoBO.setEstado(areaRiscoAlagamento.getEstado());
        areaRiscoAlagamentoBO.setCidade(areaRiscoAlagamento.getCidade());
        areaRiscoAlagamentoBO.setDescricao(areaRiscoAlagamento.getDescricao());
        areaRiscoAlagamentoBO.setLatitude(areaRiscoAlagamento.getLatitude());
        areaRiscoAlagamentoBO.setLongitude(areaRiscoAlagamento.getLongitude());
        areaRiscoAlagamentoBO.setNivelRisco(areaRiscoAlagamento.getNivelRisco());
        areaRiscoAlagamentoBO.setHistoricoDados(areaRiscoAlagamento.getHistoricoDados());
        return areaRiscoAlagamentoBO;
    }

    public void toEntity(AreaRiscoAlagamentoInputDTO areaRiscoAlagamentoInputDTO, AreaRiscoAlagamento areaRiscoAlagamento) {
        areaRiscoAlagamento.setNome(areaRiscoAlagamentoInputDTO.getNome());
        areaRiscoAlagamento.setDescricao(areaRiscoAlagamentoInputDTO.getDescricao());
        areaRiscoAlagamento.setEstado(areaRiscoAlagamentoInputDTO.getEstado());
        areaRiscoAlagamento.setCidade(areaRiscoAlagamentoInputDTO.getCidade());
        areaRiscoAlagamento.setLatitude(areaRiscoAlagamentoInputDTO.getLatitude());
        areaRiscoAlagamento.setLongitude(areaRiscoAlagamentoInputDTO.getLongitude());
        areaRiscoAlagamento.setNivelRisco(areaRiscoAlagamentoInputDTO.getNivelRisco());
        areaRiscoAlagamento.setHistoricoDados(areaRiscoAlagamentoInputDTO.getHistoricoDados());
    }

}
