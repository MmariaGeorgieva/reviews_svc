package danko_handmade.reviews_svc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReviewResponse {
    @NotNull
    private UUID userId;

    @NotNull
    private String productCode;

    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String textReview;

    @NotNull
    private int rating;

    @NotNull
    private LocalDateTime createdOn;
}
