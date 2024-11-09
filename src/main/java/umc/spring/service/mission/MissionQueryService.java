package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionQueryService {
    List<Mission> getMissionsByMemberAndStatus(Long memberId, MissionStatus status, int page);

}
