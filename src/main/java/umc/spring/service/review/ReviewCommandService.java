package umc.spring.service.review;

import umc.spring.domain.Review;

public interface ReviewCommandService {
    Review createReview(Float rating, String content, Long memberId, Long storeId);
}
