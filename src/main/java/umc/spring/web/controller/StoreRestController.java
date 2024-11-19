package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.store.StoreCommandService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.web.dto.store.request.StoreRequestDTO;
import umc.spring.web.dto.store.response.StoreResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

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


}
