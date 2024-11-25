package umc.spring.service.member;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.member.response.MemberResponseDTO;

import java.util.List;

public interface MemberQueryService {
    MemberResponseDTO.MyPageDTO getMemberById(Long memberId);

    boolean checkIfCategoriesExist(List<Long> categoryIds);

    Page<Review> getMyReviewList(int page);
}
