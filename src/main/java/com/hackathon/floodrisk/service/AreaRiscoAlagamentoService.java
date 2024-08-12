package com.hackathon.floodrisk.service;

import com.hackathon.floodrisk.api.AreaRiscoAlagamentoInputDTO;
import com.hackathon.floodrisk.domain.AreaRiscoAlagamento;
import com.hackathon.floodrisk.repos.AreaRiscoAlagamentoRepository;
import com.hackathon.floodrisk.util.AreaRiscoAlagamentoConverter;
import com.hackathon.floodrisk.util.NotFoundException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AreaRiscoAlagamentoService {

    private final AreaRiscoAlagamentoRepository areaRiscoAlagamentoRepository;
    private final AreaRiscoAlagamentoConverter converter;

    public List<AreaRiscoAlagamentoBO> findAll() {
        final List<AreaRiscoAlagamento> areaRiscoAlagamentos = areaRiscoAlagamentoRepository.findAll(Sort.by("id"));
        return areaRiscoAlagamentos.stream()
                .map(areaRiscoAlagamento -> converter.toBO(areaRiscoAlagamento, new AreaRiscoAlagamentoBO()))
                .toList();
    }

    public AreaRiscoAlagamentoBO get(final Long id) {
        return areaRiscoAlagamentoRepository.findById(id)
                .map(areaRiscoAlagamento -> converter.toBO(areaRiscoAlagamento, new AreaRiscoAlagamentoBO()))
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public AreaRiscoAlagamentoBO create(final AreaRiscoAlagamentoInputDTO areaRiscoAlagamentoInputDTO) {
        final AreaRiscoAlagamento areaRiscoAlagamento = new AreaRiscoAlagamento();
        converter.toEntity(areaRiscoAlagamentoInputDTO, areaRiscoAlagamento);
        return converter.toBO(areaRiscoAlagamentoRepository.save(areaRiscoAlagamento), new AreaRiscoAlagamentoBO());
    }

    @Transactional
    public AreaRiscoAlagamentoBO update(final Long id, final AreaRiscoAlagamentoInputDTO areaRiscoAlagamentoInputDTO) {
        final AreaRiscoAlagamento areaRiscoAlagamento = areaRiscoAlagamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        converter.toEntity(areaRiscoAlagamentoInputDTO, areaRiscoAlagamento);
        return converter.toBO(areaRiscoAlagamentoRepository.save(areaRiscoAlagamento), new AreaRiscoAlagamentoBO());
    }

    @Transactional
    public void delete(final Long id) {
        AreaRiscoAlagamento areaRiscoAlagamento = areaRiscoAlagamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        areaRiscoAlagamentoRepository.delete(areaRiscoAlagamento);
    }

}
