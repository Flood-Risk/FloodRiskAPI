package com.hackathon.floodrisk.service;

import com.hackathon.floodrisk.api.AreaRiscoEnchenteInputDTO;
import com.hackathon.floodrisk.domain.AreaRiscoEnchente;
import com.hackathon.floodrisk.model.AreaRiscoEnchenteBO;
import com.hackathon.floodrisk.repos.AreaRiscoEnchenteRepository;
import com.hackathon.floodrisk.util.AreaRiscoEnchenteConverter;
import com.hackathon.floodrisk.util.NotFoundException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AreaRiscoEnchenteService {

    private final AreaRiscoEnchenteRepository areaRiscoEnchenteRepository;
    private final AreaRiscoEnchenteConverter converter;

    public List<AreaRiscoEnchenteBO> findAll() {
        final List<AreaRiscoEnchente> areaRiscoEnchentes = areaRiscoEnchenteRepository.findAll(Sort.by("id"));
        return areaRiscoEnchentes.stream()
                .map(areaRiscoEnchente -> converter.toBO(areaRiscoEnchente, new AreaRiscoEnchenteBO()))
                .toList();
    }

    public AreaRiscoEnchenteBO get(final Long id) {
        return areaRiscoEnchenteRepository.findById(id)
                .map(areaRiscoEnchente -> converter.toBO(areaRiscoEnchente, new AreaRiscoEnchenteBO()))
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public AreaRiscoEnchenteBO create(final AreaRiscoEnchenteInputDTO areaRiscoEnchenteInputDTO) {
        final AreaRiscoEnchente areaRiscoEnchente = new AreaRiscoEnchente();
        converter.toEntity(areaRiscoEnchenteInputDTO, areaRiscoEnchente);
        return converter.toBO(areaRiscoEnchenteRepository.save(areaRiscoEnchente), new AreaRiscoEnchenteBO());
    }

    @Transactional
    public AreaRiscoEnchenteBO update(final Long id, final AreaRiscoEnchenteInputDTO areaRiscoEnchenteInputDTO) {
        final AreaRiscoEnchente areaRiscoEnchente = areaRiscoEnchenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        converter.toEntity(areaRiscoEnchenteInputDTO, areaRiscoEnchente);
        return converter.toBO(areaRiscoEnchenteRepository.save(areaRiscoEnchente), new AreaRiscoEnchenteBO());
    }

    @Transactional
    public void delete(final Long id) {
        areaRiscoEnchenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        areaRiscoEnchenteRepository.deleteById(id);
    }

    public boolean latitudeExists(final String latitude) {
        return areaRiscoEnchenteRepository.existsByLatitudeIgnoreCase(latitude);
    }

    public boolean longitudeExists(final String longitude) {
        return areaRiscoEnchenteRepository.existsByLongitudeIgnoreCase(longitude);
    }

}
