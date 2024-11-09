package umc.spring.repository.member;

import umc.spring.web.dto.member.response.MemberResponseDTO;

public interface MemberRepositoryCustom {
    MemberResponseDTO.MyPageDTO findMemberById(Long memberId);
}
