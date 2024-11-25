package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.response.MemberResponseDTO;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberResponseDTO.UpdateMemberMissionResultDTO toUpdateMemberMissionDTO(MemberMission memberMission) {
        return MemberResponseDTO.UpdateMemberMissionResultDTO.builder()
                .missionId(memberMission.getMission().getId())
                .memberMissionId(memberMission.getId())
                .missionStatus(memberMission.getStatus())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }

    public static MissionResponseDTO.MyMissionPreViewDTO myMissionPreViewDTO(MemberMission memberMission) {
        return MissionResponseDTO.MyMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .price(memberMission.getMission().getPrice())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .storeId(memberMission.getMission().getStore().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .storeCategory(memberMission.getMission().getStore().getCategory())
                .build();
    }

    public static MissionResponseDTO.MyMissionPreViewListDTO myMissionPreViewListDTO(Page<MemberMission> memberMissionPage) {
        List<MissionResponseDTO.MyMissionPreViewDTO> myMissionPreViewDTOList = memberMissionPage.stream()
                .map(MemberMissionConverter::myMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MyMissionPreViewListDTO.builder()
                .isLast(memberMissionPage.isLast())
                .isFirst(memberMissionPage.isFirst())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .listSize(myMissionPreViewDTOList.size())
                .missionList(myMissionPreViewDTOList)
                .build();
    }
}
