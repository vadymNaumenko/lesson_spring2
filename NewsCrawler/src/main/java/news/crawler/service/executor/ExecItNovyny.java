package news.crawler.service.executor;

import lombok.extern.slf4j.Slf4j;
import news.crawler.common.DateTimeUtils;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExecItNovyny implements Execute {


    /*
    SourceConfig
    rootUrl: https://it.novyny.live/ru/news/
    newsSuffix: ?page=1
    className: news.crawler.service.executer.ExecItNovyny
     */

    @Override
    public List<EventDTO> execute(SourceConfig config) {

        List<EventDTO> events = new ArrayList<>();

        Document doc = null;


        try {

            doc = Jsoup.connect(config.getRootUrl()+config.getNewsSuffix()).get();
//            doc = Jsoup.connect(config.getRootUrl()).get();

            Elements links = doc.select(".all-news__list a");

            for (Element element : links) {
                String newsUrl = element.attr("href");
                String title = element.select("span h3").text();
                if (!title.isEmpty()) {
                    Document page = Jsoup.connect(newsUrl).get();
                    String text = page.select(".content__wrapp p").text();
                    String dateTime = page.select(".content__info-create").text();
                    String photoUrl = page.select(".content__main-image img").attr("src");

                    LocalDateTime localDateTime = DateTimeUtils.convertDateTime(dateTime);
                    log.info("scan: {}",newsUrl);
                    events.add(new EventDTO(config, title, newsUrl, localDateTime, photoUrl, text));

                }
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }


        return events;
    }

    @Override
    public List<EventDTO> readTitle(List<String> title) {
        return null;
    }
}
