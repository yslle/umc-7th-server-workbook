package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.mission.MissionQueryService;
import umc.spring.validation.annotation.IsProgressingMission;
import umc.spring.web.dto.member.request.MemberRequestDTO;

@Component
@RequiredArgsConstructor
public class IsProgressingMissionValidator implements ConstraintValidator<IsProgressingMission, MemberRequestDTO.UpdateMemberMissionDTO> {

    private final MissionQueryService missionQueryService;

    @Override
    public void initialize(IsProgressingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.UpdateMemberMissionDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = !missionQueryService.checkIfMissionIsProgressing(value.getMemberId(), value.getMissionId());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_PROGRESSING.toString()).addConstraintViolation();
        }
        return isValid;
    }
}