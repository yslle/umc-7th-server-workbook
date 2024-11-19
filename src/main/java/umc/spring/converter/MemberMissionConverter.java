package umc.spring.converter;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.response.MemberResponseDTO;

public class MemberMissionConverter {

    public static MemberResponseDTO.UpdateMemberMissionResultDTO toUpdateMemberMissionDTO(MemberMission memberMission) {
        return MemberResponseDTO.UpdateMemberMissionResultDTO.builder()
                .missionId(memberMission.getMission().getId())
                .memberMissionId(memberMission.getId())
                .missionStatus(memberMission.getStatus())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }
}
