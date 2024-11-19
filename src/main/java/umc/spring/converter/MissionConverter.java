package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.request.MissionRequestDTO;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

public class MissionConverter {

    public static MissionResponseDTO.MissionResultDTO toCreateResultDTO(Mission mission) {
        return MissionResponseDTO.MissionResultDTO.builder()
                .missionId(mission.getId())
                .price(mission.getPrice())
                .reward(mission.getReward())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.CreateMissionDTO request) {
        return Mission.builder()
                .price(request.getPrice())
                .deadline(request.getDeadline())
                .reward(request.getReward())
                .ownerCode(request.getOwnerCode())
                .build();
    }
}
