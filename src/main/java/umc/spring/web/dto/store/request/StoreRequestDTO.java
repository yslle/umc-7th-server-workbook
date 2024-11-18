package umc.spring.web.dto.store.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class CreateDTO {
        @NotEmpty
        String name;
        @NotNull
        String category;
        @NotNull
        String address;
        @NotNull
        String operatingHours;
        @NotNull
        String region;
    }
}
