package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MissionAlreadyExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {MissionAlreadyExistValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyMission {
    String message() default "이미 진행 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
