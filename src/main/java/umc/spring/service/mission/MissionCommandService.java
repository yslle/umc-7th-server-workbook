package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.request.MemberRequestDTO;
import umc.spring.web.dto.mission.request.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(Long storeId, MissionRequestDTO.CreateMissionDTO request);

    MemberMission updateMemberMissionStatus(MemberRequestDTO.UpdateMemberMissionDTO request);

    MemberMission updateMemberMissionStatusToComplete(Long memberMissionId);
}
