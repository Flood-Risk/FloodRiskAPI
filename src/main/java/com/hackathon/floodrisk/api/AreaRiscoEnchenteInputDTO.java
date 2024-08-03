package com.hackathon.floodrisk.api;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class AreaRiscoEnchenteInputDTO {
    @NotNull
    @Size(max = 255)
    private String nome;
    @NotNull
    @Size(max = 255)
    private String descricao;
    @NotNull
    @Size(max = 255)
    private String estado;
    @NotNull
    @Size(max = 255)
    private String cidade;
    @Indexed(unique = true)
    @NotNull
    @Size(max = 255)
    private String latitude;
    @Indexed(unique = true)
    @NotNull
    @Size(max = 255)
    private String longitude;
    @Min(1)
    @Max(5)
    private Integer nivelRisco;
    private List<@Size(max = 255) String> historicoDados;
}
