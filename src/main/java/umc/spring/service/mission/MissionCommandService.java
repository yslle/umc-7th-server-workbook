package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.request.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(Long storeId, MissionRequestDTO.CreateMissionDTO request);
}
