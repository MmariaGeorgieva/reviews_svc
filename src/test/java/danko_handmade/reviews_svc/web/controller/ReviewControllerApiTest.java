package danko_handmade.reviews_svc.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

    @Test
    void upsertReview_shouldReturnCreatedStatusAndReviewResponse() throws Exception {
        UpsertReview upsertReview = new UpsertReview();
        upsertReview.setOrderId(UUID.randomUUID());
        upsertReview.setUserId(UUID.randomUUID());
        upsertReview.setProductId(UUID.randomUUID());
        upsertReview.setMainPhotoUrl("https://example.com/image.jpg");
        upsertReview.setTextReview("Great product!");
        upsertReview.setRating(5);

        Review review = new Review();
        review.setProductId(upsertReview.getProductId());
        review.setOrderId(upsertReview.getOrderId());
        review.setUserId(upsertReview.getUserId());
        review.setTextReview(upsertReview.getTextReview());
        review.setRating(upsertReview.getRating());
        review.setMainPhotoUrl(upsertReview.getMainPhotoUrl());

        when(reviewService.upsertReview(upsertReview)).thenReturn(review);

        String jsonContent = new ObjectMapper().writeValueAsString(upsertReview);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(review.getProductId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(review.getUserId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.textReview").value(review.getTextReview()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(review.getRating()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mainPhotoUrl").value(review.getMainPhotoUrl()));
    }
}
