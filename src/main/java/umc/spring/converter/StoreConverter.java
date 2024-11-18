package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.request.StoreRequestDTO;
import umc.spring.web.dto.store.response.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.CreateResultDTO toCreateResultDTO(Store store) {
        return StoreResponseDTO.CreateResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static Store toStore(StoreRequestDTO.CreateDTO request) {
        return Store.builder()
                .name(request.getName())
                .category(request.getCategory())
                .address(request.getAddress())
                .operatingHours(request.getOperatingHours())
                .rating(5.0f)
                .build();
    }
}
