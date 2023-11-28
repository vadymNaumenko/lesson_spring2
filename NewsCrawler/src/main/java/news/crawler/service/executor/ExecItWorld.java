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

                String description = news.select(".article__lid").text();
                String text = description + " " + news.select(".news-detail__content").text();

                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, time);

                events.add(new EventDTO(config, title, newsUrl, dateTime, null, text));

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }
}


