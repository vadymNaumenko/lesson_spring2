package news.crawler.service.executor;

import lombok.extern.slf4j.Slf4j;
import news.crawler.common.DateTimeUtils;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.Jsoup.connect;

@Slf4j
public class ExecItWorld implements Execute {


    @Override
    public List<EventDTO> execute(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();
        try {
            Document document = connect(config.getRootUrl() + config.getNewsSuffix()).timeout(20000 * 10).get();

            Elements links = document.getElementsByClass("news-time");
            for (Element element : links) {

                Element href = element.select("a").first();
                String title = href.text();
                String newsUrl = config.getRootUrl() + href.attr("href");
                String time = element.getElementsByClass("news__time").first().text();


                Document news = connect(newsUrl).get();
                Element spanDate = news.select(".separator-line span").first();
                if (spanDate == null) {
                    spanDate = news.select(".color-silver span").first();
                }
                String newsDate = spanDate != null ? spanDate.text() : "undefined";
                String imageUrl = config.getRootUrl() + news.select("picture img").first().attr("src");
                String description = news.select(".article__lid").text();
                String text = description + " " + news.select(".news-detail__content").text();

                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, time);
                log.info("scan: {}", newsUrl);

                events.add(new EventDTO(config, title, newsUrl, dateTime, imageUrl, text));

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }

    @Override
    public List<EventDTO> readNews(List<EventDTO> eventDTOS) {
        for (int i = 0; i < eventDTOS.size() - 1; i++) {
            try {
                Document news = connect(eventDTOS.get(i).getNewsUrl()).get();
                Element spanDate = news.select(".separator-line span").first();
                if (spanDate == null) {
                    spanDate = news.select(".color-silver span").first();
                }
                String newsDate = spanDate != null ? spanDate.text() : "undefined";
                String imageUrl = eventDTOS.get(eventDTOS.size() - 1).getNewsUrl() + news.select("picture img").first().attr("src"); //TODO read rootUrl from last object
                String description = news.select(".article__lid").text();
                String text = description + " " + news.select(".news-detail__content").text();
                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, eventDTOS.get(i).getImageUrl());//TODO read imageUrl from eventDTOS "eventDTOS.get(i).getImageUrl() = getTime"

                log.info("scan: {}", eventDTOS.get(i).getNewsUrl());

                eventDTOS.get(i).setDateTime(dateTime);
                eventDTOS.get(i).setImageUrl(imageUrl);
                eventDTOS.get(i).setText(text);

            } catch (
                    IOException e) {
                log.error(e.getMessage());
            }
        }

        return eventDTOS;
    }
    @Override
    public List<EventDTO> readUrl(SourceConfig config) {

        List<EventDTO> events = new ArrayList<>();
        try {
            Document document = connect(config.getRootUrl() + config.getNewsSuffix()).timeout(20000 * 10).get();

            Elements links = document.getElementsByClass("news-time");
            for (Element element : links) {

                Element href = element.select("a").first();
                String title = href.text();
                String newsUrl = config.getRootUrl() + href.attr("href");
                String time = element.getElementsByClass("news__time").first().text();

                events.add(new EventDTO(config, title, newsUrl, null, time, null)); //TODO time mast put in  imageUrl
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        events.add(new EventDTO(null, null, config.getRootUrl(), null, null, null)); //TODO RootUrl mast put in last object in  newsUrl
        return events;
    }

}


