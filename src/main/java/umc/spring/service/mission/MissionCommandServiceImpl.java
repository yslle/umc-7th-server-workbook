package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.mission.MemberMissionRepository;
import umc.spring.repository.mission.MissionRepository;
import umc.spring.repository.store.StoreRepository;
import umc.spring.web.dto.mission.request.MissionRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Mission createMission(Long storeId, MissionRequestDTO.CreateMissionDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // Mission 생성
        Mission newMission = MissionConverter.toMission(request);
        newMission.setStore(store);
        Mission savedMission = missionRepository.save(newMission);

        List<Member> members = memberRepository.findAll();

        // 각 멤버에 대해 MemberMission 생성
        List<MemberMission> memberMissions = members.stream()
                .map(member -> MemberMission.builder()
                        .mission(savedMission)
                        .member(member)
                        .status(MissionStatus.NONE)
                        .build())
                .toList();

        memberMissionRepository.saveAll(memberMissions);
        return savedMission;
    }
}
