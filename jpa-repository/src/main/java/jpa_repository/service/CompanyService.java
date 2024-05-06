package jpa_repository.service;

import jpa_repository.dto.CompanyReadDTO;
import jpa_repository.entity.Company;
import jpa_repository.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;


    public Optional<CompanyReadDTO> findById(Integer id) {
        return companyRepository.findById(id)
                .map(company -> new CompanyReadDTO(company.getId()));
    }


    public void findTopAndSort(){
        Sort.TypedSort<Company> typedSort = Sort.sort(Company.class);
        Sort sortAnd = typedSort.by(Company::getId).and(typedSort.by(Company::getName));
        Sort sortById = Sort.by("id");
        companyRepository.findTop2ByNameBefore("lol",sortById.descending());
    }
    public void findByPageable(){
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by("id"));
//        postRepository.findTop3(pageRequest);
    }

    public List<Company> findTop3ByTitleBefore(String title, Sort descending) {
        return companyRepository.findTop2ByNameBefore(title, Sort.by("title"));
    }
}
