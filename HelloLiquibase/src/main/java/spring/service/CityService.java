package spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.controller.dto.CityDTO;
import spring.mapper.CityMapper;
import spring.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

//    private static Logger log =  LoggerFactory.getLogger(CityService.class);

    public List<CityDTO> findAll() {
        log.info("find all in service");
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

    public void save(CityDTO cityDTO){
        log.info("save city {}",cityDTO);
        cityRepository.save(CityMapper.toCity(cityDTO));
    }

    public void editeCity(CityDTO cityDTO) {
        cityRepository.findById(cityDTO.getId()).ifPresent((city)->{
            cityRepository.save(CityMapper.toCity(cityDTO));
            log.info("save city: {} ",cityDTO);
        });
        //log.info("not save city: {} ",cityDTO);
    }


    public void deleteById(Long id) {
        cityRepository.findById(id).ifPresent((city)->{
            cityRepository.deleteById(id);
            log.info("remove city {} ",city);
        });
        //log.info("not fount city with id: {} ",id);
    }

}
