package danko_handmade.reviews_svc.web.controller;

import danko_handmade.reviews_svc.model.Review;
import danko_handmade.reviews_svc.service.ReviewService;
import danko_handmade.reviews_svc.web.ReviewController;
import danko_handmade.reviews_svc.web.dto.DtoMapper;
import danko_handmade.reviews_svc.web.dto.ReviewDto;
import danko_handmade.reviews_svc.web.dto.ReviewResponse;
import danko_handmade.reviews_svc.web.dto.UpsertReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerApiTest {

    @MockitoBean
    private ReviewService reviewService;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void upsertReview_shouldReturnCreatedStatusAndReviewResponse() throws Exception {
//
//        UpsertReview upsertReview = new UpsertReview();
//        upsertReview.setOrderId(UUID.randomUUID());
//        upsertReview.setUserId(UUID.randomUUID());
//        upsertReview.setProductId(UUID.randomUUID());
//        upsertReview.setMainPhotoUrl("https://example.com/image.jpg");
//        upsertReview.setTextReview("Great product!");
//        upsertReview.setRating(5);
//
//        Review review = new Review();
//        review.setProductId(upsertReview.getProductId());
//        review.setOrderId(upsertReview.getProductId());
//        review.setUserId(upsertReview.getProductId());
//        review.setTextReview(upsertReview.getTextReview());
//        review.setRating(upsertReview.getRating());
//        review.setMainPhotoUrl(upsertReview.getMainPhotoUrl());
//
//        ReviewResponse reviewResponse = DtoMapper.fromReview(review);
//
//        when(reviewService.upsertReview(upsertReview)).thenReturn(review);
//
//        Review result = reviewService.upsertReview(upsertReview);
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/reviews")
//                        .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request)
//                .andExpect(status().isOk());
//    }

//    @Test
//    void getAllReviews_shouldReturnOkAndListOfReviews() throws Exception {
//
//        List<ReviewDto> mockReviewDtos = new ArrayList<>();
//        mockReviewDtos.add(new ReviewDto(UUID.randomUUID(), "image1.jpg", 5, "Great product!"));
//        mockReviewDtos.add(new ReviewDto(UUID.randomUUID(), "image2.jpg", 3, "Not bad"));
//
//        when(reviewService.getAllReviewsDto()).thenReturn(mockReviewDtos);
//
//        mockMvc.perform(get("/all"))
//                .andExpect(status().isOk());
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$[0].productId").value("Product 1"))
//                .andExpect(jsonPath("$[1].productId").value("Product 2"))
//                .andExpect(jsonPath("$[0].reviewText").value("Great product!"))
//                .andExpect(jsonPath("$[1].reviewText").value("Not bad"))
//                .andExpect(jsonPath("$[0].rating").value(5))
//                .andExpect(jsonPath("$[1].rating").value(3));
//    }


}
