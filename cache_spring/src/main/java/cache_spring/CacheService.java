package cache_spring;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final Dao dao;

    @Cacheable(cacheNames = "data")
    public String getData(){
        return dao.getData();
    }
    public String deleteData(){
    return "delete";
    }



}
