package danko_handmade.reviews_svc.service;

import danko_handmade.reviews_svc.model.Review;
import danko_handmade.reviews_svc.repository.ReviewRepository;
import danko_handmade.reviews_svc.web.dto.DtoMapper;
import danko_handmade.reviews_svc.web.dto.UpsertReview;
import danko_handmade.reviews_svc.web.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final DtoMapper dtoMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, DtoMapper dtoMapper) {
        this.reviewRepository = reviewRepository;
        this.dtoMapper = dtoMapper;
    }


    public boolean hasUserAlreadyReviewedThisProduct(UUID userId, UUID productId) {
        return reviewRepository.existsByUserIdAndProductId(userId, productId);
    }

    public Review upsertReview(UpsertReview upsertReview) {

        UUID userId = upsertReview.getUserId();
        UUID productId = upsertReview.getProductId();

        if (hasUserAlreadyReviewedThisProduct(userId, productId)) {
            Review review = reviewRepository.findByUserIdAndProductId(userId, productId);
            review.setRating(upsertReview.getRating());
            review.setTextReview(upsertReview.getTextReview());
            return reviewRepository.save(review);
        }

        Review review = Review.builder()
                .productId(upsertReview.getProductId())
                .orderId(upsertReview.getOrderId())
                .textReview(upsertReview.getTextReview())
                .mainPhotoUrl(upsertReview.getMainPhotoUrl())
                .createdOn(LocalDateTime.now())
                .userId(upsertReview.getUserId())
                .rating(upsertReview.getRating())
                .build();
        return reviewRepository.save(review);
    }

    public List<ReviewDto> getAllReviewsDto() {
        List<Review> allReviews = reviewRepository.findAll();
        List<ReviewDto> allReviewsDto = new ArrayList<>();

        for (Review review : allReviews) {
            ReviewDto reviewDto = DtoMapper.toReviewDto(review);
            allReviewsDto.add(reviewDto);
        }
        return allReviewsDto;
    }
}