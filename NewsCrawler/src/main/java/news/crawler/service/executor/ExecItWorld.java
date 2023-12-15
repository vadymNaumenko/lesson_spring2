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

import static org.jsoup.Jsoup.connect;

@Slf4j
public class ExecItWorld implements Execute {


//    @Override
//    public List<EventDTO> execute(SourceConfig config) {
//        List<EventDTO> events = new ArrayList<>();
//        try {
//            Document document = connect(config.getRootUrl() + config.getNewsSuffix()).timeout(20000 * 10).get();
//
//            Elements links = document.getElementsByClass("news-time");
//            for (Element element : links) {
//
//                Element href = element.select("a").first();
//                String title = href.text();
//                String newsUrl = config.getRootUrl() + href.attr("href");
//                String time = element.getElementsByClass("news__time").first().text();
//
//
//                Document news = connect(newsUrl).get();
//                Element spanDate = news.select(".separator-line span").first();
//                if (spanDate == null) {
//                    spanDate = news.select(".color-silver span").first();
//                }
//                String newsDate = spanDate != null ? spanDate.text() : "undefined";
//                String imageUrl = config.getRootUrl() + news.select("picture img").first().attr("src");
//                String description = news.select(".article__lid").text();
//                String text = description + " " + news.select(".news-detail__content").text();
//
//                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, time);
//                log.info("scan: {}", newsUrl);
//
//                events.add(new EventDTO(config, title, newsUrl, dateTime, imageUrl, text));
//
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//
//        return events;
//    }

    @Override
    public List<EventDTO> readUrl(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();

        try {
            Document root = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();
            Elements links = root.getElementsByClass("news-time");
            for (Element element : links) {
                Element href = element.selectFirst("a");
                String title = href.text();
                String newsUrl = config.getRootUrl() + href.attr("href");
                String time = element.getElementsByClass("news__time").first().text();

                events.add(new EventDTO(title, newsUrl, config.getRootUrl(), time));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }

    @Override
    public List<EventDTO> readNews(List<EventDTO> newsList) {
        List<EventDTO> events = new ArrayList<>();

        try {
            for (EventDTO event : newsList) {
                log.info("Reading news from {}...", event.getNewsUrl());
                Document news = Jsoup.connect(event.getNewsUrl()).get();

                // get image url
                String imgUrl = news.getElementsByClass("news-detail__picture")
                        .select("a")
                        .attr("href");
                imgUrl =  event.getRootUrl() + imgUrl;

                // get date and create dateTime
                Element spanDate = news.selectFirst(".separator-line span");
                if (spanDate == null) {
                    spanDate = news.selectFirst(".color-silver span");
                }
                String newsDate = spanDate != null? spanDate.text() : "undefined";
                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, event.getTime());

                // get news text
                String description = news.select(".article__lid").text();
                String text = description + " " + news.select(".news-detail__content").text();

                events.add(new EventDTO(event.getTitle(), event.getNewsUrl(), imgUrl, dateTime, text));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }

}


