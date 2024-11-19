package umc.spring.web.dto.mission.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class CreateMissionDTO {
        @NotNull
        Integer price;
        @NotNull
        LocalDate deadline;
        @NotNull
        Integer reward;
        @NotNull
        String ownerCode;
    }
}
