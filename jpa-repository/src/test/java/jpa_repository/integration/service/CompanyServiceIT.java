package jpa_repository.integration.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import jpa_repository.annotation.IT;
import jpa_repository.entity.Company;
import jpa_repository.service.CompanyService;
import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.Sort;

import java.util.List;

@IT
@RequiredArgsConstructor
public class CompanyServiceIT {

    private final CompanyService postService;
    private final EntityManager entityManager;

    @Test
    public void findById() {
        Company actual = postService.findById(1);

        Company post = entityManager.find(Company.class, 1);
        assertNotNull(post);
        assertNotNull(actual);
        assertEquals(1, actual.getId());
    }

    @Test
    public void findTopAndSort() {
        Sort.TypedSort<Company> typedSort = Sort.sort(Company.class);
        Sort sortAnd = typedSort.by(Company::getId).and(typedSort.by(Company::getTitle));

        List<Company> posts = postService.findTop3ByTitleBefore("путин",sortAnd.descending());
        assertFalse(posts.isEmpty());

    }

    @Test
    public void findByPageable() {
//        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by("id"));
//        postRepository.findTop3(pageRequest);
    }
}
