package spring.mapper;

import spring.controller.dto.CityDTO;
import spring.domin.City;

public class CityMapper {

    public static City toCity(CityDTO cityDTO){
        return new City(cityDTO.getId(),cityDTO.getName());
    }
}
