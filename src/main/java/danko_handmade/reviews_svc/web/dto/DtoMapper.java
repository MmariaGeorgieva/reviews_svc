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
                .productCode(review.getProductCode())
                .build();
    }

    public static ReviewDto toReviewDto(Review review) {
        return ReviewDto.builder()
                .productCode(review.getProductCode())
                .mainPhotoUrl(review.getMainPhotoUrl())
                .rating(review.getRating())
                .reviewText(review.getTextReview())
                .build();
    }
}