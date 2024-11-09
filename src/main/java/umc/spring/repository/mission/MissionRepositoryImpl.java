package umc.spring.repository.mission;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, int page) {
        return queryFactory
                .selectFrom(mission)
                .join(memberMission).on(mission.id.eq(memberMission.mission.id))
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(status)
                )
                .orderBy(mission.createdAt.desc())
                .limit(10)
                .offset((page - 1) * 10L)
                .fetch();
    }

}
