package umc.spring.web.dto.member.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.spring.domain.enums.Role;
import umc.spring.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto {
        @NotBlank
        String name;
        @NotNull
        LocalDate birth;
        @NotNull
        String address;
        @NotNull
        Integer gender;
        @Email
        String email;
        @NotBlank
        String password;
        @NotNull
        String phone;
        @ExistCategories
        List<Long> preferCategory;
        @NotNull
        Role role;
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateMemberMissionDTO {
        @NotNull
        Long memberId;
        @NotNull
        Long missionId;
    }
}