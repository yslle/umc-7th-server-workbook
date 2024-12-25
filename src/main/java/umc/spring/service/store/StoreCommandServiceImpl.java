package umc.spring.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.aws.AmazonS3Manager;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.domain.common.Uuid;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.UuidRepository;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.review.ReviewImageRepository;
import umc.spring.repository.review.ReviewRepository;
import umc.spring.repository.store.StoreRepository;
import umc.spring.web.dto.store.request.StoreRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final UuidRepository uuidRepository;
    private final AmazonS3Manager s3Manager;

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
    public Review createReview(Long storeId, StoreRequestDTO.CreateReviewDTO request, MultipartFile reviewPicture) {
        // 하드코딩된 멤버
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Optional<Store> store = storeRepository.findById(storeId);

        // @ExistStores 사용하지 않는 경우
//        Store store = storeRepository.findById(storeId)
//                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // Review 생성
        Review review = StoreConverter.toReview(request);
        review.setMember(member);
        review.setStore(store.get());

        // Review Image
        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);

        review.setMember(memberRepository.findById(member.getId()).get());
        review.setStore(storeRepository.findById(storeId).get());


        reviewImageRepository.save(StoreConverter.toReviewImage(pictureUrl, review));
        return reviewRepository.save(review);
    }

}
