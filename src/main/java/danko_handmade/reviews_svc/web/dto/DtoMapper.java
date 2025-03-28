package danko_handmade.reviews_svc.web.dto;

import danko_handmade.reviews_svc.model.Review;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static ReviewResponse fromReview(Review review) {

        return ReviewResponse.builder()
                .userId(review.getUserId())
                .rating(review.getRating())
                .createdOn(review.getCreatedOn())
                .mainPhotoUrl(review.getMainPhotoUrl())
                .textReview(review.getTextReview())
                .productId(review.getProductId())
                .build();
    }

    public static ReviewDto toReviewDto(Review review) {
        return ReviewDto.builder()
                .productId(review.getProductId())
                .mainPhotoUrl(review.getMainPhotoUrl())
                .rating(review.getRating())
                .reviewText(review.getTextReview())
                .build();
    }
}