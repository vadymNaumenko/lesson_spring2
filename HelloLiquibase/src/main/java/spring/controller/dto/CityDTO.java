package spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.domin.City;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private Long id;
    private String name;

    public static CityDTO getInstance(City city){
        return new CityDTO(city.getId(),city.getName());
    }
}
