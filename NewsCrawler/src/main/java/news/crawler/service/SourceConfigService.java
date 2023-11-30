package news.crawler.service;

import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.SourceConfig;
import news.crawler.mapper.MapToSourceConfig;
import news.crawler.repository.SourceConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceConfigService {
    @Autowired
    private SourceConfigRepository configRepository;
    public List<SourceConfigDTO> findAll() {
        return configRepository.findAll().stream()
                .map(SourceConfigDTO::getInstance)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
    configRepository.deleteById(id);
    }

    public SourceConfigDTO update(SourceConfigDTO config) {
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
        SourceConfig save = configRepository.save(MapToSourceConfig.mapToSource(config));
        return SourceConfigDTO.getInstance(save);
    }

    public Long countEvents(){
        return configRepository.countAll();
    }

}
