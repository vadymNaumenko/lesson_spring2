package cache_spring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;
    @GetMapping("/getData")
    public String getData(){
        return cacheService.getData();
    }

}
