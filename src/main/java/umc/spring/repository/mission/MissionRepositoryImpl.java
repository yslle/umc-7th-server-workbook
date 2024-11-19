package umc.spring.repository.mission;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMission;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QStore store = QStore.store;
    private final QRegion region = QRegion.region;

    @Override
    public List<MissionResponseDTO.MissionResultDTO> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, int page) {
        return queryFactory
                .select(Projections.constructor(
                        MissionResponseDTO.MissionResultDTO.class,
                        mission.id,
                        mission.price,
                        mission.reward,
                        mission.store.name
                ))
                .from(mission)
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

    @Override
    public List<MissionResponseDTO.MissionHomeDTO> findMissionsForHome(Long memberId, String regionName, int page) {
        return queryFactory
                .select(Projections.constructor(MissionResponseDTO.MissionHomeDTO.class,
                        mission.id.as("missionId"),
                        mission.price,
                        mission.reward,
                        mission.deadline,
                        store.name.as("storeName"),
                        store.category.as("storeCategory")
                ))
                .from(mission)
                .innerJoin(store).on(mission.store.id.eq(store.id))
                .innerJoin(memberMission).on(mission.id.eq(memberMission.mission.id))
                .innerJoin(region).on(store.region.id.eq(region.id))
                .where(
                        region.name.eq(regionName)
                                .and(memberMission.member.id.eq(memberId))
                                .and(memberMission.status.eq(MissionStatus.NONE))
                )
                .orderBy(mission.deadline.desc())
                .limit(10)
                .offset((page - 1) * 10L)
                .fetch();
    }
}
