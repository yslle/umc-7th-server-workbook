package umc.spring.service.mission;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

import java.util.List;

public interface MissionQueryService {
    List<MissionResponseDTO.MissionResultDTO> getMissionsByMemberAndStatus(Long memberId, MissionStatus status, int page);

    List<MissionResponseDTO.MissionHomeDTO> getMissionsForHome(Long memberId, String regionName, int page);
}
