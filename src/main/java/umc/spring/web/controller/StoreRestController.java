package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.mission.MissionCommandService;
import umc.spring.service.store.StoreCommandService;
import umc.spring.service.store.StoreQueryService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.web.dto.mission.request.MissionRequestDTO;
import umc.spring.web.dto.mission.response.MissionResponseDTO;
import umc.spring.web.dto.store.request.StoreRequestDTO;
import umc.spring.web.dto.store.response.StoreResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final MissionCommandService missionCommandService;
    private final StoreQueryService storeQueryService;

    @Operation(summary = "가게 추가 API", description = "특정 지역에 가게를 추가합니다.")
    @PostMapping
    public ApiResponse<StoreResponseDTO.CreateResultDTO> createStore(@Valid @RequestBody StoreRequestDTO.CreateDTO request) {
        Store store = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(StoreConverter.toCreateResultDTO(store));
    }

    @Operation(summary = "리뷰 추가 API", description = "특정 가게에 리뷰를 추가합니다.")
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@ExistStores @PathVariable(name = "storeId") Long storeId,
                                                                            @Valid @RequestBody StoreRequestDTO.CreateReviewDTO request) {
        Review review = storeCommandService.createReview(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @Operation(summary = "미션 추가 API", description = "특정 가게에 미션을 추가합니다.")
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionResultDTO> createMission(@PathVariable(name = "storeId") Long storeId,
                                                                          @Valid @RequestBody MissionRequestDTO.CreateMissionDTO request) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toCreateResultDTO(mission));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStores @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

}
