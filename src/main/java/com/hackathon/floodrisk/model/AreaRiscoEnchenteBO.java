package com.hackathon.floodrisk.model;

import lombok.Data;

import java.util.List;

@Data
public class AreaRiscoEnchenteBO {
    private Long id;
    private String nome;
    private String descricao;
    private String estado;
    private String cidade;
    private String latitude;
    private String longitude;
    private Integer nivelRisco;
    private List<String> historicoDados;
}
