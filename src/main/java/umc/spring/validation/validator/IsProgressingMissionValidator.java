package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.mission.MemberMissionRepository;
import umc.spring.validation.annotation.IsProgressingMission;
import umc.spring.web.dto.member.request.MemberRequestDTO;

@Component
@RequiredArgsConstructor
public class IsProgressingMissionValidator implements ConstraintValidator<IsProgressingMission, MemberRequestDTO.UpdateMemberMissionDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(IsProgressingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.UpdateMemberMissionDTO value, ConstraintValidatorContext context) {
        boolean isValid = memberMissionRepository.findByMemberIdAndMissionIdAndStatus(
                value.getMemberId(), value.getMissionId(), MissionStatus.PROGRESSING).isEmpty();

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_PROGRESSING.toString()).addConstraintViolation();
        }
        return isValid;
    }
}