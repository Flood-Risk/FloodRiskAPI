package com.hackathon.floodrisk.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.hackathon.floodrisk.service.AreaRiscoEnchenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;

@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = AreaRiscoEnchenteLongitudeUnique.AreaRiscoEnchenteLongitudeUniqueValidator.class
)
public @interface AreaRiscoEnchenteLongitudeUnique {

    String message() default "{Exists.areaRiscoEnchente.longitude}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class AreaRiscoEnchenteLongitudeUniqueValidator implements ConstraintValidator<AreaRiscoEnchenteLongitudeUnique, String> {

        private final AreaRiscoEnchenteService areaRiscoEnchenteService;
        private final HttpServletRequest request;

        public AreaRiscoEnchenteLongitudeUniqueValidator(
                final AreaRiscoEnchenteService areaRiscoEnchenteService,
                final HttpServletRequest request) {
            this.areaRiscoEnchenteService = areaRiscoEnchenteService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(areaRiscoEnchenteService.get(Long.parseLong(currentId)).getLongitude())) {
                return true;
            }
            return !areaRiscoEnchenteService.longitudeExists(value);
        }

    }

}
