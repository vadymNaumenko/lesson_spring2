package cache_spring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;
    @GetMapping("/getData")
    public String getData(@RequestParam String lastName){
        return cacheService.getData(lastName);
    }
    @GetMapping("/update")
    public String update(@RequestParam String lastName){
        return cacheService.update(lastName);
    }
    @GetMapping("/evict")
    public void evict(){
        cacheService.evict();
    }
}
