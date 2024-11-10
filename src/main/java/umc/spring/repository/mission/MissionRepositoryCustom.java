package umc.spring.repository.mission;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

import java.util.List;

public interface MissionRepositoryCustom {
    List<MissionResponseDTO.MissionDTO> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, int page);

    List<MissionResponseDTO.MissionHomeDTO> findMissionsForHome(Long memberId, String regionName, int page);
}
