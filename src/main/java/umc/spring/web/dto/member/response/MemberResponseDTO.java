package umc.spring.web.dto.member.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class MyPageDTO {
        private Long memberId;
        private String name;
        private String email;
        private String phone;
        private Integer point;

        @Override
        public String toString() {
            return "MyPageDTO {" +
                    "memberId=" + memberId +
                    ", name=" + name +
                    ", email=" + email +
                    ", phone=" + phone +
                    ", point='" + point +
                    '}';
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateMemberMissionResultDTO {
        Long missionId;
        Long memberMissionId;
        MissionStatus missionStatus;
        LocalDateTime updatedAt;
    }
}
