package umc.spring.repository.mission;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, int page);
}
