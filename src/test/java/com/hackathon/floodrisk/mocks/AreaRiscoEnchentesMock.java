package com.hackathon.floodrisk.mocks;

import com.hackathon.floodrisk.api.AreaRiscoEnchenteInputDTO;
import com.hackathon.floodrisk.api.AreaRiscoEnchenteOutputDTO;
import com.hackathon.floodrisk.domain.AreaRiscoEnchente;
import com.hackathon.floodrisk.model.AreaRiscoEnchenteBO;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class AreaRiscoEnchentesMock {

    public static AreaRiscoEnchenteBO buildAreaRiscoEnchenteBO() {
        AreaRiscoEnchenteBO bo = new AreaRiscoEnchenteBO();
        bo.setId(1L);
        bo.setNome("Ilha das Flores");
        bo.setDescricao("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                "especialmente durante chuvas intensas e períodos de cheia dos rios.");
        bo.setEstado("Rio Grande do Sul");
        bo.setCidade("Porto Alegre");
        bo.setLatitude("-29.98754116965509");
        bo.setLongitude("-51.258601061711346");
        bo.setNivelRisco(5);
        bo.setHistoricoDados(List.of("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));
        return bo;
    }

    public static AreaRiscoEnchenteOutputDTO buildAreaRiscoEnchenteOutputDTO() {
        AreaRiscoEnchenteOutputDTO outputDTO = new AreaRiscoEnchenteOutputDTO();
        outputDTO.setId(1L);
        outputDTO.setNome("Ilha das Flores");
        outputDTO.setDescricao("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                "especialmente durante chuvas intensas e períodos de cheia dos rios.");
        outputDTO.setEstado("Rio Grande do Sul");
        outputDTO.setCidade("Porto Alegre");
        outputDTO.setLatitude("-29.98754116965509");
        outputDTO.setLongitude("-51.258601061711346");
        outputDTO.setNivelRisco(5);
        outputDTO.setHistoricoDados(List.of("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));
        return outputDTO;
    }

    public static AreaRiscoEnchenteInputDTO buildAreaRiscoEnchenteInputDTO() {
        AreaRiscoEnchenteInputDTO input = new AreaRiscoEnchenteInputDTO();
        input.setNome("Ilha das Flores");
        input.setDescricao("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                "especialmente durante chuvas intensas e períodos de cheia dos rios.");
        input.setEstado("Rio Grande do Sul");
        input.setCidade("Porto Alegre");
        input.setLatitude("-29.98754116965509");
        input.setLongitude("-51.258601061711346");
        input.setNivelRisco(5);
        input.setHistoricoDados(List.of("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));
        return input;
    }

    public static AreaRiscoEnchente buildAreaRiscoEnchente() {
        AreaRiscoEnchente entity = new AreaRiscoEnchente();
        entity.setId(1L);
        entity.setNome("Ilha das Flores");
        entity.setDescricao("Localizada próxima ao delta do Jacuí, essa região enfrenta riscos frequentes de inundação, " +
                "especialmente durante chuvas intensas e períodos de cheia dos rios.");
        entity.setEstado("Rio Grande do Sul");
        entity.setCidade("Porto Alegre");
        entity.setLatitude("-29.98754116965509");
        entity.setLongitude("-51.258601061711346");
        entity.setNivelRisco(5);
        entity.setHistoricoDados(List.of("https://www.brasildefators.com.br/2023/11/22/moradores-das-ilhas-de-porto-alegre-sofrem-com-mais-uma-enchente-do-guaiba"));
        entity.setDateCreated(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        return entity;
    }
}
