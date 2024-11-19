package umc.spring.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.review.ReviewImageRepository;
import umc.spring.repository.review.ReviewRepository;
import umc.spring.repository.store.StoreRepository;
import umc.spring.web.dto.store.request.StoreRequestDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateDTO request) {
        // region 조회
        Region region = regionRepository.findByName(request.getRegion())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        // store 생성
        Store newStore = StoreConverter.toStore(request);
        newStore.setRegion(region);
        return storeRepository.save(newStore);
    }

    @Override
    @Transactional
    public Review createReview(Long storeId, StoreRequestDTO.CreateReviewDTO request) {
        // 하드코딩된 멤버
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Optional<Store> store = storeRepository.findById(storeId);

        // @ExistStores 사용하지 않는 경우
//        Store store = storeRepository.findById(storeId)
//                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // Review 생성
        Review newReview = StoreConverter.toReview(request);
        newReview.setMember(member);
        newReview.setStore(store.get());

        Review savedReview = reviewRepository.save(newReview);

        // ReviewImage 처리
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            List<ReviewImage> reviewImages = StoreConverter.toReviewImages(savedReview, request.getImageUrls());

            for (ReviewImage reviewImage : reviewImages) {
                reviewImage.setReview(savedReview);
                savedReview.getReviewImageList().add(reviewImage);
            }
            reviewImageRepository.saveAll(reviewImages);
        }

        return savedReview;
    }

}
