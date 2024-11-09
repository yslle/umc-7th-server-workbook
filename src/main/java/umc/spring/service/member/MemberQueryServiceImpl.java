package umc.spring.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.member.MemberRepository;
import umc.spring.web.dto.member.response.MemberResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDTO.MyPageDTO getMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId);
    }

}
