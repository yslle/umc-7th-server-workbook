package umc.spring.web.dto.mission.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    @Getter
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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionResultDTO {
        private Long missionId;
        private Integer price;
        private Integer reward;
        private Long storeId;
        private String storeName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        Long missionId;
        Integer price;
        Integer reward ;
        LocalDate deadline;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        List<MissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

}
