package spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.entity.Company;
import spring.repository.CompanyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private  CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        Company company = new Company();
        company.setId(1);
        company.setName("Google");
        Mockito.doReturn(Optional.of(company))
                .when(companyRepository).findById(1);

        Company result = companyService.findById(1);
        assertTrue(result!=null);
        assertEquals(1,result.getId());


    }
}