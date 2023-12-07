package spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.controller.dto.CityDTO;
import spring.service.CityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/list")
    public List<CityDTO> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CityDTO cityDTOS) {
        cityService.save(cityDTOS);
    }
    @PutMapping("/edite")
    public void editeCity(@RequestBody CityDTO cityDTO) {
        cityService.editeCity(cityDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }


}
