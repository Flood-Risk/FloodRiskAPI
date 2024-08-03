package com.hackathon.floodrisk.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.List;

@Document("areaRiscoEnchentes")
@Data
public class AreaRiscoEnchente {
    @Id
    private Long id;
    private String nome;
    private String descricao;
    private String estado;
    private String cidade;
    private String latitude;
    private String longitude;
    private Integer nivelRisco;
    private List<String> historicoDados;

    @CreatedDate
    private OffsetDateTime dateCreated;
    @LastModifiedDate
    private OffsetDateTime lastUpdated;
    @Version
    private Integer version;

}
