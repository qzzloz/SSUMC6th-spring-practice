package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                .star(request.getScore())
                .content(request.getContents())
                .build();

    }

    public static StoreResponseDTO.WriteReviewDTO toWriteReviewDTO(Review review) {
        return StoreResponseDTO.WriteReviewDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
