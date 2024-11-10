package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.mission.MissionRepository;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public List<MissionResponseDTO.MissionDTO> getMissionsByMemberAndStatus(Long memberId, MissionStatus status, int page) {
        return missionRepository.findMissionsByMemberIdAndStatus(memberId, status, page);
    }

    @Override
    public List<MissionResponseDTO.MissionHomeDTO> getMissionsForHome(Long memberId, String regionName, int page) {
        return missionRepository.findMissionsForHome(memberId, regionName, page);
    }

}
