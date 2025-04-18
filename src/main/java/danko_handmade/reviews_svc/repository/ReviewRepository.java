package danko_handmade.reviews_svc.repository;

import danko_handmade.reviews_svc.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    boolean existsByUserIdAndProductId(UUID userId, UUID productId);

    Review findByUserIdAndProductId(UUID userId, UUID productId);
}