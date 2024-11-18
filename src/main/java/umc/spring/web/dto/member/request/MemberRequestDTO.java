package umc.spring.web.dto.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String name;
        @NotNull
        LocalDate birth;
        @NotNull
        String address;
        @NotNull
        Integer gender;
        @NotNull
        String email;
        @NotNull
        String phone;
        @ExistCategories
        List<Long> preferCategory;
    }
}