package cache_spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final Dao dao;

    public String getData(){
        return dao.getData();
    }
    public String deleteData(){
    return "delete";
    }



}
