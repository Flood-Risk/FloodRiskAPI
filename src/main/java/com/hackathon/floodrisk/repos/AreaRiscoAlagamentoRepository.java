package com.hackathon.floodrisk.repos;

import com.hackathon.floodrisk.domain.AreaRiscoAlagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AreaRiscoAlagamentoRepository extends MongoRepository<AreaRiscoAlagamento, Long> {
}
