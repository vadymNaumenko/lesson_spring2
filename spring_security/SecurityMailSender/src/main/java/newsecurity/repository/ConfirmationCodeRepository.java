package newsecurity.repository;

import newsecurity.model.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode,Integer> {
    Optional<ConfirmationCode> findByCode(String cod);
}
