package com.hackathon.floodrisk.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Não encontrado área de risco de enchente com o ID: " + id);
    }

}
