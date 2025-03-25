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
                .textReview(review.getTextReview())
                .productId(review.getProductId())
                .build();
    }
}
