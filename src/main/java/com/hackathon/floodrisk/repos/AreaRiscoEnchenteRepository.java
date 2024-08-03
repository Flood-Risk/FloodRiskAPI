package com.hackathon.floodrisk.repos;

import com.hackathon.floodrisk.domain.AreaRiscoEnchente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AreaRiscoEnchenteRepository extends MongoRepository<AreaRiscoEnchente, Long> {
    boolean existsByLatitudeIgnoreCase(String latitude);
    boolean existsByLongitudeIgnoreCase(String longitude);
}
