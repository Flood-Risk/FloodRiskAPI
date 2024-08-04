package com.hackathon.floodrisk.repos;

import com.hackathon.floodrisk.domain.AreaRiscoAlagamento;
import com.hackathon.floodrisk.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class AreaRiscoAlagamentoListener extends AbstractMongoEventListener<AreaRiscoAlagamento> {

    private final PrimarySequenceService primarySequenceService;

    public AreaRiscoAlagamentoListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<AreaRiscoAlagamento> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());
        }
    }

}
