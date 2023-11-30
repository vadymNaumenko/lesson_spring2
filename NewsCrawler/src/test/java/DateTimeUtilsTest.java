import news.crawler.common.DateTimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DateTimeUtilsTest {

    @Test
    public void convertDateTime(){
        String dt = "17 октября, 2023 15:39";

        LocalDateTime dateTime = LocalDateTime.parse("17.10.2023 15:39", DateTimeFormatter.ofPattern("d.M.yyyy HH:mm"));
        Assertions.assertEquals(dateTime, DateTimeUtils.convertDateTime(dt));
    }

}