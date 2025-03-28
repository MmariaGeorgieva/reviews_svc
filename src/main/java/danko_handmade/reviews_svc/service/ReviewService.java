package danko_handmade.reviews_svc.service;

import danko_handmade.reviews_svc.model.Review;
import danko_handmade.reviews_svc.repository.ReviewRepository;
import danko_handmade.reviews_svc.web.dto.DtoMapper;
import danko_handmade.reviews_svc.web.dto.LeaveReview;
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

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public boolean hasUserReviewedProduct(UUID userId, UUID productId) {
        return reviewRepository.existsByUserIdAndProductId(userId, productId);
    }

    public Review createReview(LeaveReview leaveReview) {

        UUID userId = leaveReview.getUserId();
        UUID productId = leaveReview.getProductId();

        if (hasUserReviewedProduct(userId, productId)) {
            throw new RuntimeException("User has already reviewed this product.");
        }

        Review review = Review.builder()
                .productId(leaveReview.getProductId())
                .textReview(leaveReview.getTextReview())
                .mainPhotoUrl(leaveReview.getMainPhotoUrl())
                .createdOn(LocalDateTime.now())
                .userId(leaveReview.getUserId())
                .rating(leaveReview.getRating())
                .build();
        return reviewRepository.save(review);
    }

    public List<ReviewDto> getAllReviewsDtos() {
        List<Review> allReviews = reviewRepository.findAll();
        ReviewDto reviewDto = null;
        List<ReviewDto> allReviewsDtos = new ArrayList<>();

        for (Review review : allReviews) {
            reviewDto = DtoMapper.toReviewDto(review);
            allReviewsDtos.add(reviewDto);
        }
        return allReviewsDtos;
    }
}