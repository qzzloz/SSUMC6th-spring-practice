package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Member;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.validation.annotation.AlreadyMission;

@Component
@RequiredArgsConstructor
public class MissionAlreadyExistValidator implements ConstraintValidator<AlreadyMission, Long> {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(AlreadyMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long values, ConstraintValidatorContext context) {
        boolean isValid = memberMissionRepository.existsById(values);

        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_EXIST_MISSION.toString()).addConstraintViolation();
            return false;
        }

        return true;

    }
}
