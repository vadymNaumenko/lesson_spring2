package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.SourceConfig;
import news.crawler.mapper.MapToSourceConfig;
import news.crawler.repository.SourceConfigRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class SourceConfigService {
    @Autowired
    private SourceConfigRepository configRepository;
    public List<SourceConfigDTO> findAll() {
        return configRepository.findAll().stream()
                .map(SourceConfigDTO::getInstance)
                .collect(Collectors.toList());
    }

    public SourceConfig delete(Integer id) {

        Optional<SourceConfig> byId = configRepository.findById(id);

        if (!byId.isEmpty()){
        configRepository.deleteById(id);
        return byId.get();
        }
        return null;
    }

    public SourceConfigDTO update(SourceConfigDTO config) {
        Validate.notNull(config.getId(),"Field id can not be null");
        SourceConfig isConfig = configRepository.findById(config.getId()).orElse(null);
        System.out.println();
        if (isConfig != null){
            SourceConfig r = MapToSourceConfig.mapToSource(config);
            r.setId(config.getId());
            configRepository.save(r);
            return SourceConfigDTO.getInstance(isConfig);
        }

       return null;
    }

    public SourceConfigDTO add(SourceConfigDTO config) {
        Validate.notNull(config.getRootUrl(),"RootUrl can not be null");
        SourceConfig save = configRepository.save(MapToSourceConfig.mapToSource(config));
        return SourceConfigDTO.getInstance(save);
    }

    public Long countEvents(){
        return configRepository.countAll();
    }

}
