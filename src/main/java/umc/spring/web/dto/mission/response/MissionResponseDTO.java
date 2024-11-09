package umc.spring.web.dto.mission.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionHomeDTO {
        private Long missionId;
        private Integer price;
        private Integer reward;
        private LocalDate deadline;
        private String storeName;
        private String storeCategory;

        @Override
        public String toString() {
            return "MissionHomeDTO {" +
                    "missionId=" + missionId +
                    ", price=" + price +
                    ", reward=" + reward +
                    ", deadline=" + deadline +
                    ", storeName='" + storeName + '\'' +
                    ", storeCategory='" + storeCategory + '\'' +
                    '}';
        }
    }

}
