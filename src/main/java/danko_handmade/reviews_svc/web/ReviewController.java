package danko_handmade.reviews_svc.web;


import danko_handmade.reviews_svc.model.Review;
import danko_handmade.reviews_svc.service.ReviewService;
import danko_handmade.reviews_svc.web.dto.DtoMapper;
import danko_handmade.reviews_svc.web.dto.UpsertReview;
import danko_handmade.reviews_svc.web.dto.ReviewDto;
import danko_handmade.reviews_svc.web.dto.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping
    ResponseEntity<ReviewResponse> upsertReview(@RequestBody UpsertReview upsertReview) {
        Review review = reviewService.upsertReview(upsertReview);

        ReviewResponse reviewResponse = DtoMapper.fromReview(review);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewResponse);
    }

    @GetMapping("/all")
    ResponseEntity<List<ReviewDto>> getAllReviews() {

        List<ReviewDto> allReviewsDto = reviewService.getAllReviewsDto();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allReviewsDto);
    }
}