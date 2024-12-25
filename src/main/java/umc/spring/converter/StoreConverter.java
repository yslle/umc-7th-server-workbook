package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.web.dto.store.request.StoreRequestDTO;
import umc.spring.web.dto.store.response.StoreResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.CreateResultDTO toCreateResultDTO(Store store) {
        return StoreResponseDTO.CreateResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static Store toStore(StoreRequestDTO.CreateDTO request) {
        return Store.builder()
                .name(request.getName())
                .category(request.getCategory())
                .address(request.getAddress())
                .operatingHours(request.getOperatingHours())
                .rating(5.0f)
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        List<String> imageUrls = review.getReviewImageList().stream()
                .map(ReviewImage::getUrl)
                .distinct()
                .toList();

        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .imageUrls(imageUrls)
                .writer(review.getMember().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(StoreRequestDTO.CreateReviewDTO request) {
        return Review.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .reviewImageList(new ArrayList<>())
                .build();
    }

    public static ReviewImage toReviewImage(String pictureUrl, Review review) {
        return ReviewImage.builder()
                .url(pictureUrl)
                .review(review)
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

}
