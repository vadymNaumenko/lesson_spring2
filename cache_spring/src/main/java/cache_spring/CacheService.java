package cache_spring;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class CacheService {
    private final Dao dao;
    private final CacheManager cacheManager;

    public String getManualData(String lastname){
       return cacheManager.getCache("data").get(lastname,String.class);
    }

    @Cacheable(key = "#lastname")
    public String getData(String lastname){
        return dao.getData(lastname);
    }

    @CachePut
    public String update(String lastName){
    return dao.getData(lastName);
    }

    @CacheEvict(cacheNames = "data",allEntries = true)
    public void evict (){
    }

//    @Cacheable(cacheNames = "data")
//    public String getData(String lastname){
//        return dao.getData(lastname);
//    }
//
//    @CachePut(cacheNames = "data")
//    public String update(String lastName){
//        return dao.getData(lastName);
//    }
//
//    @CacheEvict(cacheNames = "data")
//    public void evict (){
//    }



}
