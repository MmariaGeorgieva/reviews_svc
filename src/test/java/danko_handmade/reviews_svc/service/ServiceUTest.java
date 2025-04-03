package danko_handmade.reviews_svc.service;

import danko_handmade.reviews_svc.model.Review;
import danko_handmade.reviews_svc.repository.ReviewRepository;
import danko_handmade.reviews_svc.web.dto.DtoMapper;
import danko_handmade.reviews_svc.web.dto.ReviewDto;
import danko_handmade.reviews_svc.web.dto.UpsertReview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceUTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private DtoMapper dtoMapper;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void givenUserHasReviewedProduct_whenHasUserAlreadyReviewedThisProduct_thenReturnTrue() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        when(reviewRepository.existsByUserIdAndProductId(userId, productId)).thenReturn(true);

        boolean result = reviewService.hasUserAlreadyReviewedThisProduct(userId, productId);

        assertTrue(result);
        verify(reviewRepository, times(1)).existsByUserIdAndProductId(userId, productId);
    }

    @Test
    void givenUserHasNotReviewedProduct_whenHasUserAlreadyReviewedThisProduct_thenReturnFalse() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        when(reviewRepository.existsByUserIdAndProductId(userId, productId)).thenReturn(false);

        boolean result = reviewService.hasUserAlreadyReviewedThisProduct(userId, productId);

        assertFalse(result);
        verify(reviewRepository, times(1)).existsByUserIdAndProductId(userId, productId);
    }

    @Test
    void givenUserHasAlreadyReviewed_whenUpsertReview_thenUpdateExistingReview() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        UpsertReview upsertReview = UpsertReview.builder()
                .userId(userId)
                .productId(productId)
                .orderId(UUID.randomUUID())
                .textReview("Updated review")
                .mainPhotoUrl("updated-photo-url")
                .rating(4)
                .build();

        Review existingReview = new Review();
        existingReview.setUserId(userId);
        existingReview.setProductId(productId);
        existingReview.setRating(3);
        existingReview.setTextReview("Old review");

        when(reviewService.hasUserAlreadyReviewedThisProduct(userId, productId)).thenReturn(true);
        when(reviewRepository.findByUserIdAndProductId(userId, productId)).thenReturn(existingReview);
        when(reviewRepository.save(any(Review.class))).thenReturn(existingReview);

        Review result = reviewService.upsertReview(upsertReview);

        assertEquals(4, result.getRating());
        assertEquals("Updated review", result.getTextReview());
        verify(reviewRepository, times(1)).findByUserIdAndProductId(userId, productId);
        verify(reviewRepository, times(1)).save(existingReview);
    }

    @Test
    void givenUserHasNotReviewed_whenUpsertReview_thenCreateNewReview() {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        UpsertReview upsertReview = UpsertReview.builder()
                .userId(userId)
                .productId(productId)
                .orderId(UUID.randomUUID())
                .textReview("New review")
                .mainPhotoUrl("new-photo-url")
                .rating(5)
                .build();

        Review newReview = Review.builder()
                .userId(userId)
                .productId(productId)
                .orderId(upsertReview.getOrderId())
                .textReview("New review")
                .mainPhotoUrl("new-photo-url")
                .rating(5)
                .createdOn(LocalDateTime.now())
                .build();

        when(reviewService.hasUserAlreadyReviewedThisProduct(userId, productId)).thenReturn(false);
        when(reviewRepository.save(any(Review.class))).thenReturn(newReview);

        Review result = reviewService.upsertReview(upsertReview);

        assertEquals(5, result.getRating());
        assertEquals("New review", result.getTextReview());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void whenGetAllReviewsDto_thenReturnMappedDtoList() {

        List<Review> mockReviews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Review review = Review.builder()
                    .userId(UUID.randomUUID())
                    .productId(UUID.randomUUID())
                    .orderId(UUID.randomUUID())
                    .textReview("New review" + i)
                    .mainPhotoUrl("new-photo-url" + i)
                    .rating(5 - i)
                    .createdOn(LocalDateTime.now())
                    .build();
            mockReviews.add(review);
        }

        List<ReviewDto> expectedDtos = new ArrayList<>();
        for (Review review : mockReviews) {
            ReviewDto dto = ReviewDto.builder()
                    .productId(review.getProductId())
                    .reviewText(review.getTextReview())
                    .mainPhotoUrl(review.getMainPhotoUrl())
                    .rating(review.getRating())
                    .build();
            expectedDtos.add(dto);
        }

        when(reviewRepository.findAll()).thenReturn(mockReviews);

        List<ReviewDto> result = reviewService.getAllReviewsDto();

        assertEquals(3, result.size());
        assertEquals(expectedDtos, result);
        for (int i = 0; i < expectedDtos.size(); i++) {
            assertEquals(expectedDtos.get(i), result.get(i));
        }
        verify(reviewRepository, times(1)).findAll();
        verifyNoMoreInteractions(reviewRepository);
    }
}
