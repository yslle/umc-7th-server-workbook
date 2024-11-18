package umc.spring.service.member;

import umc.spring.domain.Member;
import umc.spring.web.dto.member.request.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
