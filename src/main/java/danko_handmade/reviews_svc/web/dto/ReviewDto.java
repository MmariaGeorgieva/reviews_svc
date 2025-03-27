package danko_handmade.reviews_svc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {

    @NotBlank
    private String productCode;

    @NotNull
    private int rating;

    @NotBlank
    private String reviewText;
}

