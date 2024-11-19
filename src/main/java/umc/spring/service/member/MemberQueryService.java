package umc.spring.service.member;

import umc.spring.web.dto.member.response.MemberResponseDTO;

import java.util.List;

public interface MemberQueryService {
    MemberResponseDTO.MyPageDTO getMemberById(Long memberId);

    boolean checkIfCategoriesExist(List<Long> categoryIds);
}
