package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.controller.dto.CityDTO;
import spring.mapper.CityMapper;
import spring.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<CityDTO> findAll() {
        return cityRepository.findAll().stream()
                .map(CityDTO::getInstance)
                .collect(Collectors.toList());
    }

    public CityDTO findById(Long id) {

        return cityRepository.findById(id)
                .stream()
                .map(CityDTO::getInstance)
                .findFirst().orElse(null);
    }

    public void editeAll(List<CityDTO> cityDTOS) {
        cityDTOS.stream().map(CityMapper::toCity).map(cityRepository::save);
    }

    public void editeCity(CityDTO cityDTO) {
        cityRepository.save(CityMapper.toCity(cityDTO));
    }


    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

}
