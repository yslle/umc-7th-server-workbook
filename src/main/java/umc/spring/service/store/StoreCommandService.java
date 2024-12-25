package umc.spring.service.store;

import org.springframework.web.multipart.MultipartFile;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.store.request.StoreRequestDTO;

public interface StoreCommandService {
    Store createStore(StoreRequestDTO.CreateDTO request);

    Review createReview(Long storeId, StoreRequestDTO.CreateReviewDTO request, MultipartFile reviewPicture);
}
