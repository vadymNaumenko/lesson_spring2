package jpa_repository.integration.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import jpa_repository.annotation.IT;
import jpa_repository.entity.Company;
import jpa_repository.service.CompanyService;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

@IT
@RequiredArgsConstructor
public class CompanyServiceIT {

    private final CompanyService companyService;
    private final EntityManager entityManager;

    @Test
    void findById() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple1")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}
