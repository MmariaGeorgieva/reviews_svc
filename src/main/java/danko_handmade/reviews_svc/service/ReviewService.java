package danko_handmade.reviews_svc.service;

import danko_handmade.reviews_svc.model.Review;
import danko_handmade.reviews_svc.repository.ReviewRepository;
import danko_handmade.reviews_svc.web.dto.DtoMapper;
import danko_handmade.reviews_svc.web.dto.LeaveReview;
import danko_handmade.reviews_svc.web.dto.ReviewDto;
import danko_handmade.reviews_svc.web.dto.ReviewResponse;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public boolean hasUserReviewedProduct(UUID userId, String productCode) {
        return reviewRepository.existsByUserIdAndProductCode(userId, productCode);
    }

    public Review createReview(LeaveReview leaveReview) {

        UUID userId = leaveReview.getUserId();
        String productCode = leaveReview.getProductCode();

        if (hasUserReviewedProduct(userId, productCode)) {
            throw new RuntimeException("User has already reviewed this product.");
        }

        Review review = Review.builder()
                .productCode(leaveReview.getProductCode())
                .textReview(leaveReview.getTextReview())
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
