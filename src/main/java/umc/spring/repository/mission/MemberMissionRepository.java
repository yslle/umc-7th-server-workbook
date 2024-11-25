package umc.spring.repository.mission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

    Optional<MemberMission> findByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus missionStatus);

    Page<MemberMission> findByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);
}
