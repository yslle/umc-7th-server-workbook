package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.mission.MissionCommandService;
import umc.spring.service.mission.MissionQueryService;
import umc.spring.validation.annotation.IsProgressingMission;
import umc.spring.web.dto.member.request.MemberRequestDTO;
import umc.spring.web.dto.member.response.MemberResponseDTO;
import umc.spring.web.dto.mission.response.MissionResponseDTO;

import java.util.List;

@Validated
@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    // 미션 목록 조회(진행 중, 진행 완료) API
//    @GetMapping
//    public List<MissionResponseDTO.MissionResultDTO> getMissionsByMemberAndStatus(@RequestParam(name = "memberId") Long memberId, @RequestParam(name = "status") MissionStatus status,
//                                                                                  @RequestParam(name = "page", defaultValue = "0") int page) {
//        return missionQueryService.getMissionsByMemberAndStatus(memberId, status, page);
//    }

    @Operation(summary = "미션 도전하기 API", description = "특정 미션을 도전 중인 미션에 추가합니다.")
    @PutMapping("/challenge")
    public ApiResponse<MemberResponseDTO.UpdateMemberMissionResultDTO> updateMemberMissionStatus(@Valid @IsProgressingMission @RequestBody MemberRequestDTO.UpdateMemberMissionDTO request) {
        MemberMission memberMission = missionCommandService.updateMemberMissionStatus(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateMemberMissionDTO(memberMission));
    }

    @Operation(summary = "진행 중인 미션 진행 완료로 바꾸기 API", description = "진행 중인 미션을 진행 완료로 바꾸는 API입니다.")
    @PutMapping("/{memberMissionId}/complete")
    public ApiResponse<MemberResponseDTO.UpdateMemberMissionResultDTO> updateMemberMissionStatus(@PathVariable(name = "memberMissionId") Long memberMissionId) {
        MemberMission memberMission = missionCommandService.updateMemberMissionStatusToComplete(memberMissionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateMemberMissionDTO(memberMission));
    }
}
