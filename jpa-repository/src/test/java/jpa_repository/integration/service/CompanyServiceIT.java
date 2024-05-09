package jpa_repository.integration.service;

import jpa_repository.annotation.IT;
import jpa_repository.service.CompanyService;
import lombok.RequiredArgsConstructor;


@IT
@RequiredArgsConstructor
public class CompanyServiceIT {

    private final CompanyService companyService;

}
