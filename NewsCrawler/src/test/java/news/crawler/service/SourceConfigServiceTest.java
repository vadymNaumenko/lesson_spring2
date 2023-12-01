package news.crawler.service;

import news.crawler.controller.dto.SourceConfigDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SourceConfigServiceTest {


    @Autowired
    private SourceConfigService configService;

    @Test
    @Order(1)
    void save() {
        SourceConfigDTO config = new SourceConfigDTO("www.https://it.world.ru","news","className",null);
       // Long count = configService.countEvents();
       // System.out.println();  //TODO add method count events
        SourceConfigDTO dto = configService.add(config);
        assertAll(()->{

            assertEquals(config.getRootUrl(),dto.getRootUrl());
            assertEquals(config.getNewsSuffix(),dto.getNewsSuffix());
            assertNotNull(dto.getId());
            assertEquals(config.getClassName(),dto.getClassName());

        });

    }

    @Test
    @Order(3)
    void delete() {
    }

//    @Test
//    @Order(2)
//    void update() {
//        SourceConfigDTO source = new SourceConfigDTO(1,"www.https://it.world.ru","news","className",null);
//        SourceConfigDTO config = configService.update(source);
//        assertEquals(config,source);
//    }

    @Test
    void add() {
    }
}