package umc.spring.service.member;

import umc.spring.web.dto.member.response.MemberResponseDTO;

public interface MemberQueryService {
    MemberResponseDTO.MyPageDTO getMemberById(Long memberId);
}
