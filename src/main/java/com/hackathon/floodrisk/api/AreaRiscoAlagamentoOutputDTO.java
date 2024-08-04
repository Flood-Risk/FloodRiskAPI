package com.hackathon.floodrisk.api;

import lombok.Data;

import java.util.List;

@Data
public class AreaRiscoAlagamentoOutputDTO {
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
