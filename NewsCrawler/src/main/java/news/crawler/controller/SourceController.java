package news.crawler.controller;

import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.SourceConfig;
import news.crawler.service.SourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/source")
public class SourceController {
    @Autowired
    private SourceConfigService configService;

    @GetMapping("/all")
    public List<SourceConfigDTO> findAll() {
        return configService.findAll();
    }

    @PostMapping("/add")
    public SourceConfigDTO add(@RequestBody SourceConfigDTO config){
        return configService.add(config);
    }

    @PutMapping("/update")
    public ResponseEntity<SourceConfigDTO> update(@RequestBody SourceConfigDTO config){
        SourceConfigDTO responce = configService.update(config);
        if (responce == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responce);
    }

    @PutMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        SourceConfig response = configService.delete(id);
//        if (response == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(response);
    }

}
