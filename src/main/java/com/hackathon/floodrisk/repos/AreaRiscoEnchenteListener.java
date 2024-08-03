package com.hackathon.floodrisk.repos;

import com.hackathon.floodrisk.domain.AreaRiscoEnchente;
import com.hackathon.floodrisk.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class AreaRiscoEnchenteListener extends AbstractMongoEventListener<AreaRiscoEnchente> {

    private final PrimarySequenceService primarySequenceService;

    public AreaRiscoEnchenteListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<AreaRiscoEnchente> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());
        }
    }

}
