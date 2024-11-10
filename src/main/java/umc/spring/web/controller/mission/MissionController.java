package umc.spring.web.controller.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.mission.MissionQueryService;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;

    // 미션 목록 조회(진행 중, 진행 완료) API
    @GetMapping
    public List<MissionResponseDTO.MissionDTO> getMissionsByMemberAndStatus(@RequestParam(name = "memberId") Long memberId, @RequestParam(name = "status") MissionStatus status,
                                                                 @RequestParam(name = "page", defaultValue = "0") int page) {
        return missionQueryService.getMissionsByMemberAndStatus(memberId, status, page);
    }
}
