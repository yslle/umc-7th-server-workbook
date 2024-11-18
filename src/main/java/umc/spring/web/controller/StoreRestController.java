package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.store.StoreCommandService;
import umc.spring.web.dto.store.request.StoreRequestDTO;
import umc.spring.web.dto.store.response.StoreResponseDTO;

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

}
