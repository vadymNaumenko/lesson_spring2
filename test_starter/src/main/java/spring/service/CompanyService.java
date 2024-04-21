package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.entity.Company;
import spring.repository.CompanyRepository;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company findById(Integer id){
        return companyRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("company with id: "+id+" not found"));
    }

}
