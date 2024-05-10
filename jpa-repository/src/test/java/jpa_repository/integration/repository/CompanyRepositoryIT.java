package jpa_repository.integration.repository;

import jakarta.persistence.EntityManager;
import jpa_repository.annotation.IT;
import jpa_repository.entity.Company;
import jpa_repository.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class CompanyRepositoryIT {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;
    private static final Integer COMPANY_APPLE_ID = 4;


    @Test
    void checkFindByQueries(){
        Optional<Company> google = companyRepository.findByName("Google");
        List<Company> companies = companyRepository.findByNameContainingIgnoreCase("a");
        System.out.println("google");
    }
    @Test
    @Order(2)
    @Commit
    void delete(){
        Optional<Company> maybeCompany = companyRepository.findById(COMPANY_APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        companyRepository.deleteById(COMPANY_APPLE_ID);
//        entityManager.flush(); // or set commit

        assertTrue(companyRepository.findById(COMPANY_APPLE_ID).isEmpty());

    }
    @Test
//    @Transactional
//    @Rollback
//    @Commit
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
            var company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    @Commit
    @Order(1)
    void save() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}
