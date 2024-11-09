package umc.spring.repository.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMember;
import umc.spring.web.dto.member.response.MemberResponseDTO;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private final QMember member = QMember.member;

    @Override
    public MemberResponseDTO.MyPageDTO findMemberById(Long memberId) {
        return queryFactory
                .select(Projections.constructor(MemberResponseDTO.MyPageDTO.class,
                        member.id,
                        member.name,
                        member.email,
                        member.phone,
                        member.point))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }

}
