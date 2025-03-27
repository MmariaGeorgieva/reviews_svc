package danko_handmade.reviews_svc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String productCode;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String textReview;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private LocalDateTime createdOn;
}
