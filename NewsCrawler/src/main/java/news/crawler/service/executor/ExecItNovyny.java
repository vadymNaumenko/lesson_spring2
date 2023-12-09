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

    @Override
    public List<EventDTO> execute(SourceConfig config) {

        List<EventDTO> events = new ArrayList<>();

        Document doc = null;

        try {

            doc = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();

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
                    log.info("scan: {}", newsUrl);
                    events.add(new EventDTO(config, title, newsUrl, localDateTime, photoUrl, text));

                }
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }


        return events;
    }

    @Override
    public List<EventDTO> readNews(List<EventDTO> eventDTOS) {
        for (int i = 0; i < eventDTOS.size(); i++) {

            try {
                Document page = Jsoup.connect(eventDTOS.get(i).getNewsUrl()).get();
                String text = page.select(".content__wrapp p").text();
                String dateTime = page.select(".content__info-create").text();
                String photoUrl = page.select(".content__main-image img").attr("src");
                LocalDateTime localDateTime = DateTimeUtils.convertDateTime(dateTime);

                log.info("scan: {}", eventDTOS.get(i).getNewsUrl());

                eventDTOS.get(i).setImageUrl(photoUrl);
                eventDTOS.get(i).setDateTime(localDateTime);
                eventDTOS.get(i).setText(text);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return eventDTOS;
    }

    @Override
    public List<EventDTO> readUrl(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();

        Document doc = null;

        try {

            doc = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();

            Elements links = doc.select(".all-news__list a");

            for (Element element : links) {
                String newsUrl = element.attr("href");
                String title = element.select("span h3").text();

                if (!title.isEmpty()) {
                    events.add(new EventDTO(null, title, newsUrl, null, null, null));
                }
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }


}
