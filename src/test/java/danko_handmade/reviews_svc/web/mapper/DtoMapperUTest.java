package danko_handmade.reviews_svc.web.mapper;

import danko_handmade.reviews_svc.model.Review;
import danko_handmade.reviews_svc.web.dto.DtoMapper;
import danko_handmade.reviews_svc.web.dto.ReviewDto;
import danko_handmade.reviews_svc.web.dto.ReviewResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DtoMapperUTest {

    @Test
    void givenHappyPath_whenReviewToReviewResponse() {
        Review review = Review.builder()
                .userId(UUID.randomUUID())
                .productId(UUID.randomUUID())
                .orderId(UUID.randomUUID())
                .mainPhotoUrl("image.jpg")
                .textReview("textReview")
                .rating(5)
                .createdOn(LocalDateTime.now())
                .build();
        ReviewResponse reviewResponse = DtoMapper.fromReview(review);
        assertEquals(review.getProductId(), reviewResponse.getProductId());
        assertEquals(review.getUserId(), reviewResponse.getUserId());
        assertEquals(review.getMainPhotoUrl(), reviewResponse.getMainPhotoUrl());
        assertEquals(review.getTextReview(), reviewResponse.getTextReview());
        assertEquals(review.getRating(), reviewResponse.getRating());
        assertEquals(review.getCreatedOn(), reviewResponse.getCreatedOn());
    }

    @Test
    void givenHappyPath_whenReviewToReviewDto() {
        Review review = Review.builder()
                .userId(UUID.randomUUID())
                .productId(UUID.randomUUID())
                .orderId(UUID.randomUUID())
                .mainPhotoUrl("image.jpg")
                .textReview("textReview")
                .rating(5)
                .createdOn(LocalDateTime.now())
                .build();
        ReviewDto reviewDto = DtoMapper.toReviewDto(review);
        assertEquals(review.getProductId(), reviewDto.getProductId());
        assertEquals(review.getMainPhotoUrl(), reviewDto.getMainPhotoUrl());
        assertEquals(review.getTextReview(), reviewDto.getReviewText());
        assertEquals(review.getRating(), reviewDto.getRating());
    }
}
