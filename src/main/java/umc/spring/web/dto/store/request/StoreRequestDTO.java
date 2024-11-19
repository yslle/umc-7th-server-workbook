package umc.spring.web.dto.store.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

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

    @Getter
    public static class CreateReviewDTO {
        @NotNull
        Float rating;
        @NotEmpty
        String content;
        List<String> imageUrls;
    }

}
